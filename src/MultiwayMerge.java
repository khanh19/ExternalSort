import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;

public class MultiwayMerge {
    private RunStore runner;
    private ArrayList<RunStore.RunInfo> array;

    public MultiwayMerge(String sourceFile, RunStore run, ArrayList<RunStore.RunInfo> list) {
        this.runner = run;
        this.array = list;
    }

    public RunStore getRunner() {
        return this.runner;
    }

    public void setRunner(RunStore runner) {
        this.runner = runner;
    }

    public ArrayList<RunStore.RunInfo> getArray() {
        return this.array;
    }

    public void setArray(ArrayList<RunStore.RunInfo> array) {
        this.array = array;
    }

    /**
     * Steps to do K-way merge: - Add first block of each 8 runs to 8 blocks MinHeap
     * - then remove Min and write Min to output File. - Check if there are any run
     * is exhausted then move to next block of that run. - the run is exhausted when
     * the file pointer position reaches the start of the next run
     * 
     * @param sourceFile is name of file
     */
    public void multiwayMerge(String sourceFile) throws IOException {
        File source = new File(sourceFile);
        File result = new File("mergeOut.bin");
        FileReader converter;
        int runcount = 0;
        RandomAccessFile resultFile;
        while (array.size() > 1) {
            converter = new FileReader(source);
            resultFile = new RandomAccessFile(result, "rw");
            runner.assignRConverter(converter);
            Heap<Record> minHeap = new Heap<Record>(new Record[4096], 4096);
            ArrayList<Integer> recordAmmount = new ArrayList<Integer>();
            for (int i = 0; i < array.size(); i++) {
                recordAmmount.add(-1);
            }
            while (runcount < array.size()) {

                while (!(runcount >= array.size() || runcount >= runcount + 8)) {
                    byte[] currBlock = array.get(runcount).extractRun();
                    int i = 0;
                    while (i < currBlock.length) {
                        minHeap.insert(new Record(Arrays.copyOfRange(currBlock, i, i + 16), runcount));
                        i += 16;
                    }
                    int recnum = currBlock.length / 16;
                    recordAmmount.set(runcount, recnum);
                    runcount++;
                }

                do {
                    Record record = (Record) minHeap.removeMin();
                    resultFile.write(record.getCompleteRecord());
                    int idx = record.getCurrentRun();
                    int value = recordAmmount.get(idx);
                    recordAmmount.set(idx, value - 1);
                    // check exhausted input
                    int checker = -1;
                    for (int i = 0; i < recordAmmount.size(); i++) {
                        if (recordAmmount.get(i) == 0) {
                            checker = i;
                        } else {
                            checker = -1;
                        }
                    }
                    if (checker != -1) {
                        byte[] currBlock = array.get(checker).extractRun();
                        if (currBlock == null) {
                            recordAmmount.set(checker, -1);
                        } else {
                            int i = 0;
                            while (i < currBlock.length) {
                                minHeap.insert(new Record(Arrays.copyOfRange(currBlock, i, i + 16), checker));
                                i += 16;
                            }
                            int recnum = currBlock.length / 16;
                            recordAmmount.set(checker, recnum);
                        }
                    }
                } while (minHeap.heapsize() > 0);

            }
            converter.closeFile();
            resultFile.close();
            Files.move(result.toPath(), result.toPath().resolveSibling(sourceFile),
                    StandardCopyOption.REPLACE_EXISTING);
            runcount = 0;
            runner = new RunStore(sourceFile);
            ArrayList<RunStore.RunInfo> newarr = runner.getAllRun();
            array = newarr;
        }
    }

}

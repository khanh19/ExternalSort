import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;

//On my honor:
//
//- I have not used source code obtained from another student,
//or any other unauthorized source, either modified or
//unmodified.
//
//- All source code and documentation used in my program is
//either my original work, or was derived by me from the
//source code published in the textbook for this course.
//
//- I have not discussed coding details about this project with
//anyone other than my partner (in the case of a joint
//submission), instructor, ACM/UPE tutors or the TAs assigned
//to this course. I understand that I may discuss the concepts
//of this program with other students, and that another student
//may help me debug my program so long as neither of us writes
//anything during the discussion or modifies any computer file
//during the discussion. I have violated neither the spirit nor
//letter of this restriction.
/**
 * the way merge class
 * 
 * @author Quoc Cuong Pham
 * @author Khanh Pham
 * @version 11/17/2021
 *
 */
public class MultiwayMerge {
    private RunStore runner;
    private ArrayList<RunStore.RunInfo> array;

    /**
     * the construtor
     * 
     * @param sourceFile source file
     * @param run        the run store
     * @param list       the list of run
     */
    public MultiwayMerge(String sourceFile, 
            RunStore run, ArrayList<RunStore.RunInfo> list) {
        this.runner = run;
        this.array = list;
    }

    /**
     * get runner method
     * 
     * @return the runner
     */
    public RunStore getRunner() {
        return this.runner;
    }

    /**
     * set runner method
     * 
     * @param runner the runner
     */
    public void setRunner(RunStore runner) {
        this.runner = runner;
    }

    /**
     * the array list run
     * 
     * @return the list of run
     */
    public ArrayList<RunStore.RunInfo> getArray() {
        return this.array;
    }

    /**
     * set array of run
     * 
     * @param array the list of run
     */
    public void setArray(ArrayList<RunStore.RunInfo> array) {
        this.array = array;
    }

    /**
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

                while (!(runcount >= array.size() 
                        || runcount >= runcount + 8)) {
                    byte[] currBlock = array.get(runcount).extractRun();
                    int i = 0;
                    while (i < currBlock.length) {
                        minHeap.insert(new Record(Arrays.
                                copyOfRange(currBlock, 
                                        i, i + 16), runcount));
                        i += 16;
                    }
                    int recnum = currBlock.length / 16;
                    recordAmmount.set(runcount, recnum);
                    runcount++;
                }

                while (minHeap.heapsize() > 0) {
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
                        } 
                        else {
                            checker = -1;
                        }
                    }
                    if (checker != -1) {
                        byte[] currBlock = array.get(checker).extractRun();
                        if (currBlock == null) {
                            recordAmmount.set(checker, -1);
                        } 
                        else {
                            int i = 0;
                            while (i < currBlock.length) {
                                minHeap.insert(new Record(Arrays.
                                        copyOfRange(currBlock, 
                                                i, i + 16), checker));
                                i += 16;
                            }
                            int recnum = currBlock.length / 16;
                            recordAmmount.set(checker, recnum);
                        }
                    }
                }

            }
            converter.closeFile();
            resultFile.close();
            Files.move(result.toPath(), 
                    result.toPath().resolveSibling(sourceFile),
                    StandardCopyOption.REPLACE_EXISTING);
            runcount = 0;
            runner = new RunStore(sourceFile);
            ArrayList<RunStore.RunInfo> newarr = runner.getAllRun();
            array = newarr;
        }
    }

}

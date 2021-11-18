import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;

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

    public void multiwayMerge(String sourceFile) {
        File source = new File(sourceFile);
        File result = new File("mergeOut.bin");
        FileReader converter;
        RandomAccessFile resultFile;

    }

}

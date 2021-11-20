import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class RunStore {
    public class RunInfo {
        private FileReader converter;
        private int current;
        private int beginOff;
        private int closeOff;

        public RunInfo() {
            this.beginOff = -1;
            this.current = beginOff;
            this.closeOff = -1;
        }

        public RunInfo(int beginOff, int closeOff) {
            this.beginOff = beginOff;
            this.closeOff = closeOff;
            this.current = beginOff;

        }

        public FileReader getReader() {
            return this.converter;
        }

        public int getCurr() {
            return this.current;
        }

        public int getCloseOff() {
            return this.closeOff;
        }

        public int getBeginOff() {
            return this.beginOff;
        }

        public void setReader(FileReader converter) {
            this.converter = converter;
        }

        public byte[] extractRun() {
            byte[] currBlock;
            if (this.getCurr() == this.getCloseOff()) {
                return null;
            } else if (this.getCurr() + 8192 > this.getCloseOff()) {
                int nSize = this.getCloseOff() - this.getCurr();
                currBlock = new byte[nSize];
                try {
                    converter.parseRange(currBlock, current, closeOff);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                current = closeOff;
            } else {
                currBlock = new byte[8192];
                int upper = current + 8192;
                try {
                    converter.parseRange(currBlock, current, upper);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                current = current + 8192;
            }
            return currBlock;
        }

    }

    private ArrayList<RunInfo> runStore;
    private String intput;

    public RunStore(String input) {
        runStore = new ArrayList<RunInfo>();
        this.intput = input;
    }

    public ArrayList<RunInfo> getRunStore() {
        return this.runStore;
    }

    public void setRunStore(ArrayList<RunInfo> runStore) {
        this.runStore = runStore;
    }

    public String getIntput() {
        return this.intput;
    }

    public void setIntput(String intput) {
        this.intput = intput;
    }

    public void assignRConverter(FileReader converter) {
        for (int i = 0; i < runStore.size(); i++) {
            runStore.get(i).setReader(converter);
        }
    }

    public ArrayList<RunInfo> getAllRun() throws IOException {
        try {
            FileReader convert = new FileReader(new File(this.getIntput()));
            Record oldRecord = null;
            int rPointer = 0;
            int lPointer = 0;
            byte[] currBlock;
            while (convert.hasNext()) {
                currBlock = convert.next();
                for (int i = 0; i < currBlock.length; i += 16) {
                    Record currRecord = new Record(Arrays.copyOfRange(currBlock, i, i + 16));
                    if (oldRecord != null ) {
                        if (oldRecord.compareTo(currRecord) > 0 ) {
                            runStore.add(new RunStore.RunInfo(lPointer, rPointer));
                            lPointer = rPointer;
                        }
                    }
                    rPointer += 16;
                    oldRecord = currRecord;
                }
            }
            runStore.add(new RunStore.RunInfo(lPointer, rPointer));
            lPointer = rPointer;
            convert.closeFile();
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        return runStore;

    }

}

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.
/**
 * The class containing all run
 *
 * @author Quoc Cuong Pham
 * @author Khanh Pham
 * @version 11/17/2021
 */
public class RunStore {

    /**
     * main class
     * 
     * @author Quoc Cuong Pham
     * @author Khanh Pham
     *
     */
    public class RunInfo {
        private FileReader converter;
        private int current;
        private int beginOff;
        private int closeOff;

        /**
         * the constructor
         */
        public RunInfo() {
            this.beginOff = -1;
            this.current = beginOff;
            this.closeOff = -1;
        }


        /**
         * helper function to read info
         * 
         * @param beginOff
         *            the begin info
         * @param closeOff
         *            the end info
         */
        public RunInfo(int beginOff, int closeOff) {
            this.beginOff = beginOff;
            this.closeOff = closeOff;
            this.current = beginOff;

        }


        /**
         * the get reader method
         * 
         * @return the next run
         */
        public FileReader getReader() {
            return this.converter;
        }


        /**
         * the helper function to get current
         * 
         * @return the currently current
         */
        public int getCurr() {
            return this.current;
        }


        /**
         * the helper function to get close off
         * 
         * @return the close off
         */
        public int getCloseOff() {
            return this.closeOff;
        }


        /**
         * the helper function to get the first close off
         * 
         * @return the close off
         */
        public int getBeginOff() {
            return this.beginOff;
        }


        /**
         * the function to set next reader
         * 
         * @param convert
         *            the converter
         */
        public void setReader(FileReader convert) {
            this.converter = convert;
        }


        /**
         * the function to extract run
         * 
         * @return the byte of run
         */
        public byte[] extractRun() {
            byte[] currBlock;
            if (this.getCurr() == this.getCloseOff()) {
                return null;
            }
            else if (this.getCurr() + 8192 > this.getCloseOff()) {
                int nSize = this.getCloseOff() - this.getCurr();
                currBlock = new byte[nSize];
                try {
                    converter.parseRange(currBlock, current, closeOff);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                current = closeOff;
            }
            else {
                currBlock = new byte[8192];
                int upper = current + 8192;
                try {
                    converter.parseRange(currBlock, current, upper);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                current = current + 8192;
            }
            return currBlock;
        }

    }

    private ArrayList<RunInfo> run;
    private String intput;

    /**
     * the run store
     * 
     * @param input
     *            the string
     */
    public RunStore(String input) {
        run = new ArrayList<RunInfo>();
        this.intput = input;
    }


    /**
     * the helper function to get list run
     * 
     * @return the list of run
     */
    public ArrayList<RunInfo> getRunStore() {
        return this.run;
    }


    /**
     * the function to set run store
     * 
     * @param runOut
     *            the list of run
     */
    public void setRunStore(ArrayList<RunInfo> runOut) {
        this.run = runOut;
    }


    /**
     * the function to get next input
     * 
     * @return the next input
     */
    public String getIntput() {
        return this.intput;
    }


    /**
     * the function to set input
     * 
     * @param intput
     *            the input
     */
    public void setIntput(String intput) {
        this.intput = intput;
    }


    /**
     * the helper function of converter
     * 
     * @param converter
     *            the convert
     */
    public void assignRConverter(FileReader converter) {
        for (int i = 0; i < run.size(); i++) {
            run.get(i).setReader(converter);
        }
    }


    /**
     * the array list of all run
     * 
     * @return the list of run
     * @throws IOException
     *             throw exception when wrong
     */
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
                    Record currRecord = new Record(Arrays.copyOfRange(currBlock,
                        i, i + 16));
                    if (oldRecord != null) {
                        if (oldRecord.compareTo(currRecord) > 0) {
                            run.add(new RunStore.RunInfo(lPointer, rPointer));
                            lPointer = rPointer;
                        }
                    }
                    rPointer += 16;
                    oldRecord = currRecord;
                }
            }
            run.add(new RunStore.RunInfo(lPointer, rPointer));
            lPointer = rPointer;
            convert.closeFile();
        }
        catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        return run;

    }

}

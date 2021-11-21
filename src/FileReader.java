import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
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
 * This class will help to read file
 * 
 * @author Quoc Cuong Pham
 * @author Khanh Pham
 * @version 11/17/2021
 *
 */
public class FileReader {
    private RandomAccessFile inputFile;
    private int totalByte;
    private long off;

    /**
     * This is the constructor
     * 
     * @param input
     *            the input file
     * @throws FileNotFoundException
     *             throw exception when it's wrong
     */
    public FileReader(File input) throws FileNotFoundException {
        this.inputFile = new RandomAccessFile(input, "rw");
        this.off = 0;
    }


    /**
     * this function will help to check if next block exist
     * 
     * @return the byte
     * @throws IOException
     *             throw exception when it's wrong
     */
    public byte[] next() throws IOException {
        byte[] currBlock;
        if (totalByte == -1) {
            System.out.println("End of this file has been reached.");
            return null;
        }
        else if (off + 8192 > inputFile.length()) {
            long nSize = inputFile.length() - off;
            currBlock = new byte[Integer.parseInt(Long.toString(nSize))];
            totalByte = inputFile.read(currBlock);
            off += totalByte;
        }
        else {
            currBlock = new byte[8192];
            totalByte = inputFile.read(currBlock);
            off += totalByte;
        }
        return currBlock;
    }


    /**
     * this is a helper function to read the first record in each block
     * 
     * @param source
     *            the file source
     * @throws IOException
     *             throw exception when it's done
     */
    public static void firstRecEachBlock(String source) throws IOException {
        FileReader reader = new FileReader(new File(source));
        byte[] currBlock;
        String str = "";
        int recCount = 0;
        while (reader.hasNext()) {
            currBlock = reader.next();
            Record rec = new Record(Arrays.copyOfRange(currBlock, 0, 16));
            recCount++;
            if (recCount % 5 == 0) {
                str += rec.toString() + "\n";
            }
            else {
                str += rec.toString() + " ";
            }

        }
        reader.closeFile();
        System.out.println(str);

    }


    /**
     * This function to check if next record exist
     * 
     * @return true if it's right
     * @throws IOException
     *             throw exception when it's wrong
     */
    public boolean hasNext() throws IOException {
        if (totalByte == -1) {
            System.out.println("End of this file has been reached.");
            return false;
        }
        return off != inputFile.length();
    }


    /**
     * This function to parse next record
     * 
     * @param block
     *            the next block
     * @param start
     *            position start
     * @param end
     *            position end
     * @throws IOException
     *             throw exception when it's wrong
     */
    public void parseRange(byte[] block, int start, int end)
        throws IOException {
        inputFile.seek(start);
        inputFile.read(block, 0, block.length);

    }


    /**
     * helper function
     * 
     * @return the next file
     */
    public RandomAccessFile getInputFile() {
        return this.inputFile;
    }


    /**
     * helper function to set up input file
     * 
     * @param inputFile
     *            the next file
     */
    public void setInputFile(RandomAccessFile inputFile) {
        this.inputFile = inputFile;
    }


    /**
     * get total byte
     * 
     * @return the total byte
     */
    public int getTotalByte() {
        return this.totalByte;
    }


    /**
     * this function to set next byte
     * 
     * @param totalByte
     *            the total byte
     */
    public void setTotalByte(int totalByte) {
        this.totalByte = totalByte;
    }


    /**
     * this function to get off
     * 
     * @return the off
     */
    public long getOff() {
        return this.off;
    }


    /**
     * this function to set off
     * 
     * @param off
     *            the off
     */
    public void setOff(long off) {
        this.off = off;
    }


    /**
     * the helper function
     * 
     * @throws IOException
     *             throw when wrong
     */
    public void closeFile() throws IOException {
        inputFile.close();
    }

}

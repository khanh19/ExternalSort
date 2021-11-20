import java.io.*;

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
 * the output buffer class
 * 
 * @author Quoc Cuong Pham
 * @author Khanh Pham
 * @version 11/17/2021
 *
 */
public class OutputBuffer {
    private RandomAccessFile randomfile;
    private byte[] buff;
    private int counter;

    /**
     * constructor
     * 
     * @throws FileNotFoundException throw when wrong
     */
    public OutputBuffer() throws FileNotFoundException {
        File file = new File("runFile.bin");
        this.randomfile = new RandomAccessFile(file, "rw");
        this.buff = new byte[8192];
        this.counter = 0;
    }

    /**
     * helper function
     * 
     * @return the random file
     */
    public RandomAccessFile getRandomfile() {
        return this.randomfile;
    }

    /**
     * helper function
     * 
     * @param randomfile the random file
     */
    public void setRandomfile(RandomAccessFile randomfile) {
        this.randomfile = randomfile;
    }

    /**
     * the helper function
     * 
     * @return the byte buff
     */
    public byte[] getBuff() {
        return this.buff;
    }

    /**
     * the helper function
     * 
     * @param buff set buff
     */
    public void setBuff(byte[] buff) {
        this.buff = buff;
    }

    /**
     * the helper function
     * 
     * @return the counter
     */
    public int getCounter() {
        return this.counter;
    }

    /**
     * the set counter function
     * 
     * @param counter the counter
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }

    /**
     * the function to add buff
     * 
     * @param record the record
     */
    public void addToOutBuff(byte[] record) {
        int k = 0;
        for (int i = counter; i < counter + 16; i++) {
            buff[i] = record[k];
            k++;
        }
        counter += 16;

    }

    /**
     * check if it is full
     * 
     * @return true if full
     */
    public boolean isFull() {
        return counter == 8192;
    }

    /**
     * check if it is empty
     * 
     * @return true if empty
     */
    public boolean isEmpty() {
        return counter == 0;
    }

    /**
     * the fill run file function
     * 
     * @throws IOException throw if wrong
     */
    public void fillRunFile() throws IOException {
        randomfile.write(buff, 0, counter);
        this.buff = new byte[8192];
        this.counter = 0;
    }

    /**
     * helper function
     * 
     * @throws IOException throw if wrong
     */
    public void closeFile() throws IOException {
        this.randomfile.close();
    }
}

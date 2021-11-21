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
 * the input buffer class
 * 
 * @author Quoc Cuong Pham
 * @author Khanh Pham
 * @version 11/17/2021
 *
 */
public class InputBuffer {
    private byte[] buff;
    private int counter;

    /**
     * constructor of the class
     * 
     * @param block
     *            the byte of block
     */
    public InputBuffer(byte[] block) {
        this.buff = block;
        this.counter = 0;
    }


    /**
     * this helper function
     * 
     * @return the buff byte
     */
    public byte[] getBuff() {
        return this.buff;
    }


    /**
     * this help set buff
     * 
     * @param buff
     *            the byte of buff
     */
    public void setBuff(byte[] buff) {
        this.buff = buff;
    }


    /**
     * this help counter
     * 
     * @return the counter
     */
    public int getCounter() {
        return this.counter;
    }


    /**
     * this help set counter
     * 
     * @param counter
     *            the counter
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }


    /**
     * this help to check next record
     * 
     * @return the next record
     */
    public byte[] nextRecord() {
        byte[] next = Arrays.copyOfRange(buff, counter, counter + 16);
        counter += 16;
        return next;
    }


    /**
     * this help to check next block
     * 
     * @param block
     *            the block
     */
    public void nextBlock(byte[] block) {
        this.setBuff(block);
        this.setCounter(0);
    }


    /**
     * check if it is empty
     * 
     * @return true if empty
     */
    public boolean isEmpty() {
        return (this.buff == null || this.counter == 8192);

    }

}

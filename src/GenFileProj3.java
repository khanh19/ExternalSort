// WARNING: This program uses the Assertion class. When it is run,
// assertions must be turned on. For example, under Linux, use:
// java -ea Genfile

import java.io.*;
import java.util.*;
import java.math.*;

/**
 * this function to help generate input bin
 * 
 * @author Quoc Cuong Pham
 * @author Khanh Pham
 * @version 11/17/2021
 *
 */
public class GenFileProj3 {
    /**
     * static variable
     */
    static final int NUMRECS = 512; // Because they are short ints

    /**
     * static variable
     */
    static private Random value = new Random(); // Hold the Random class object

    /**
     * static variable
     * 
     * @return the next value
     */
    static long randLong() {
        return value.nextLong();
    }


    /**
     * static variable
     * 
     * @return the next value
     */
    static double randDouble() {
        return value.nextDouble();
    }


    /**
     * main function to generate
     * 
     * @param args
     *            the file
     * @throws IOException
     *             throw exception when wrong
     */
    public static void main(String[] args) throws IOException {
        long val;
        double val2;
        assert (args.length == 2) : "\nUsage: Genfile <filename> <size>"
            + "\nOptions \nSize is measured in blocks of 8192 bytes";

        int filesize = Integer.parseInt(args[1]); // Size of file in blocks
        DataOutputStream file = new DataOutputStream(new BufferedOutputStream(
            new FileOutputStream(args[0])));

        for (int i = 0; i < filesize; i++) {
            for (int j = 0; j < NUMRECS; j++) {
                val = (long)(randLong());
                file.writeLong(val);
                val2 = (double)(randDouble());
                file.writeDouble(val2);
            }
        }

        file.flush();
        file.close();
    }

}

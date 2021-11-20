import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * this project will sort the data
 */

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
 * The class containing the main method.
 *
 * @author Quoc Cuong Pham
 * @author Khanh Pham
 * @version 11/17/2021
 */
public class Externalsort {

    /**
     * @param args Command line parameters
     */
    public static void main(String[] args) throws IOException {
        ReplacementSelection rep = new ReplacementSelection(new File(args[0]));
        rep.sort();
        RunStore store = new RunStore(args[0]);
        ArrayList<RunStore.RunInfo> array = store.getAllRun();
        MultiwayMerge merger = new MultiwayMerge(args[0], store, array);
        merger.multiwayMerge(args[0]);
        printFile(args[0], args[1]);
        firstRecEachBlock(args[0]);

    }

    /**
     * this helper function will print the record output into some file
     * 
     * @param source
     * @param outputFile
     * @throws IOException
     */
    private static void printFile(String source, 
            String outputFile) throws IOException {
        FileWriter outFile = new FileWriter(outputFile);
        FileReader reader = new FileReader(new File(source));
        byte[] currBlock;
        while (reader.hasNext()) {
            currBlock = reader.next();
            for (int i = 0; i < currBlock.length; i += 16) {
                Record rec = new Record(Arrays.
                        copyOfRange(currBlock, i, i + 16));
                outFile.write(rec.toString() + "at off: " + i + " \n");
            }
        }
        reader.closeFile();
        outFile.close();
    }

    /**
     * this is a helper function to read the first record in each block
     * 
     * @param source the file source
     * @throws IOException throw exception when it's done
     */
    private static void firstRecEachBlock(String source) throws IOException {
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

}

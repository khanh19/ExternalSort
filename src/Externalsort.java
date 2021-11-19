import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

/**
 * this project will sort the data
 */

/**
 * The class containing the main method.
 *
 * @author Quoc Cuong Pham
 * @author Khanh Pham
 * @version 11/17/2021
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

public class Externalsort {

	/**
	 * @param args Command line parameters
	 */
	public static void main(String[] args) throws IOException {
		dumpFile(args[0], args[1]);
		FileWriter writer = new FileWriter(new File(args[1]));
		replacementSelection rep = new replacementSelection(writer, new File(args[0]));
		rep.sort();
		dumpFile(args[0], args[2]);
	}

	private static void dumpFile(String source, String outputFile) throws IOException {
		FileWriter writer = new FileWriter(outputFile);
		FileReader parser = new FileReader(new File(source));
		byte[] block;
		while (parser.hasNext()) {
			block = parser.next();
			for (int i = 0; i < block.length; i += 16) {
				Record rec = new Record(Arrays.copyOfRange(block, i, i + 16));
				writer.write(rec.getRecId() + " " + rec.getKey() + "at off: " + i + " \n");
			}
		}
		parser.closeFile();
		writer.close();
	}

}

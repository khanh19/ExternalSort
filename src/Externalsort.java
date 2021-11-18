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
		File source = new File(args[0]);
		FileReader dataParser = new FileReader(source);
		FileWriter writer = new FileWriter(new File(args[1]));

		byte[] block;
		Record[] list = new Record[4096];
		Heap<Record> record = new Heap<Record>(list, 4096);
		int count = 0;
		while (dataParser.hasNext() && count <= 8) {
			block = dataParser.next();
			if(record.heapsize() >= 4096) {
				break;
			}
			System.out.println(record.heapsize());
			for (int i = 0; i < block.length; i += 16) {
				Record rec = new Record(Arrays.copyOfRange(block, i, i + 16));
				record.insert(rec);
			}
			count++;
		}
		
		
		
		int len = 0;
		int check = record.heapsize();
		for (int i = 0; i < check; i++) {
			Record rec = record.removeMin();
			writer.write(rec.getRecId() + " " + rec.getKey() + "\n");
			len++;

		}
		System.out.println(len);

		dataParser.closeFile();
		writer.close();

	}

}

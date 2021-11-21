import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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
 * replacement selection test
 *
 * @author Quoc Cuong Pham
 * @author Khanh Pham
 * @version 11/17/2021
 */
public class ReplacementSelectionTest extends student.TestCase {
    private ReplacementSelection replace;

    /**
     * this is a constructor
     */
    public void setUp() throws FileNotFoundException {
        File raf1 = new File("sampleInput16.bin");
        replace = new ReplacementSelection(raf1);
    }

    /**
     * test method
     * 
     * @throws IOException
     */
    public void testSort() throws IOException {
        assertNotNull(replace);
        replace.sort();

    }

}
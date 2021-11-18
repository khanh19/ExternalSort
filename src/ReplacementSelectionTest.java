import java.io.FileNotFoundException;

import student.TestCase;

public class ReplacementSelectionTest extends TestCase {
	private replacementSelection check;
	private byte[] aBite;
	private OutputBuffer outfile;

	public void setup() throws FileNotFoundException {
		outfile = new OutputBuffer();
		check = new replacementSelection(aBite, outfile);
	}

}

import java.io.IOException;

import student.TestCase;

/**
 * @author Quoc Cuong Pham
 * @author Khanh Pham
 * @version 11/17/2021
 */
public class ExternalsortTest extends TestCase {
    private Externalsort sorter;

    /**
     * set up for tests
     */
    public void setUp() {
        sorter = new Externalsort();
    }

    /**
     * Get code coverage of the class declaration.
     * 
     * @throws IOException throw exception
     */
    @SuppressWarnings({ "static-access" })
    public void testExternalsortInit() throws IOException {
        String[] string = new String[1];
        string[0] = "sampleInput16.bin";
        sorter.main(string);
        assertNotNull(sorter);
    }

}

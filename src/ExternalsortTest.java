import java.io.IOException;

import student.TestCase;

/**
 * @author {Your Name Here}
 * @version {Put Something Here}
 */
public class ExternalsortTest extends TestCase {

    /**
     * set up for tests
     */
    public void setUp() {
        // nothing to set up.
    }

    /**
     * Get code coverage of the class declaration.
<<<<<<< HEAD
     * 
     * @throws IOException
=======
     * @throws IOException 
>>>>>>> 973354c5513e04b294575b3c9a48e0487c82ea48
     */
    public void testExternalsortInit() throws IOException {
        Externalsort sorter = new Externalsort();
        assertNotNull(sorter);
        Externalsort.main(null);
    }

}

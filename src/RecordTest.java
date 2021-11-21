
import java.nio.ByteBuffer;
import student.TestCase;

/**
 * Holds a single record
 * 
 * @author CS Staff
 * @version 2020-10-15
 */
public class RecordTest extends TestCase {

    private byte[] aBite;
    private byte[] aBite2;

    /**
     * The setup for the tests
     */
    public void setUp() {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES + Double.BYTES);
        buffer.putLong(7);
        buffer.putDouble(8, 1);
        aBite = buffer.array();
        aBite2 = buffer.array();
    }

    /**
     * Tests the first constructor
     */
    public void testConstruct1() {
        Record rec = new Record(aBite);
        Record rec2 = new Record(aBite2, 5);
        assertEquals((double) 1, rec.getKey(), 0.00);
        assertTrue(rec.toString().equals("7 1.0"));
        assertTrue(rec2.toString().equals("7 1.0"));
        assertEquals(rec2.getCurrentRun(), 5);
        rec2.setCurrentRun(6);
        assertEquals(rec2.getCurrentRun(), 6);
    }

    /**
     * Tests the first constructor
     */
    public void testCompareTo() {
        Record rec = new Record(aBite);
        Record recToBeCompared = new Record(aBite);
        assertEquals(rec.compareTo(recToBeCompared), 0);
    }

}

import student.TestCase;
import java.nio.ByteBuffer;

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
 * the heap test class
 * 
 * @author Quoc Cuong Pham
 * @author Khanh Pham
 * @version 11/17/2021
 *
 */
public class HeapTest extends TestCase {
    private Heap<Record> heap;
    private byte[] aBite;
    private byte[] aBite2;
    private Heap<Integer> heap2;
    private Heap<Integer> heap3;

    /**
     * The setup for the tests
     */
    public void setUp() {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES + Double.BYTES);
        ByteBuffer buffer2 = ByteBuffer.allocate(Long.BYTES + Double.BYTES);
        buffer.putLong(0, 7);
        buffer.putDouble(8, 5);
        aBite = buffer.array();
        buffer2.putLong(1, 7);
        buffer2.putDouble(7, 6.9);
        aBite2 = buffer2.array();

        Record[] list = new Record[20];
        heap = new Heap<Record>(list, 6);

        Integer[] numbers = new Integer[4140];
        heap2 = new Heap<Integer>(numbers, 4140);
        Integer[] numbers2 = new Integer[5];
        heap3 = new Heap<Integer>(numbers2, 5);

    }

    /**
     * test heap insert
     */
    public void testInsert() {
        heap2.insert(1);
        heap2.insert(5);
        heap2.insert(7);
        heap2.insert(2);
        heap2.insert(20);
        heap2.insert(50);
        heap2.insert(11);
        heap2.insert(17);
        heap2.insert(100);
        heap2.insert(40);
        heap2.insert(9);
        heap2.insert(70);
        assertEquals(12, heap2.heapsize());
        assertEquals("1", heap2.getRoot().toString());
        heap2.swapFirst();
        heap2.siftdown(0);
        assertEquals("2", heap2.getRoot().toString());
        heap2.removeMin();
        assertEquals("5", heap2.getRoot().toString());
        heap3.insert(1);
        heap3.insert(5);
        heap3.insert(7);
        heap3.insert(2);
        heap3.insert(20);
        Exception exception = null;
        try {
            heap3.insert(8);
        } 
        catch (ArrayIndexOutOfBoundsException e) {
            exception = e;
        }
        assertNotNull(exception);

    }

    /**
     * test function
     */
    public void test() {
        Record record = new Record(aBite);
        Record record2 = new Record(aBite2);
        heap.insert(record);
        heap.insert(record2);

        assertEquals("64 1.0107936529880631E-175", 
                heap.getRecord(0).toString());

    }
}

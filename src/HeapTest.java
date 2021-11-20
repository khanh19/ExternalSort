import student.TestCase;
import java.nio.ByteBuffer;
import java.util.Random;

public class HeapTest extends TestCase {
	private Record[] list;
	private Heap<Record> heap;
	private Heap<int> heap2;

	private byte[] aBite;
	private byte[] aBite2;

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

		list = new Record[20];
		heap = new Heap<Record>(list, 6);

	}

	public void test() {
		Record record = new Record(aBite);
		Record record2 = new Record(aBite2);
		heap.insert(record);
		heap.insert(record2);
		System.out.println(list[0].getKey());
		System.out.println(list[1].getKey());
		System.out.println(list[0].getKey());
		System.out.println(list[1].getKey());

	}
}

import java.io.File;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.LinkedList;

/**
 * this class will do the replacement Selection
 * 
 * @author Quoc Cuong Pham
 * @author Khanh Pham
 *
 */
public class replacementSelection {

	public replacementSelection(byte[] input, OutputBuffer outfile) {
		InputBuffer b = new InputBuffer(input);

		Record[] list = new Record[4096];

		Heap<Record> heap = new Heap<Record>(list, 4096);

		int count = 0;

		while (b.nextRecord() != null) {

			heap.insert(new Record(b.nextRecord()));

		}
		
		do {
			while (heap.heapsize() != 0) {
				Record record = heap.removeMin();
				outfile.addToOutBuff(record.getCompleteRecord());
				if (!b.isEmpty()) {
					Record next = new Record(b.nextRecord());
					if (next.compareTo(record) >= 0) {
						heap.insert(next);
					} else {
						list[count] = next;
					}

				}
			}
		} while (heap.heapsize() != 0);
		}

	}
}

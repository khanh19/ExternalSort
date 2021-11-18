import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * this class will do the replacement Selection
 * 
 * @author Quoc Cuong Pham
 * @author Khanh Pham
 *
 */
public class replacementSelection {
	private OutputBuffer outfile;
	private Heap<Record> heap;
	private FileWriter writer;

	public replacementSelection(OutputBuffer outfile, Heap<Record> heap, FileWriter writer) {
		this.outfile = outfile;
		this.heap = heap;
		this.writer = writer;
	}

	/**
	 * this class will take input and do replacement selection to the output buffer
	 * 
	 * @param input   the byte
	 * @param outfile the output buffer
	 * @throws IOException
	 */
	public void sort(byte[] input) throws IOException {
		InputBuffer b = new InputBuffer(input);

		LinkedList<Record> list = new LinkedList<Record>();

		int count = 0;
		if (heap.heapsize() < 4096) {
			for (int i = 0; i < input.length; i += 16) {
				Record rec = new Record(Arrays.copyOfRange(input, i, i + 16));
				heap.insert(rec);

			}
		} else {

			do {
				while (heap.heapsize() != 0) {
					Record record = heap.removeMin();
					writer.write(record.getRecId() + " " + record.getKey() + "\n");
					if (!b.isEmpty()) {
						Record next = new Record(b.nextRecord());
						if (next.compareTo(record) >= 0) {
							heap.insert(next);
						} else {
							writer.write(next.getRecId() + " " + next.getKey() + "\n");
						}

					}
				}
			} while (heap.heapsize() != 0);
		}
//		} else {
//			int check = heap.heapsize();
//			for (int i = 0; i < check; i++) {
//				Record rec = heap.removeMin();
//				writer.write(rec.getRecId() + " " + rec.getKey() + "\n");
//			}
//		}

	}

}

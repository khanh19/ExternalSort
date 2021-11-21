import java.io.File;
import java.io.FileNotFoundException;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
//import java.util.Arrays;
import java.util.Arrays;

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
 * 
 * 
 * @author Quoc Cuong Pham
 * @author Khanh Pham
 * @version 11/17/2021
 *
 */
public class ReplacementSelection {
    private OutputBuffer outfile;
    private Heap<Record> heap;
    private File sourceFile;

    /**
     * the constructor
     * 
     * @param sourceFile
     *            the source file
     * @throws FileNotFoundException
     *             throw exception when it's wrong
     */
    public ReplacementSelection(File sourceFile) throws FileNotFoundException {
        outfile = new OutputBuffer();
        heap = new Heap<Record>(new Record[4096], 4096);
        this.sourceFile = sourceFile;
    }


    /**
     * the sort function
     * 
     * @throws IOException
     *             throw exception when it's wrong
     */
    public void sort() throws IOException {
        FileReader parser = new FileReader(sourceFile);
        int count = 0;
        byte[] block;
        while (parser.hasNext() && count < 8) {
            block = parser.next();

            if (heap.heapsize() < 4096) {
                for (int i = 0; i < block.length; i += 16) {
                    Record rec = new Record(Arrays.copyOfRange(block, i, i
                        + 16));
                    heap.insert(rec);

                }
                count++;
            }
        }
        while (parser.hasNext()) {
            block = parser.next();
            InputBuffer b = new InputBuffer(block);
            while (!b.isEmpty()) {
                if (heap.heapsize() == 0) {
                    for (int i = 4095; i >=0; i--) {
                        heap.insert(heap.getRecord(i));
                    }
                }
                if (outfile.isEmpty()) {
                    Record record = heap.getRoot();

                    outfile.addToOutBuff(record.getCompleteRecord());
                    Record next = new Record(b.nextRecord());
                    heap.replacementRoot(next);
                    if (next.compareTo(record) < 0) {
                        heap.swapFirst();
                    }
                    heap.siftdown(0);

                }
                else {
                    Record record = heap.getRoot();

                    outfile.addToOutBuff(record.getCompleteRecord());
                    Record next = new Record(b.nextRecord());
                    heap.replacementRoot(next);
                    if (next.compareTo(record) < 0 && !outfile.isFull()) {
                        
                        heap.swapFirst();

                    }
                    heap.siftdown(0);
                }
                if (outfile.isFull()) {
                    outfile.fillRunFile();
                }
            }
        }
        int n = heap.heapsize();
        while (heap.heapsize() != 0) {
            Record rec = heap.removeMin();
            outfile.addToOutBuff(rec.getCompleteRecord());
            if (outfile.isFull()) {
                outfile.fillRunFile();
            }

        }
        if (heap.getRecord(4095) != null) {
            for (int i = 4095; i >= n; i--) {
                heap.insert(heap.getRecord(i));
            }
        }
        while (heap.heapsize() != 0) {

            Record rec = heap.removeMin();
            outfile.addToOutBuff(rec.getCompleteRecord());
            if (outfile.isFull()) {
                outfile.fillRunFile();
            }

        }

        outfile.closeFile();
        parser.closeFile();
        File nfile = new File("runFile.bin");
        Files.move(nfile.toPath(), nfile.toPath().resolveSibling(sourceFile
            .getName()), StandardCopyOption.REPLACE_EXISTING);
    }
}

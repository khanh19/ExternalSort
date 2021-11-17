import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileReader {
    private RandomAccessFile inputFile;
    private int totalByte;
    private long off;

    public FileReader(File input) throws FileNotFoundException {
        this.inputFile = new RandomAccessFile(input, "rw");
        this.off = 0;
    }

    public byte[] next() throws IOException {
        byte[] currBlock;
        if (totalByte == -1) {
            System.out.println("End of this file has been reached.");
            return null;
        } else if (off + 8192 > inputFile.length()) {
            long nSize = inputFile.length() - off;
            currBlock = new byte[Integer.parseInt(Long.toString(nSize))];
            totalByte = inputFile.read(currBlock);
            off += totalByte;
        } else {
            currBlock = new byte[8192];
            totalByte = inputFile.read(currBlock);
            off += totalByte;
        }
        return currBlock;
    }

    public boolean hasNext() throws IOException {
        if (totalByte == -1) {
            System.out.println("End of this file has been reached.");
            return false;
        }
        return totalByte != inputFile.length();

    }

    public void parseRange(byte[] block, int start, int end) throws IOException {
        inputFile.seek(start);
        inputFile.read(block, 0, block.length);

    }

    public RandomAccessFile getInputFile() {
        return this.inputFile;
    }

    public void setInputFile(RandomAccessFile inputFile) {
        this.inputFile = inputFile;
    }

    public int getTotalByte() {
        return this.totalByte;
    }

    public void setTotalByte(int totalByte) {
        this.totalByte = totalByte;
    }

    public long getOff() {
        return this.off;
    }

    public void setOff(long off) {
        this.off = off;
    }

    public void closeFile() throws IOException {
        inputFile.close();
    }

}

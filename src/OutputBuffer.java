import java.io.*;

public class OutputBuffer {
    private RandomAccessFile randomfile;
    private byte[] buff;
    private int counter;

    public OutputBuffer() throws FileNotFoundException {
        this.randomfile = new RandomAccessFile(new File("runFile.bin"), "rw");
        this.buff = new byte[8192];
        this.counter = 0;
    }

    public RandomAccessFile getRandomfile() {
        return this.randomfile;
    }

    public void setRandomfile(RandomAccessFile randomfile) {
        this.randomfile = randomfile;
    }

    public byte[] getBuff() {
        return this.buff;
    }

    public void setBuff(byte[] buff) {
        this.buff = buff;
    }

    public int getCounter() {
        return this.counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void addToOutBuff(byte[] record) {
        int k = 0;
        for (int i = counter; i < counter +16; i++) {
        	buff[i] = record[k];
        }
        counter += 16;
       
    }
    public boolean isFull() {
        return counter == 8192;
    }

    public void fillRunFile() throws IOException {
        randomfile.write(buff, 0, counter);
        this.buff = new byte[8192];
        this.counter = 0;
    }

}

import java.util.Arrays;

public class InputBuffer {
    private byte[] buff;
    private int counter;

    public InputBuffer(byte[] block) {
        this.buff = block;
        this.counter = 0;
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

    public byte[] nextRecord() {
        byte[] next = Arrays.copyOfRange(buff, counter, counter + 16);
        counter += 16;
        return next;
    }

    public void nextBlock(byte[] block) {
        this.setBuff(block);
        this.setCounter(0);
    }

    public boolean isEmpty() {
        if (this.buff == null || this.counter == 8192) {
            return true;
        }
        return false;
    }

}

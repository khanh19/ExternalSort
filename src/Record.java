import java.nio.ByteBuffer;

/**
 * Holds a single record
 * 
 * @author CS Staff
 * @version 2020-10-15
 */
public class Record implements Comparable<Record> {

    private byte[] completeRecord;
    private int currentRun;

    /**
     * The constructor for the Record class
     * 
     * @param record The byte for this object
     */
    public Record(byte[] record) {
        this.completeRecord = record;
    }

    public Record(byte[] record, int numRun) {
        this.completeRecord = record;
        this.currentRun = numRun;
    }

    /**
     * returns the complete record
     * 
     * @return complete record
     */
    public byte[] getCompleteRecord() {
        return completeRecord;
    }

    /**
     * Returns the object's key
     * 
     * @return the key
     */
    public long getRecId() {
        ByteBuffer buff = ByteBuffer.wrap(completeRecord);
        return buff.getLong(0);
    }

    /**
     * Returns the object's key
     * 
     * @return the key
     */
    public double getKey() {
        ByteBuffer buff = ByteBuffer.wrap(completeRecord);
        return buff.getDouble(8);
    }

    /**
     * Compare Two Records based on their keys
     * 
     * @param o - The Record to be compared.
     * @return A negative integer, zero, or a positive integer as this employee is
     *         less than, equal to, or greater than the supplied record object.
     */
    @Override
    public int compareTo(Record toBeCompared) {
        return Double.compare(this.getKey(), toBeCompared.getKey());
    }

    /**
     * Outputs the record as a String
     * 
     * @return a string of what the record contains
     */
    public String toString() {
        return "" + this.getKey();
    }

    public int getCurrentRun() {
        return this.currentRun;
    }

    public void setCurrentRun(int currentRun) {
        this.currentRun = currentRun;
    }

}

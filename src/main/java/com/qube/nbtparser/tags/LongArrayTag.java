package main.java.com.qube.nbtparser.tags;

public class LongArrayTag extends Tag {
    /**
     * Value of the tag
     */
    private long[] content;
    private int index;

    /**
     * Creates a new ByteTag
     * @param name String name of the tag
     * @param count number of elements in array
     */
    public LongArrayTag(String name, int count) {
        super(name);
        content = new long[count];
        index = 0;
    }

    public void add(long value) {
        content[index] = value;
        index++;
    }

    /**
     * Return the value of the tag
     * @return byte value
     */
    public long[] getContents() {
        return this.content;
    }

    public String toString() {
        return String.format("TAG_Long_Array('%s'): [%d values]", this.getName(), this.content.length);
    }
}
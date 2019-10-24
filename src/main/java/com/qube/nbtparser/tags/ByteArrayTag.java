package main.java.com.qube.nbtparser.tags;

public class ByteArrayTag extends Tag {
    /**
     * Value of the tag
     */
    private byte[] content;

    /**
     * Creates a new ByteArrayTag
     * @param name String name of the tag
     * @param value byte[] value of the tag
     */
    public ByteArrayTag(String name, byte[] value) {
        super(name);
        this.content = value;
    }

    public ByteArrayTag(String name, int count) {
        super(name);
        this.content = new byte[count];
    }

    /**
     * Return the value of the tag
     * @return byte[] value
     */
    public byte[] getValue() {
        return content;
    }

    public String toString() {
        return String.format("TAG_Byte_Array('%s'): [%d values]", this.getName(), this.content.length);
    }
}

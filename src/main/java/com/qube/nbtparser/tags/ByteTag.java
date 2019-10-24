package main.java.com.qube.nbtparser.tags;

public class ByteTag extends Tag {
    /**
     * Value of the tag
     */
    private byte value;

    /**
     * Creates a new ByteTag
     * @param name String name of the tag
     * @param value byte value of the tag
     */
    public ByteTag(String name, byte value) {
        super(name);
        this.value = value;
    }

    /**
     * Return the value of the tag
     * @return byte value
     */
    public byte getValue() {
        return value;
    }

    public String toString() {
        return String.format("TAG_Byte('%s'): '%x'", this.getName(), this.getValue());
    }
}

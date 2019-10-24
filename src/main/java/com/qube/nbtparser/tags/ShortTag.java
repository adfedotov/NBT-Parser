package main.java.com.qube.nbtparser.tags;

public class ShortTag extends Tag {
    /**
     * Value of the tag
     */
    private final short value;

    /**
     * Creates a new ShortTag
     * @param name String name of the tag
     * @param value short value of the tag
     */
    public ShortTag(String name, short value) {
        super(name);
        this.value = value;
    }

    /**
     * Returns the value of the tag
     * @return short value
     */
    public short getValue() {
        return value;
    }

    public String toString() {
        return String.format("TAG_Short('%s'): '%d'", this.getName(), this.getValue());
    }
}

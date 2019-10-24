package main.java.com.qube.nbtparser.tags;

public class LongTag extends Tag {
    /**
     * Value of the tag
     */
    private final long value;

    /**
     * Creates a new LongTag
     * @param name String name of the tag
     * @param value long value of the tag
     */
    public LongTag(String name, long value) {
        super(name);
        this.value = value;
    }

    /**
     * Return the value of the tag
     * @return long value
     */
    public long getValue() {
        return value;
    }

    public String toString() {
        return String.format("TAG_Long('%s'): '%d'", this.getName(), this.getValue());
    }
}

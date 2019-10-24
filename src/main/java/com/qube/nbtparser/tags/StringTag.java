package main.java.com.qube.nbtparser.tags;

public class StringTag extends Tag {
    /**
     * Value of the tag
     */
    private String value;

    /**
     * Creates a new StringTag
     * @param name String name of the tag
     * @param value String value of the tag
     */
    public StringTag(String name, String value) {
        super(name);
        this.value = value;
    }

    /**
     * Return the value of the tag
     * @return String value
     */
    public String getValue() {
        return value;
    }

    public String toString() {
        return String.format("TAG_String('%s'): '%s'", this.getName(), this.getValue());
    }
}

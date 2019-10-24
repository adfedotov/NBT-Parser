package main.java.com.qube.nbtparser.tags;

public class IntTag extends Tag {
    /**
     * Value of the tag
     */
    private int value;

    /**
     * Creates a new IntTag
     * @param name String name of the tag
     * @param value int value of the tag
     */
    public IntTag(String name, int value) {
        super(name);
        this.value = value;
    }

    /**
     * Return the value of the tag
     * @return int value
     */
    public int getValue() {
        return value;
    }

    public String toString() {
        return String.format("TAG_Int('%s'): '%d'", this.getName(), this.getValue());
    }
}

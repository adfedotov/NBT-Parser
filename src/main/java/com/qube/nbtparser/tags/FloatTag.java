package main.java.com.qube.nbtparser.tags;

public class FloatTag extends Tag {
    /**
     * Value of the tag
     */
    private final float value;

    /**
     * Creates a new FloatTag
     * @param name String name of the tag
     * @param value float value of the tag
     */
    public FloatTag(String name, float value) {
        super(name);
        this.value = value;
    }

    /**
     * Return the value of the tag
     * @return float value
     */
    public float getValue() {
        return value;
    }

    public String toString() {
        return String.format("TAG_Float('%s'): '%f'", this.getName(), this.getValue());
    }
}

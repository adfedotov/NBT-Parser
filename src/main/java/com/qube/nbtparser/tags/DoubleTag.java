package main.java.com.qube.nbtparser.tags;

public class DoubleTag extends Tag {
    /**
     * Value of the tag
     */
    private double value;

    /**
     * Creates a new DoubleTag
     * @param name String name of the tag
     * @param value double value of the tag
     */
    public DoubleTag(String name, double value) {
        super(name);
        this.value = value;
    }

    /**
     * Return the value of the tag
     * @return double value
     */
    public double getValue() {
        return value;
    }

    public String toString() {
        return String.format("TAG_Double('%s'): '%f'", this.getName(), this.getValue());
    }
}

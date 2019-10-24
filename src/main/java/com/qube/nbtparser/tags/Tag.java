package main.java.com.qube.nbtparser.tags;

public class Tag {
    /**
     * Tag name
     */
    private final String name;

    /**
     * Creates a Tag
     * @param name
     */
    public Tag(String name) {
        this.name = name;
    }

    /**
     * Returns name of the tag
     * @return
     */
    public String getName() {
        return this.name;
    }
}

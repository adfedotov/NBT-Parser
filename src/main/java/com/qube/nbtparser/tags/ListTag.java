package main.java.com.qube.nbtparser.tags;

import java.util.LinkedList;
import java.util.List;

public class ListTag extends Tag {
    /**
     * Contents of the tag
     */
    private List<Tag> contents;

    /**
     * Creates a new ListTag
     * @param name
     */
    public ListTag(String name) {
        super(name);
        this.contents = new LinkedList<Tag>();
    }

    public void add(Tag item) {
        this.contents.add(item);
    }

    /**
     * Return the contents of the tag
     * @return List of contents
     */
    public List<Tag> getContents() {
        return contents;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("TAG_List('%s'):\n{", this.getName()));
        for (Tag tag : contents) {
            result.append(String.format("\n\t%s", tag.toString()));
        }
        result.append("\n}");
        return result.toString();
    }
}

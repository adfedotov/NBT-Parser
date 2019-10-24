package main.java.com.qube.nbtparser.tags;

import java.util.ArrayList;

public class CompoundTag extends Tag {
    private ArrayList<Tag> contents;

    public CompoundTag(String name) {
        super(name);
        this.contents = new ArrayList<>();
    }

    public void add(Tag item) {
        this.contents.add(item);
    }

    public ArrayList<Tag> getContents() {
        return this.contents;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("TAG_Compound('%s'):\n{", this.getName()));
        for (Tag tag : contents) {
            result.append(String.format("\n\t%s", tag.toString()));
        }
        result.append("\n}");

        return result.toString();
    }
}

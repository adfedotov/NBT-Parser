package main.java.com.qube.nbtparser.tags;

public class IntArrayTag extends Tag {
    /**
     * Value of the tag
     */
    private int[] content;
    private int index;

    /**
     * Creates a new ByteTag
     * @param name String name of the tag
     * @param count number of elements in array
     */
    public IntArrayTag(String name, int count) {
        super(name);
        content = new int[count];
        index = 0;
    }

    public void add(int value) {
        content[index] = value;
        index++;
    }

    /**
     * Return the value of the tag
     * @return byte value
     */
    public int[] getContents() {
        return this.content;
    }

    public String toString() {
        return String.format("TAG_Int_Array('%s'): [%d values]", this.getName(), this.content.length);
    }
}


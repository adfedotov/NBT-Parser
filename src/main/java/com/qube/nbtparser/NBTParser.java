/**
 * NBT File Specifications: https://wiki.vg/NBT
 */

package main.java.com.qube.nbtparser;

import main.java.com.qube.nbtparser.tags.*;

import java.io.*;
import java.util.Stack;

public class NBTParser {

    // Tag representations
    static final int END_TAG = 0,
            BYTE_TAG = 1,
            SHORT_TAG = 2,
            INT_TAG = 3,
            LONG_TAG = 4,
            FLOAT_TAG = 5,
            DOUBLE_TAG = 6,
            BYTE_ARRAY_TAG = 7,
            STRING_TAG = 8,
            LIST_TAG = 9,
            COMPOUND_TAG = 10,
            INT_ARRAY_TAG = 11,
            LONG_ARRAY_TAG = 12;

    private DataInputStream in;
    private Stack<CompoundTag> stack;
    private CompoundTag root;

    /**
     * Parser constructor that accepts String parameter
     *
     * @param file String path to file
     * @throws IOException
     */
    public NBTParser(String file) throws IOException {
        this.in = new DataInputStream(new FileInputStream(file));
        this.stack = new Stack<>();
    }

    /**
     * Parser constructor that accepts File instance parameter
     *
     * @param file File instance
     * @throws IOException
     */
    public NBTParser(File file) throws IOException {
        this.in = new DataInputStream(new FileInputStream(file));
        this.stack = new Stack<>();
    }

    /**
     * Parser constructor that accepts InputStream parameter
     *
     * @param is File instance
     * @throws IOException
     */
    public NBTParser(InputStream is) {
        this.in = new DataInputStream(is);
        this.stack = new Stack<>();
    }

    /**
     * Reads a tag from the DataInputStream, returns null if tag is not valid
     *
     * @param name
     * @param tagType
     * @return Tag
     * @throws IOException
     */
    private Tag readTag(String name, int tagType) throws IOException {
        int length;
        int count;
        switch (tagType) {
            case END_TAG:
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                return new EndTag();
            case BYTE_TAG:
                return new ByteTag(name, in.readByte());
            case SHORT_TAG:
                return new ShortTag(name, in.readShort());
            case INT_TAG:
                return new IntTag(name, in.readInt());
            case LONG_TAG:
                return new LongTag(name, in.readLong());
            case FLOAT_TAG:
                return new FloatTag(name, in.readFloat());
            case DOUBLE_TAG:
                return new DoubleTag(name, in.readDouble());
            case BYTE_ARRAY_TAG:
                length = in.readInt();
                byte[] byteArray = new byte[length];
                in.read(byteArray);
                return new ByteArrayTag(name, byteArray);
            case STRING_TAG:
                length = in.readShort();
                byte[] stringByteArray = new byte[length];
                in.read(stringByteArray);
                return new StringTag(name, new String(stringByteArray));
            case LIST_TAG: // TODO: add type to ListTag + String formatting
                ListTag tag = new ListTag(name);
                int innerType = in.read();
                count = in.readInt();

                for (int i = 0; i < count; i++) {
                    Tag newTag = readTag("None", innerType);
                    tag.add(newTag);
                }
                return tag;
            case COMPOUND_TAG: // TODO: add String Formatting
                CompoundTag newCompound = new CompoundTag(name);

                if (stack.isEmpty()) {
                    stack.push(newCompound);
                }

                int innerTagID;
                Tag innerTag;
                while ((innerTagID = in.read()) != END_TAG) {
                    innerTag = readTag(getTagNameFromStream(), innerTagID);
                    newCompound.add(innerTag);
                }

                if (!stack.isEmpty() && stack.peek() == newCompound) {
                    stack.peek().add(newCompound);
                }
                stack.push(newCompound);

                return newCompound;
            case INT_ARRAY_TAG: // TODO: Implement Int_Array_Tag
                count = in.readInt();
                for (int i = 0; i < count; i++) {

                }
                break;
            case LONG_ARRAY_TAG: // TODO: Implement Long_Array_Tag
                break;
            default:
                System.out.println("Invalid tag");
        }
        return null;
    }

    /**
     * Returns root of the NBT-FIle, has to be a CompoundTag
     *
     * @return CompoundTag
     */
    public Tag getRoot() {
        return this.root;
    }

    /**
     * Returns tag name from DataInputStream
     *
     * @return
     * @throws IOException
     */
    private String getTagNameFromStream() throws IOException {
        int nameLength = in.readShort();
        byte[] nameArray = new byte[nameLength];
        in.read(nameArray);
        return new String(nameArray);
    }

    public void parse() throws IOException {
        CompoundTag root = null;
        // TODO: HANDLING, Check whether Compound tag is present, handle error during parsing
        int type = in.read();
        String name = getTagNameFromStream();
        this.root = (CompoundTag) readTag(name, type);
    }

    // Example
    public static void main(String[] args) throws IOException {
        NBTParser nbt = new NBTParser("bigtest-uncompressed.nbt");

        nbt.parse();
        CompoundTag root = (CompoundTag) nbt.getRoot();

        System.out.println(root.toString());
    }
}

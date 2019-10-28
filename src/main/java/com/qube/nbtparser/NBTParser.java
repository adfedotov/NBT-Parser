/**
 * NBT File Specifications: https://wiki.vg/NBT
 */

package main.java.com.qube.nbtparser;

import main.java.com.qube.nbtparser.tags.*;

import java.io.*;
import java.util.Stack;

public class NBTParser {

    private DataInputStream in;
    private Stack<CompoundTag> stack;
    private CompoundTag root;

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
    private Tag readTag(String name, int tagType) throws IOException { // TODO: String formatting
        int length;
        int count;

        switch (tagType) {
            case TagConstants.END_TAG:
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                return new EndTag();

            case TagConstants.BYTE_TAG:
                return new ByteTag(name, in.readByte());

            case TagConstants.SHORT_TAG:
                return new ShortTag(name, in.readShort());

            case TagConstants.INT_TAG:
                return new IntTag(name, in.readInt());

            case TagConstants.LONG_TAG:
                return new LongTag(name, in.readLong());

            case TagConstants.FLOAT_TAG:
                return new FloatTag(name, in.readFloat());

            case TagConstants.DOUBLE_TAG:
                return new DoubleTag(name, in.readDouble());

            case TagConstants.BYTE_ARRAY_TAG:
                length = in.readInt();
                byte[] byteArray = new byte[length];
                in.read(byteArray);
                return new ByteArrayTag(name, byteArray);

            case TagConstants.STRING_TAG:
                length = in.readShort();
                byte[] stringByteArray = new byte[length];
                in.read(stringByteArray);
                return new StringTag(name, new String(stringByteArray));

            case TagConstants.LIST_TAG: // TODO: add type to ListTag + String formatting
                ListTag newListTag = new ListTag(name);
                int innerType = in.read();
                count = in.readInt();

                for (int i = 0; i < count; i++) {
                    Tag newTag = readTag("None", innerType);
                    newListTag.add(newTag);
                }
                return newListTag;

            case TagConstants.COMPOUND_TAG:
                CompoundTag newCompound = new CompoundTag(name);

                if (stack.isEmpty()) {
                    stack.push(newCompound);
                }

                int innerTagID;
                Tag innerTag;
                while ((innerTagID = in.read()) != TagConstants.END_TAG) {
                    innerTag = readTag(getTagNameFromStream(), innerTagID);
                    newCompound.add(innerTag);
                }

                if (!stack.isEmpty() && stack.peek() == newCompound) {
                    stack.peek().add(newCompound);
                }
                stack.push(newCompound);

                return newCompound;

            case TagConstants.INT_ARRAY_TAG:
                count = in.readInt();
                IntArrayTag newIntArrayTag = new IntArrayTag(name, count);
                for (int i = 0; i < count; i++) {
                    newIntArrayTag.add(in.readInt());
                }
                return newIntArrayTag;

            case TagConstants.LONG_ARRAY_TAG:
                count = in.readInt();
                LongArrayTag newLongArrayTag = new LongArrayTag(name, count);
                for (int i = 0; i < count; i++) {
                    newLongArrayTag.add(in.readLong());
                }
                return newLongArrayTag;

            default:
                throw new IOException("Invalid Tag");
        }
    }

    /**
     * Returns root of the NBT-FIle, has to be a CompoundTag
     *
     * @return CompoundTag
     */
    public CompoundTag getRoot() {
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

    /**
     * Parse the NBT stream
     *
     * @throws IOException
     */
    public void parse() throws IOException {
        int type = in.read();
        if (type != TagConstants.COMPOUND_TAG) throw new IOException("NBT contents must be enclosed in a CompoundTag");
        String name = getTagNameFromStream();
        this.root = (CompoundTag) readTag(name, type);
    }
}

package example;

import main.java.com.qube.nbtparser.NBTParser;
import main.java.com.qube.nbtparser.tags.CompoundTag;

import java.io.FileInputStream;
import java.io.IOException;

public class NBTParserExample {
    public static void main(String[] args) {
        FileInputStream fis = null;
        NBTParser nbt = null;

        try {
            fis = new FileInputStream("src/example/bigtest-uncompressed.nbt");
            nbt = new NBTParser(fis); // Accepts InputStream
            nbt.parse(); // Parse the stream
        } catch (IOException e) {
            e.printStackTrace();
        }

        CompoundTag root = nbt.getRoot(); // Get the root (CompoundTag) that holds all the other tags and data
        System.out.println(root.toString());
    }
}

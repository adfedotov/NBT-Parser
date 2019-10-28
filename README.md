## NBT-Parser

A parser for the Named Binary Tag (NBT) file format that is used by Minecraft for storing data. 
(Player data, Region/Chunk data, etc.).

```java
NBTParser nbt = new NBTParser(InputStream); 
nbt.parse(); // Throws IOException
```

After parsing, all the Tags are contained within the root (CompoundTag).

```java
CompoundTag root = nbt.getRoot();
root.getContents(); // Returns a list of contained tags
```
---

Specifications: https://minecraft.gamepedia.com/NBT_format
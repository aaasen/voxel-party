# File Structure


`Init`

* model/
    * `Model` 
    * structs/
        * `Block`
        * `Chunk`
        * `World`
        * generators/
        * blocks/
* view/
    * `View`
    * structs/
        * `VertexContainer`
        * `VertexBuffer`
    * util/
        * `Plane`
        * `Shaders`
* controller/
    * `Controller` 

---

## Block
The world of Voxel Party is made of Blocks. Every part of the landscape, from dirt and sand to water and leaves, are Blocks.

Block is meant to be a parent class, and should not be used directly. I've included a possible Block hierarchy below.

> * Block
   * TransparentBlock
      * Glass
      * Water
   * OpaqueBlock
      * Dirt
      * Sand

```java
class Block:
   Vector a, b

   public Block(Vector a):
      if a is not integer: throw IllegalArgumentException
      
      this.a = a;
      this.b = a.add((1, 1, 1))
```

### Tests
* should be integers (but in float form: 1.0)

---

## Chunk
Chunks are containers for blocks, usually 16x16 at the base and 128+ in height.

```java
class Chunk
   final static Vector dimensions = (16, 128, 16)
   
   Vector pos
   Block[][][] blocks
   Generator generator
   
   public Chunk(Generator generator):
      this.blocks = new Block[dimensions.x][dimensions.y][dimensions.z]
      this.generator = generator

   public Block getBlock(Vector pos):
      if pos.y is negative or
         pos.y is greater than dimensions.y or
         pos.x is out of bounds or
         pos.z is out of bounds:
         throw IllegalArgumentException
      
      return this.blocks[(int) dimensions.x][(int) dimensions.y][(int) dimensions.z]

```

### Tests
* check that out of bounds exceptions work

---

## World
The World (name pending), is a container for Chunks. Chunks should not usually be accessed directly, rather they should be accessed via the World.

```java
class World
   HashMap<Long, Chunk> chunks
   
   private Long getKey(Vector pos):
      return (int) pos.x * BIG_NUMBER + (int) pos.z
   
   public Chunk getChunk(Vector pos):
      Chunk chunk = chunks.get(getKey(pos))
      
      if (chunk == null):
         // generate it
      else:
         return chunk

   public Block getBlock(Vector pos):
      if pos.y out of bounds:
         thrown IllegalArgumentException
   
      return this.getChunk(pos).getBlock(pos)

```

### Tests
* check that getBlock() out of bounds works
* check that getKey() does not produce collisions in a reasonable range

---

## Generator
Generators fill Chunks with Blocks.

By making Generator an interface, biomes become much easier. There can be a Forest Generator, a Mountain Generator, an Ocean Generator, etc.
```java
interface Generator:
   public Chunk genChunk(Chunk chunk)

```

### Tests
* must fill every single block, no null values


---

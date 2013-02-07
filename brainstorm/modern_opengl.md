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

## Block
```java
class Block:
   Vector a, b

   public Block(Vector a):
      if not integer: throw IllegalArgumentException
      
      this.a = a;
      this.b = a.add((1, 1, 1))
```

### Tests
* should be integers (but in float form: 1.0)

## Chunk

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
      return this.blocks[(int) dimensions.x][(int) dimensions.y][(int) dimensions.z]

```

## World
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
      return this.getChunk(pos).getBlock(pos)

```

## Generator
```java
// Generators fill Chunks with Blocks
// we could have Generators for each biome, like Plains, Ocean, Mountains etc.
interface Generator:
   public Chunk genChunk(Chunk chunk)

```

### Tests
* must fill every single block, no null values

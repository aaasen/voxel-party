# File Structure

## MVC (Model View Controller)

> Model–view–controller (MVC) is a software architecture pattern that separates the representation of information from the user's interaction with it.
> The model consists of application data and business rules, and the controller mediates input, converting it to commands for the model or view.
> A view can be any output representation of data, such as a chart or a diagram.
> Multiple views of the same data are possible, such as a pie chart for management and a tabular view for accountants.
> The central ideas behind MVC are code reusability and separation of concerns.
>
> ![mvc explanation](http://upload.wikimedia.org/wikipedia/commons/f/fd/MVC-Process.png)
> 
> from Wikipedia, [*Model-view-controller*](http://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller)

I chose MVC for threading and modularity concerns.
However, I've heard that threading can get really ugly in games and isn't worth it.
Oh well! Current computers have at least 2 cores, my desktop has 6, and next gen consoles are rumored to have 8.
There's got to be some point in threading games.

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

## Init
Init reads a configuration file and launches the engine. In the future, it will do so via a proper graphical launcher.

```java
class Init:
   Map config = loadConfigFromFile("config.json")
   // config = { "screen_width" : 1920, "screen_height" : 1080 }
   
   // initialize LWJGL
   Display.create();
   Keyboard.create();
   Mouse.create();
   
   Model model = new Model(...)
   Controller controller = new Controller(model, ...)
   View view = new View(model, ...)
   
   model.init()
   view.init()
```

---

## Model
In an MVC framework, the model contains all data about the game world, players, etc.

Threading out the Model can create concurrent modification issues, which I try to solve by locking the Model when it is being updated.
Don't know how this will work out, though.
```java
class Model implements Runnable:
   World world
   Thread modelThread
   boolean locked
   
   public Model():
      this.modelThread = new Thread(this, "model_thread")
   	this.modelThread.start()
      
      this.world = new World()
      
   // updates the Model
   public void tick()
      this.locked = true
      // update the world
      this.locked = false
```

---

## View
In MVC, the View just draws the Model every frame.

The View is not threaded, as OpenGL must be used from a single thread.
```java
class View:
   VertexBuffer vertices

   public init():
      // initialize Shaders, etc.

   public run():
      while (!stop):
         this.render()
      
      this.cleanup()

   private render():
      glDrawArrays(vertices, ...)
      
      // render any 2D overlays

   private cleanup():
      // destroy the VertexBuffer and release GPU memory
```

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
class Chunk:
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
class World:
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

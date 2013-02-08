# Why Redesign?

I originally created Voxel Party during a weekend of fervent hacking.
I came into it with no knowledge of OpenGL, and tutorials taught me legacy methods.

Now, I'm modernizing VoxelParty. Updating it to OpenGL 3, and cleaning everything up in the process.

## MVC (Model View Controller)

Voxel Party uses an MVC model, which is explained below:

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

# Structural Overview

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

## Structural Specifics
I've include an explanation for `Init` below. It contains pseudocode, and a brief description.

Descriptions for other files can be found in `model.md` and `view.md`.

### Init
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

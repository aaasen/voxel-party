# View
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

## VertexContainer
VertexContainer is a temporary container for vertex data.
It is loaded into a container, then added to a buffer at which point the container is deleted.

Not sure if this is even necessary, actually.

---

## VertexBuffer
The VertexBuffer contains data for many vertices.
Ideally, the entire program would need just one.

### Concerns

> I have no idea how to do this dynamically right now, but it can be done.
Dynamically updating the VertexBuffer might conflict with threading.
OpenGL can only be used from one thread, and updating it would probably necessitate using it from the Model.

One possible solution would be to implement a message passing system in VertexBuffer.
`VertexBuffer.remove(VertexContainer)`, and similar `add()` methods.
This would also make VertexContainer non-temporary and require more memory.

---
   


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
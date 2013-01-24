
# How I screwed up implenting drawArrays

## Terms

### Immediate Rendering

In immediate rendering, no data is stored on the GPU. Instead, the data is loaded onto the GPU every single frame.
If you've just started with OpenGL, this is probably what you are using.

Example:

```java
glBegin(GL_TRIANGLES);

glColor3f(0, 0, 0);

glVertex3f(0, 0, 0);
glVertex3f(0, 1, 0);
glVertex3f(1, 1, 0);

glEnd();
```

### drawArrays

Immediate rendering requires data to be loaded onto the GPU every frame, which is inherently expensive.
The alternative is drawArrays, which are chunks of data that are loaded onto the GPU and kept there through multiple frames.

## How I screwed up

I started porting to drawArrays by using my old immediate rendering code.
I didn't really grasp the concept of drawArrays, and how they were much more static.
Now, I'm finally realizing that *a lot* of my rendering code is just wrong and unreadable.

## The new concept

I'm working on a new solution with a much better understanding of what I actually have to do.


 * `nexus/view/structs`
  * `VertexContainer.java`
  * `VertexBuffer.java`

### `VertexContainer`

`VertexContainer` is the parent class of any vertex in Voxel Party. It contains data for position, color, shaders, etc.
`VertexContainer` will be extended to `TriangleVertexConatiner`, `QuadVertexConatiner`, and the like.

Some pseudocode:

```java

class VertexContainer
  float[] vertices;
  float[] colors;
  

```

### `VertexBuffer`

`VertexBuffer` is the rendering container for `VertexConatiner`. 

I'm not sure how this will work in a dynamic context.

```java

class VertexBuffer
  FloatBuffer vertices
  boolean read = false
  
  put(VertexContainer)
    if (there is not room)
      expand the buffer
      
    write()
    vertices.put(VertexConatiner)
    read()
    
  write()
    if (read)
      vertices.flip()
      read = false
  
  read()
    if (!read)
      vertices.flip()
      read = true
  
```

package nexus.view.structs;

import static org.lwjgl.opengl.ARBBufferObject.GL_STATIC_DRAW_ARB;
import static org.lwjgl.opengl.ARBBufferObject.glBindBufferARB;
import static org.lwjgl.opengl.ARBBufferObject.glBufferDataARB;
import static org.lwjgl.opengl.ARBBufferObject.glDeleteBuffersARB;
import static org.lwjgl.opengl.ARBBufferObject.glGenBuffersARB;
import static org.lwjgl.opengl.ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB;
import static org.lwjgl.opengl.GL11.GL_COLOR_ARRAY;
import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_VERTEX_ARRAY;
import static org.lwjgl.opengl.GL11.glColorPointer;
import static org.lwjgl.opengl.GL11.glDisableClientState;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glEnableClientState;
import static org.lwjgl.opengl.GL11.glVertexPointer;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

public class VertexContainer {
	public float[] vertices;
	public FloatBuffer vertexBuffer;
	public int vertexCount;
	public int vertexId;

	public float[] colors;
	public FloatBuffer colorBuffer;
	public int colorId;

	public IntBuffer ib;
	public int type;

	public VertexContainer(float[] vertices) {
		this.vertices = vertices;
		this.colors = new float[] { 1, 1, 1, 1, 1, 1, 1, 1, 1 };
	}
	
	public void render() {
		load();
		draw();
		clean();
	}
	

	public void load() {
		this.vertexBuffer = BufferUtils.createFloatBuffer(vertices.length);
		this.vertexBuffer.put(this.vertices);
		this.vertexCount = vertices.length;
		this.vertexBuffer.flip();
		
		this.colorBuffer = BufferUtils.createFloatBuffer(colors.length);
		this.colorBuffer.put(this.colors);
		this.colorBuffer.flip();
		
		this.ib = BufferUtils.createIntBuffer(2);
		glGenBuffersARB(ib);

		this.vertexId = ib.get(0);
		this.colorId = ib.get(1);
	}
	
	public void draw() {
		glEnableClientState(GL_VERTEX_ARRAY);
		glEnableClientState(GL_COLOR_ARRAY);

		glBindBufferARB(GL_ARRAY_BUFFER_ARB, this.vertexId);
		glBufferDataARB(GL_ARRAY_BUFFER_ARB, this.vertexBuffer, GL_STATIC_DRAW_ARB);
		glVertexPointer(3, GL_FLOAT, 3 << 2, 0L);

		glBindBufferARB(GL_ARRAY_BUFFER_ARB, this.colorId);
		glBufferDataARB(GL_ARRAY_BUFFER_ARB, this.colorBuffer, GL_STATIC_DRAW_ARB);
		glColorPointer(3, GL_FLOAT, 3 << 2, 0L);

		glDrawArrays(GL_TRIANGLES, 0, 3);
	}
	
	public void clean() {
		glBindBufferARB(GL_ARRAY_BUFFER_ARB, 0);

		glDisableClientState(GL_COLOR_ARRAY);
		glDisableClientState(GL_VERTEX_ARRAY);
		
		this.ib.put(0, this.vertexId);
		this.ib.put(1, this.colorId);
		glDeleteBuffersARB(this.ib);
	}
}

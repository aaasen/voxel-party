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
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class VertexContainer {
	public float[] vertices;
	public FloatBuffer vertexBuffer;
	public int vertexCount;
	public int vertexId;

	public float[] colors;
	public FloatBuffer colorBuffer;
	public int colorId;

	public int vboId;
	public int vaoId;

	public int type;

	public VertexContainer(float[] vertices) {
		this.vertices = vertices;
		this.vertexBuffer = BufferUtils.createFloatBuffer(vertices.length);
		this.vertexBuffer.put(vertices);
		this.vertexCount = vertices.length;
		this.vertexBuffer.flip();
		
		this.colors = new float[] { 1, 0, 0, 0, 1, 0, 0, 0, 1 };
		this.colorBuffer = BufferUtils.createFloatBuffer(colors.length);
		this.colorBuffer.put(vertices);
		this.colorBuffer.flip();
		
//		IntBuffer ib = BufferUtils.createIntBuffer(2);
//		glGenBuffersARB(ib);
//
//		this.vertexId = ib.get(0);
//		this.colorId = ib.get(1);

//		this.genVBO();
	}

//	public void genVBO() {
//		this.vaoId = GL30.glGenVertexArrays();
//		GL30.glBindVertexArray(vaoId);
//
//		this.vboId = GL15.glGenBuffers();
//		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboId);
//
//		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, this.vertexBuffer, GL15.GL_STATIC_DRAW);
//		GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
//
//		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
//		GL30.glBindVertexArray(0);
//	}

	public void render() {
		IntBuffer ib = BufferUtils.createIntBuffer(2);

		glGenBuffersARB(ib);
		int vHandle = ib.get(0);
		int cHandle = ib.get(1);

		glEnableClientState(GL_VERTEX_ARRAY);
		glEnableClientState(GL_COLOR_ARRAY);

		glBindBufferARB(GL_ARRAY_BUFFER_ARB, vHandle);
		glBufferDataARB(GL_ARRAY_BUFFER_ARB, this.vertexBuffer, GL_STATIC_DRAW_ARB);
		glVertexPointer(3, GL_FLOAT, /* stride */3 << 2, 0L);

		glBindBufferARB(GL_ARRAY_BUFFER_ARB, cHandle);
		glBufferDataARB(GL_ARRAY_BUFFER_ARB, this.colorBuffer, GL_STATIC_DRAW_ARB);
		glColorPointer(3, GL_FLOAT, /* stride */3 << 2, 0L);

		glDrawArrays(GL_TRIANGLES, 0, 3 /* elements */);

		glBindBufferARB(GL_ARRAY_BUFFER_ARB, 0);

		glDisableClientState(GL_COLOR_ARRAY);
		glDisableClientState(GL_VERTEX_ARRAY);

		// cleanup VBO handles
		ib.put(0, vHandle);
		ib.put(1, cHandle);
		glDeleteBuffersARB(ib);
	}

	//	public void render() {
	//		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
	//		GL11.glColor3f(1f, 1f, 1f);
	//
	//		GL30.glBindVertexArray(vaoId);
	//		GL20.glEnableVertexAttribArray(0);
	//
	//		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, vertexCount);
	//
	//		GL20.glDisableVertexAttribArray(0);
	//		GL30.glBindVertexArray(0);
	//	}

	public void destroy() {
		GL20.glDisableVertexAttribArray(0);

		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL15.glDeleteBuffers(vboId);

		GL30.glBindVertexArray(0);
		GL30.glDeleteVertexArrays(vaoId);	
	}
}

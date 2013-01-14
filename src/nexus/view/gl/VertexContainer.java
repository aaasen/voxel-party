package nexus.view.gl;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class VertexContainer {
	public float[] vertices;
	public FloatBuffer vertexBuffer;
	public int vboId;
	public int vaoId;
	public int vertexCount;
	
	public VertexContainer(float[] vertices) {
		this.vertices = vertices;
		this.vertexBuffer = BufferUtils.createFloatBuffer(vertices.length);
		this.vertexBuffer.put(vertices);
		this.vertexBuffer.flip();
		this.vertexCount = vertices.length;
	}
	
	public void genVBO() {
		this.vaoId = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vaoId);

		this.vboId = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboId);
		
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, this.vertexBuffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL30.glBindVertexArray(0);
	}
	
	public void render() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

		GL30.glBindVertexArray(vaoId);
		GL20.glEnableVertexAttribArray(0);

		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, vertexCount);

		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}
	
	public void destroy() {
		GL20.glDisableVertexAttribArray(0);

		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL15.glDeleteBuffers(vboId);

		GL30.glBindVertexArray(0);
		GL30.glDeleteVertexArrays(vaoId);	
	}
}

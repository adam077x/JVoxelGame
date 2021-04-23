package voxelgame;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Model {
    private int drawCount;
    private int vertexId;
    private int colorId;
    private int indicesId;

    public Model(float[] vertices, float[] colors, int[] indices) {
        drawCount = indices.length;

        vertexId = GL20.glGenBuffers();
        GL20.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexId);
        GL20.glBufferData(GL15.GL_ARRAY_BUFFER, createBuffer(vertices), GL15.GL_STATIC_DRAW);

        colorId = GL15.glGenBuffers();
        GL20.glBindBuffer(GL15.GL_ARRAY_BUFFER, colorId);
        GL20.glBufferData(GL15.GL_ARRAY_BUFFER, createBuffer(colors), GL15.GL_STATIC_DRAW);

        indicesId = GL20.glGenBuffers();
        GL20.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesId);

        IntBuffer buffer = BufferUtils.createIntBuffer(indices.length);
        buffer.put(indices);
        buffer.flip();

        GL20.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);

        GL20.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    public Model(FloatBuffer vertices, FloatBuffer colors, IntBuffer indices) {
        drawCount = indices.capacity();

        vertexId = GL20.glGenBuffers();
        GL20.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexId);
        GL20.glBufferData(GL15.GL_ARRAY_BUFFER, vertices, GL15.GL_STATIC_DRAW);

        colorId = GL15.glGenBuffers();
        GL20.glBindBuffer(GL15.GL_ARRAY_BUFFER, colorId);
        GL20.glBufferData(GL15.GL_ARRAY_BUFFER, colors, GL15.GL_STATIC_DRAW);

        indicesId = GL20.glGenBuffers();
        GL20.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesId);

        GL20.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indices, GL15.GL_STATIC_DRAW);

        GL20.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
    }

    public void render() {
        GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        GL11.glEnableClientState(GL11.GL_COLOR_ARRAY);

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexId);
        GL11.glVertexPointer(3, GL11.GL_FLOAT, 0, 0);

        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, colorId);
        GL15.glColorPointer(4, GL11.GL_FLOAT, 0, 0);

        //GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, drawCount);
        //GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

        GL20.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesId);
        GL11.glDrawElements(GL11.GL_TRIANGLES, drawCount, GL11.GL_UNSIGNED_INT, 0);

        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);

        GL30.glDisableClientState(GL11.GL_VERTEX_ARRAY);
        GL30.glDisableClientState(GL11.GL_COLOR_ARRAY);
    }

    private FloatBuffer createBuffer(float[] data) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }
}

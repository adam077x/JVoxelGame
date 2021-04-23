package voxelgame.world;

import org.joml.Vector3f;
import org.joml.Vector3i;
import org.lwjgl.BufferUtils;
import voxelgame.Model;
import voxelgame.world.blocks.Block;
import voxelgame.world.blocks.BlockData;
import voxelgame.world.blocks.Grass;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Chunk {
    public static final int CHUNK_SIZE_WIDTH = 32;
    public static final int CHUNK_SIZE_HEIGHT = 64;

    public Block blocks[][][];
    public int x, z;

    private FloatBuffer vertices;
    private IntBuffer indices;
    private FloatBuffer colors;

    private Model model;

    public Chunk(int x, int z) {
        this.blocks = new Block[CHUNK_SIZE_WIDTH+1][CHUNK_SIZE_HEIGHT+1][CHUNK_SIZE_WIDTH+1];
        this.x = x;
        this.z = z;

        populateChunk();
        buildMesh();
    }

    private void buildMesh() {
        vertices = BufferUtils.createFloatBuffer(BlockData.vertices.length * ((CHUNK_SIZE_WIDTH * CHUNK_SIZE_WIDTH) * CHUNK_SIZE_HEIGHT));
        indices = BufferUtils.createIntBuffer(BlockData.indices.length * ((CHUNK_SIZE_WIDTH * CHUNK_SIZE_WIDTH) * CHUNK_SIZE_HEIGHT));
        colors = BufferUtils.createFloatBuffer(BlockData.getColors(new Vector3f(0, 0, 0)).length * ((CHUNK_SIZE_WIDTH * CHUNK_SIZE_WIDTH) * CHUNK_SIZE_HEIGHT));

        int vertexIndex = 0;

        for(int z = 0; z < CHUNK_SIZE_WIDTH; z++) {
            for(int y = 0; y < CHUNK_SIZE_HEIGHT; y++) {
                for(int x = 0; x < CHUNK_SIZE_WIDTH; x++) {
                    if(getBlockByPosition(x, y, z) != null) {
                        if(getBlockByPosition(x, y, z+1) == null) {
                            for(int i = 0; i < 12; i += 3) {
                                vertices.put(BlockData.vertices[i] + x + this.x * CHUNK_SIZE_WIDTH);
                                vertices.put(BlockData.vertices[i+1] + y);
                                vertices.put(BlockData.vertices[i+2] + z + this.z * CHUNK_SIZE_WIDTH);
                            }

                            indices.put(0 + vertexIndex);
                            indices.put(1 + vertexIndex);
                            indices.put(3 + vertexIndex);
                            indices.put(3 + vertexIndex);
                            indices.put(1 + vertexIndex);
                            indices.put(2 + vertexIndex);

                            float r = (float)Math.random();
                            float g = (float)Math.random();
                            float b = (float)Math.random();

                            for(int i = 0; i < 4; i++) {
                                colors.put(r);
                                colors.put(g);
                                colors.put(b);
                                colors.put(1);
                            }

                            vertexIndex += 4;
                        }

                        if(getBlockByPosition(x, y, z-1) == null) {
                            for(int i = 12; i < 24; i += 3) {
                                vertices.put(BlockData.vertices[i] + x + this.x * CHUNK_SIZE_WIDTH);
                                vertices.put(BlockData.vertices[i+1] + y);
                                vertices.put(BlockData.vertices[i+2] + z + this.z * CHUNK_SIZE_WIDTH);
                            }

                            indices.put(0 + vertexIndex);
                            indices.put(1 + vertexIndex);
                            indices.put(3 + vertexIndex);
                            indices.put(3 + vertexIndex);
                            indices.put(1 + vertexIndex);
                            indices.put(2 + vertexIndex);

                            float r = (float)Math.random();
                            float g = (float)Math.random();
                            float b = (float)Math.random();

                            for(int i = 0; i < 4; i++) {
                                colors.put(r);
                                colors.put(g);
                                colors.put(b);
                                colors.put(1);
                            }

                            vertexIndex += 4;
                        }

                        if(getBlockByPosition(x+1, y, z) == null) {
                            for(int i = 24; i < 36; i += 3) {
                                vertices.put(BlockData.vertices[i] + x + this.x * CHUNK_SIZE_WIDTH);
                                vertices.put(BlockData.vertices[i+1] + y);
                                vertices.put(BlockData.vertices[i+2] + z + this.z * CHUNK_SIZE_WIDTH);
                            }

                            indices.put(0 + vertexIndex);
                            indices.put(1 + vertexIndex);
                            indices.put(3 + vertexIndex);
                            indices.put(3 + vertexIndex);
                            indices.put(1 + vertexIndex);
                            indices.put(2 + vertexIndex);

                            float r = (float)Math.random();
                            float g = (float)Math.random();
                            float b = (float)Math.random();

                            for(int i = 0; i < 4; i++) {
                                colors.put(r);
                                colors.put(g);
                                colors.put(b);
                                colors.put(1);
                            }

                            vertexIndex += 4;
                        }

                        if(getBlockByPosition(x-1, y, z) == null) {
                            for(int i = 36; i < 48; i += 3) {
                                vertices.put(BlockData.vertices[i] + x + this.x * CHUNK_SIZE_WIDTH);
                                vertices.put(BlockData.vertices[i+1] + y);
                                vertices.put(BlockData.vertices[i+2] + z + this.z * CHUNK_SIZE_WIDTH);
                            }

                            indices.put(0 + vertexIndex);
                            indices.put(1 + vertexIndex);
                            indices.put(3 + vertexIndex);
                            indices.put(3 + vertexIndex);
                            indices.put(1 + vertexIndex);
                            indices.put(2 + vertexIndex);

                            float r = (float)Math.random();
                            float g = (float)Math.random();
                            float b = (float)Math.random();

                            for(int i = 0; i < 4; i++) {
                                colors.put(r);
                                colors.put(g);
                                colors.put(b);
                                colors.put(1);
                            }

                            vertexIndex += 4;
                        }

                        if(getBlockByPosition(x, y-1, z) == null) {
                            for(int i = 48; i < 60; i += 3) {
                                vertices.put(BlockData.vertices[i] + x + this.x * CHUNK_SIZE_WIDTH);
                                vertices.put(BlockData.vertices[i+1] + y);
                                vertices.put(BlockData.vertices[i+2] + z + this.z * CHUNK_SIZE_WIDTH);
                            }

                            indices.put(0 + vertexIndex);
                            indices.put(1 + vertexIndex);
                            indices.put(3 + vertexIndex);
                            indices.put(3 + vertexIndex);
                            indices.put(1 + vertexIndex);
                            indices.put(2 + vertexIndex);

                            float r = (float)Math.random();
                            float g = (float)Math.random();
                            float b = (float)Math.random();

                            for(int i = 0; i < 4; i++) {
                                colors.put(r);
                                colors.put(g);
                                colors.put(b);
                                colors.put(1);
                            }

                            vertexIndex += 4;
                        }
                    }
                }
            }
        }

        vertices.flip();
        indices.flip();
        colors.flip();

        model = new Model(vertices, colors, indices);
    }

    public Block getBlockByPosition(int x, int y, int z) {
             if(x > CHUNK_SIZE_WIDTH) return null;
        else if(x < 0) return null;
        else if(z > CHUNK_SIZE_WIDTH) return null;
        else if(z < 0) return null;
        else if(y > CHUNK_SIZE_HEIGHT) return null;
        else if(y < 0) return null;

        return blocks[z][y][x];
    }

    private void populateChunk() {
        for(int z = 0; z < CHUNK_SIZE_WIDTH; z++) {
            for(int y = 0; y < CHUNK_SIZE_HEIGHT; y++) {
                for (int x = 0; x < CHUNK_SIZE_WIDTH; x++) {
                    blocks[z][y][x] = new Grass(1);
                }
            }
        }
    }

    public void render() {
        model.render();
    }
}

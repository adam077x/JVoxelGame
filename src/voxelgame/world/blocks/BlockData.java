package voxelgame.world.blocks;

import org.joml.Vector3f;
import org.joml.Vector3i;

public class BlockData {
    public static final float CUBE_SIZE = 0.5f;

    public static float[] vertices = {
            -CUBE_SIZE,CUBE_SIZE,-CUBE_SIZE,
            -CUBE_SIZE,-CUBE_SIZE,-CUBE_SIZE,
            CUBE_SIZE,-CUBE_SIZE,-CUBE_SIZE,
            CUBE_SIZE,CUBE_SIZE,-CUBE_SIZE, // BACK

            -CUBE_SIZE,CUBE_SIZE,CUBE_SIZE,
            -CUBE_SIZE,-CUBE_SIZE,CUBE_SIZE,
            CUBE_SIZE,-CUBE_SIZE,CUBE_SIZE,
            CUBE_SIZE,CUBE_SIZE,CUBE_SIZE, // FRONT

            CUBE_SIZE,CUBE_SIZE,-CUBE_SIZE,
            CUBE_SIZE,-CUBE_SIZE,-CUBE_SIZE,
            CUBE_SIZE,-CUBE_SIZE,CUBE_SIZE,
            CUBE_SIZE,CUBE_SIZE,CUBE_SIZE, // LEFT

            -CUBE_SIZE,CUBE_SIZE,-CUBE_SIZE,
            -CUBE_SIZE,-CUBE_SIZE,-CUBE_SIZE,
            -CUBE_SIZE,-CUBE_SIZE,CUBE_SIZE,
            -CUBE_SIZE,CUBE_SIZE,CUBE_SIZE, // RIGHT

            -CUBE_SIZE,CUBE_SIZE,CUBE_SIZE,
            -CUBE_SIZE,CUBE_SIZE,-CUBE_SIZE,
            CUBE_SIZE,CUBE_SIZE,-CUBE_SIZE,
            CUBE_SIZE,CUBE_SIZE,CUBE_SIZE, // TOP

            -CUBE_SIZE,-CUBE_SIZE,CUBE_SIZE,
            -CUBE_SIZE,-CUBE_SIZE,-CUBE_SIZE,
            CUBE_SIZE,-CUBE_SIZE,-CUBE_SIZE,
            CUBE_SIZE,-CUBE_SIZE,CUBE_SIZE // BOT
    };

    public static int[] indices = {
            0,1,3,
            3,1,2, // BACK

            4,5,7,
            7,5,6, // FRONT

            8,9,11,
            11,9,10, // LEFT

            12,13,15,
            15,13,14, // RIGHT

            16,17,19,
            19,17,18, // TOP

            20,21,23,
            23,21,22 // BOT
    };

    public static float[] colors = {
            0,0,1,
            0,1,1,
            1,1,1,
            1,0,1,

            0,0,1,
            0,1,1,
            1,1,1,
            1,0,1,

            0,0,1,
            0,1,1,
            1,1,1,
            1,0,1,

            0,0,1,
            0,1,1,
            1,1,1,
            1,0,1,

            0,0,1,
            0,1,1,
            1,1,1,
            1,0,1,

            0,0,1,
            0,1,1,
            1,1,1,
            1,0,1
    };

    public static final float[] cubeVertices = {
            0.0f, 0.0f, 0.0f,
            CUBE_SIZE, 0.0f, 0.0f,
            CUBE_SIZE, CUBE_SIZE, 0.0f,
            0.0f, CUBE_SIZE, 0.0f,
            0.0f, 0.0f, CUBE_SIZE,
            CUBE_SIZE, 0.0f, CUBE_SIZE,
            CUBE_SIZE, CUBE_SIZE, CUBE_SIZE,
            0.0f, CUBE_SIZE, CUBE_SIZE
    };

    public static final int[] cubeIndices2 = {
            0, 3, 1, 2, // BACK
            5, 6, 4, 7, // FRONT
            3, 7, 2, 6, // TOP
            1, 5, 0, 4, // BOTTOM
            4, 7, 0, 3, // LEFT
            1, 2, 5, 6  // RIGHT
    };

    public static float[] getColors(Vector3f color) {
        float _colors[] = new float[vertices.length+vertices.length/3];

        for(int i = 0; i < _colors.length; i += 4) {
            _colors[i] = color.x;
            _colors[i + 1] = color.y;
            _colors[i + 2] = color.z;
            _colors[i + 3] = 1f;
        }

        return _colors;
    }
}

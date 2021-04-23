package voxelgame;

import org.joml.Vector2d;
import org.joml.Vector2i;
import org.lwjgl.BufferUtils;

import java.nio.DoubleBuffer;

import static org.lwjgl.glfw.GLFW.glfwGetCursorPos;

public class Mouse {
    public static Vector2d getMousePosition() {
        DoubleBuffer posX = BufferUtils.createDoubleBuffer(1);
        DoubleBuffer posY = BufferUtils.createDoubleBuffer(1);

        glfwGetCursorPos(DisplayManager.window, posX, posY);

        return new Vector2d((int)posX.get(0), (int)posY.get(0));
    }
}

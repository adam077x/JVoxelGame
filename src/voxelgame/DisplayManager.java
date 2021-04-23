package voxelgame;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFWVidMode;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwMakeContextCurrent;

public class DisplayManager {
    public static long window;

    public static void createWindow(int width, int height) {
        if(!glfwInit()) {
            throw new IllegalStateException("Failed to initalize GLFW");
        }

        glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE);
        window = glfwCreateWindow(width, height, "Voxel Game", 0, 0);
        if(window == 0) {
            throw new IllegalStateException("Failed to create window");
        }

        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window, (vidMode.width() - width) / 2, (vidMode.height() - height) / 2);

        glfwShowWindow(window);

        glfwMakeContextCurrent(window);
        glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
    }
}

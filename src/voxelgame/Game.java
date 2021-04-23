package voxelgame;

import org.joml.Vector3f;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import voxelgame.world.Chunk;
import voxelgame.world.blocks.BlockData;

import static org.lwjgl.glfw.GLFW.*;

public class Game {
    public static int WIDTH = 640, HEIGHT = 480;
    public Camera camera;
    private Model model;
    private Chunk chunk;

    public static int x, y;

    public Game() {
        DisplayManager.createWindow(WIDTH, HEIGHT);

        camera = new Camera(new Vector3f(0, 0, 0));

        GL.createCapabilities();

        GL11.glEnable(GL11.GL_DEPTH_TEST);

        //model = new Model(BlockData.vertices, BlockData.getColors(new Vector3f(1, 0, 0)), BlockData.indices);
        chunk = new Chunk(0, 0);

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while(!glfwWindowShouldClose(DisplayManager.window)){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                update();
                updates++;
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
            }
        }

        glfwTerminate();
    }

    private void update() {
        camera.update();
    }

    private void render() {
        glfwPollEvents();

        GL11.glClearColor(0.8f, 0.8f, 1, 1);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        camera.setPerspective();

        chunk.render();

        GL11.glLoadIdentity();

        glfwSwapBuffers(DisplayManager.window);
    }

    public static void main(String[] args) {
        new Game();
    }
}

package voxelgame;

import org.joml.Vector2d;
import org.joml.Vector2i;
import org.joml.Vector3f;
import org.joml.Vector3i;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.opengl.GL11;
import voxelgame.utils.Mathf;

public class Camera {
    public static float fov = 70;
    public static float zNear = 0.1f;
    public static float zFar = 1000f;
    public static float sensitivity = 0.15f;

    public float speed = 0.1f;

    public Vector3f position;
    public Vector3f rotation;

    public Camera(Vector3f position) {
        this.position = position;
        this.rotation = new Vector3f(0, 0, 0);
    }

    public Vector3f getForward() {
        Vector3f r = new Vector3f();
        Vector3f rot = new Vector3f(rotation);

        float cosY = (float) Math.cos(Math.toRadians(rot.y - 90));
        float sinY = (float) Math.sin(Math.toRadians(rot.y - 90));
        float cosP = (float) Math.cos(Math.toRadians(-rot.x));
        float sinP = (float) Math.sin(Math.toRadians(-rot.x));

        r.x = cosY * cosP;
        r.y = sinP;
        r.z = sinY * cosP;

        r.normalize();

        return r;
    }

    public Vector3f getBack() {
        return new Vector3f(getForward().mul(-1));
    }

    public Vector3f getRight() {
        Vector3f rot = new Vector3f(rotation);

        Vector3f r = new Vector3f();
        r.x = (float) Math.cos(Math.toRadians(rot.y));
        r.z = (float) Math.sin(Math.toRadians(rot.y));
        r.normalize();

        return new Vector3f(r);
    }

    public Vector3f getLeft() {
        return new Vector3f(getRight().mul(-1));
    }

    Vector2d lastMousePosition = new Vector2d();
    public void update() {
        Vector2d mousePosition = Mouse.getMousePosition();

        Vector2d diffPosition = lastMousePosition.sub(mousePosition);

        rotation.y -= diffPosition.x * sensitivity;
        rotation.x -= diffPosition.y * sensitivity;

        if(rotation.x > 90) {
            rotation.x = 90;
        }
        else if(rotation.x < -90) {
            rotation.x = -90;
        }

        if(GLFW.glfwGetKey(DisplayManager.window, GLFW.GLFW_KEY_A) == 1) {
            position.add(getLeft().mul(new Vector3f(speed, speed, speed)));
        }
        else if(GLFW.glfwGetKey(DisplayManager.window, GLFW.GLFW_KEY_D) == 1) {
            position.add(getRight().mul(new Vector3f(speed, speed, speed)));
        }
        if(GLFW.glfwGetKey(DisplayManager.window, GLFW.GLFW_KEY_W) == 1) {
            position.add(getForward().mul(new Vector3f(speed, speed, speed)));
        }
        else if(GLFW.glfwGetKey(DisplayManager.window, GLFW.GLFW_KEY_S) == 1) {
            position.add(getBack().mul(new Vector3f(speed, speed, speed)));
        }

        lastMousePosition = mousePosition;
    }

    public void setPerspective() {
        GL11.glEnable(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        Mathf.gluPerspective(fov, Game.WIDTH / Game.HEIGHT, zNear, zFar);
        GL11.glEnable(GL11.GL_MODELVIEW);

        GL11.glPushAttrib(GL11.GL_TRANSFORM_BIT);
        GL11.glRotatef(rotation.x, 1, 0, 0);
        GL11.glRotatef(rotation.y, 0, 1, 0);
        GL11.glRotatef(rotation.z, 0, 0, 1);
        GL11.glTranslatef(-position.x, -position.y, -position.z);
        GL11.glPopMatrix();
    }
}

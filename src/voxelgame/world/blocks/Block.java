package voxelgame.world.blocks;

import org.joml.Vector3f;
import org.joml.Vector3i;

public class Block {
    public int id;
    public float health;
    public Vector3f color;

    public Block(float health, int id, Vector3f color) {
        this.id = id;
        this.health = health;
        this.color = color;
    }
}

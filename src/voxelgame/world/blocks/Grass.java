package voxelgame.world.blocks;

import org.joml.Vector3f;
import org.joml.Vector3i;

public class Grass extends Block {
    public Grass(float health) {
        super(health, 1, new Vector3f(0, 0.8f, 0));
    }
}

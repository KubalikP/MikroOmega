package entities;

import java.awt.*;

public abstract class Entity {
    public int worldX, worldY;
    public int width, height;

    public Entity(int worldX, int worldY, int width, int height){
        this.worldX = worldX;
        this.worldY = worldY;
        this.width = width;
        this.height = height;
    }
}

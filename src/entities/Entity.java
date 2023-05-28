package entities;

import java.awt.*;

public abstract class Entity {
    public int worldX, worldY;
    public int width, height;

    public Rectangle hitbox;
    public boolean collision = false;
    public String direction;
    public int speed;

    public Entity(int worldX, int worldY, int width, int height){
        this.worldX = worldX;
        this.worldY = worldY;
        this.width = width;
        this.height = height;
    }
}

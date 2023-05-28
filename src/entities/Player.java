package entities;

import tiles.Tile;
import window.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static util.Constants.playerConstants.*;

public class Player extends Entity{
    private BufferedImage[] idleAnim, runAnim, runLeftAnim, jumpAnim, normalAttackAnim, dieAnim;
    private BufferedImage imgIdle, imgRun, imgRunLeft, imgJump, imgDie, imgNormalAttack;
    private int playerAction;
    private int animTick, animIndex, animRate = 20;
    private boolean attacking = false;
    private boolean jumping = false;
    private float jumpHeight = 12;
    private boolean left, right;
    private int playerSpeed = 2;
    private float gravity = 3f;
    private boolean onGround;
    private GamePanel gp;

    public Player(int worldX, int worldY, int width, int height, GamePanel gp) {
        super(worldX, worldY, width, height);
        this.gp = gp;
        hitbox = new Rectangle();
        hitbox.x = 0;
        hitbox.y = 0;
        hitbox.width = 128;
        hitbox.height = 128;
        importImage();
        loadAnim();
    }

    //method for updating animations and player position
    public void update(){
        updatePos();
        updateAnim();
        setAnim();
    }

    //method for rendering animations
    public void render(Graphics g) {
        BufferedImage[] currentAnim = null;

        if (playerAction == idle) {
            currentAnim = idleAnim;
        } else if (playerAction == normalAttack) {
            currentAnim = normalAttackAnim;
        } else if (playerAction == jump) {
            currentAnim = jumpAnim;
        } else if (playerAction == run) {
            currentAnim = runAnim;
        } else if (playerAction == runLeft) {
            currentAnim = runLeftAnim;
        } else if (playerAction == die) {
            currentAnim = dieAnim;
        }

        g.drawImage(currentAnim[animIndex], worldX, worldY, width, height, null);
    }

    //method for loading player animations
    public void loadAnim() {
        idleAnim = new BufferedImage[6];
        dieAnim = new BufferedImage[4];
        normalAttackAnim = new BufferedImage[5];
        jumpAnim = new BufferedImage[6];
        runAnim = new BufferedImage[7];
        runLeftAnim = new BufferedImage[7];

        for(int i = 0; i < idleAnim.length; i++){
            idleAnim[i] = imgIdle.getSubimage(i * 128, 0, 128, 128);
        }

        for(int i = 0; i < dieAnim.length; i++){
            dieAnim[i] = imgDie.getSubimage(i * 128, 0, 128, 128);
        }

        for(int i = 0; i < normalAttackAnim.length; i++){
            normalAttackAnim[i] = imgNormalAttack.getSubimage(i * 128, 0, 128, 128);
        }

        for(int i = 0; i < jumpAnim.length; i++){
            jumpAnim[i] = imgJump.getSubimage(i * 128, 0, 128, 128);
        }

        for(int i = 0; i < runAnim.length; i++){
            runAnim[i] = imgRun.getSubimage(i * 128, 0, 128, 128);
        }

        for(int i = 0; i < runLeftAnim.length; i++){
            runLeftAnim[i] = imgRunLeft.getSubimage(i * 128, 0, 128, 128);
        }
    }

    //method for updating player animations
    public void updateAnim() {
        animTick++;
        if(animTick >= animRate){
            animTick = 0;
            animIndex++;

            if(animIndex >= idleAnim.length){
                animIndex = 0;
                attacking = false;
            } else if(animIndex >= normalAttackAnim.length){
                animIndex = 0;
                attacking = false;
            } else if(animIndex >= jumpAnim.length){
                animIndex = 0;
                attacking = false;
            } else if(animIndex >= runAnim.length){
                animIndex = 0;
                attacking = false;
            } else if(animIndex >= runLeftAnim.length){
                animIndex = 0;
                attacking = false;
            } else if(animIndex>= dieAnim.length){
                animIndex = 0;
                attacking = false;
            }
        }
    }


    //method for detecting player action and setting animations
    public void setAnim() {
        int startAnim = playerAction;

        if(right){
            playerAction = run;
        } else if(left){
            playerAction = runLeft;
        } else {
            playerAction = idle;
        }

        if (attacking) {
            playerAction = normalAttack;
        }

        if (jumping) {
            playerAction = jump;
        }

        if (startAnim != playerAction) {
            animTick = 0;
            animIndex = 0;
        }
    }

    //method for updating player position in game
    public void updatePos() {
        if(!attacking) {
            if(left && !right) {
                worldX -= playerSpeed;
            } else if(right && !left) {
                worldX += playerSpeed;
            }
        }

        if(attacking) {
            playerAction = normalAttack;
        }

        if(jumping) {
            if (onGround) {
                jumpHeight = 12;
                onGround = false;
            }

            if (jumpHeight > 0) {
                worldY -= jumpHeight;
                jumpHeight -= 0.4f;
            } else {
                jumping = false;
                jumpHeight = 0;
            }
        } else {
            if(!onGround){
                worldY += gravity;
                if(worldY >= 275){
                    worldY = 275;
                    onGround = true;
                }
            }
        }
    }

    //method for importing player sprites
    private void importImage() {
        InputStream is1 = getClass().getResourceAsStream("/PlayerIdle.png");
        InputStream is2 = getClass().getResourceAsStream("/PlayerRun.png");
        InputStream is3 = getClass().getResourceAsStream("/PlayerDie.png");
        InputStream is4 = getClass().getResourceAsStream("/PlayerAttack.png");
        InputStream is5 = getClass().getResourceAsStream("/PlayerJump.png");
        InputStream is6 = getClass().getResourceAsStream("/PlayerRunLeft.png");
        try {
            imgIdle = ImageIO.read(is1);
            imgRun = ImageIO.read(is2);
            imgDie = ImageIO.read(is3);
            imgNormalAttack = ImageIO.read(is4);
            imgJump = ImageIO.read(is5);
            imgRunLeft = ImageIO.read(is6);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setAttacking(boolean attacking){
        this.attacking = attacking;
    }

    public void setJumping(boolean jumping){
        this.jumping = jumping;
    }

}
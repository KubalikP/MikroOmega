package entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import static util.Constants.enemyConstants.*;

public class Enemy extends Entity{
    private BufferedImage imgIdle, imgRun, imgRunLeft, imgJump, imgDie, imgNormalAttack;
    private BufferedImage[] idleAnim, runAnim, runLeftAnim, jumpAnim, normalAttackAnim, dieAnim;
    private int animTick, animIndex, animRate = 25;
    private int enemyAction;

    public Enemy(int worldX, int worldY, int width, int height) {
        super(worldX, worldY, width, height);
        importImage();
        loadAnim();
    }

    //method for rendering enemy
    public void render(Graphics g){
        BufferedImage[] currentAnim = null;

        if (enemyAction == idle) {
            currentAnim = idleAnim;
        } else if (enemyAction == normalAttack) {
            currentAnim = normalAttackAnim;
        } else if (enemyAction == jump) {
            currentAnim = jumpAnim;
        } else if (enemyAction == run) {
            currentAnim = runAnim;
        } else if (enemyAction == runLeft) {
            currentAnim = runLeftAnim;
        } else if (enemyAction == die) {
            currentAnim = dieAnim;
        }

        g.drawImage(currentAnim[animIndex], worldX, worldY, width, height, null);
    }

    //method for updating and setting enemy animation
    public void update(){
        updateAnim();
        setAnim();
    }

    //method for detecting enemy action and setting animations
    private void setAnim() {
        enemyAction = idle;
    }

    //method for updating enemy animations
    private void updateAnim() {
        animTick++;
        if(animTick >= animRate){
            animTick = 0;
            animIndex++;

            if(animIndex >= idleAnim.length){
                animIndex = 0;
            } else if(animIndex >= normalAttackAnim.length){
                animIndex = 0;
            } else if(animIndex >= jumpAnim.length){
                animIndex = 0;
            } else if(animIndex >= runAnim.length){
                animIndex = 0;
            } else if(animIndex >= runLeftAnim.length){
                animIndex = 0;
            } else if(animIndex>= dieAnim.length){
                animIndex = 0;
            }
        }
    }

    //method for loading enemy animations
    private void loadAnim() {
        idleAnim = new BufferedImage[5];
        dieAnim = new BufferedImage[4];
        normalAttackAnim = new BufferedImage[6];
        jumpAnim = new BufferedImage[8];
        runAnim = new BufferedImage[8];
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

    //method for importing enemy sprites
    private void importImage() {
        InputStream is1 = getClass().getResourceAsStream("/EnemyIdle.png");
        InputStream is2 = getClass().getResourceAsStream("/EnemyRun.png");
        InputStream is3 = getClass().getResourceAsStream("/EnemyDie.png");
        InputStream is4 = getClass().getResourceAsStream("/EnemyAttack.png");
        InputStream is5 = getClass().getResourceAsStream("/EnemyJump.png");
        InputStream is6 = getClass().getResourceAsStream("/EnemyRunLeft.png");
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
}

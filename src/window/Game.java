package window;

import entities.Player;
import tiles.TileManager;

import java.awt.*;

public class Game implements Runnable{ ;
    private GameWindow gw;
    private GamePanel gp;
    private Thread thread;
    private final int FPS = 60;
    private final int UPS = 200;
    private Player player;
    private TileManager tm;

    public Game(){
        init();
        gp = new GamePanel(this);
        gw = new GameWindow(gp);
        tm = new TileManager(gp);
        gp.requestFocus();
        startLoop();
    }

    private void init(){
        player = new Player(0,275, 128, 128);
    }

    public void startLoop(){
        thread = new Thread(this);
        thread.start();
    }

    public void update(){
        player.update();
    }

    public void render(Graphics g){
        if(tm != null){
            tm.paint(g);
        }

        player.render(g);
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS;
        double timePerUpdate = 1000000000.0 / UPS;
        long lastCheck = System.currentTimeMillis();
        long now = System.nanoTime();
        long previousTime = System.nanoTime();
        int updates = 0;
        int frames = 0;
        double deltaU = 0;
        double deltaF = 0;

        while(true){
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if(deltaU >= 1){
                update();
                updates++;
                deltaU--;
            }

            if(deltaF >= 1){
                gp.repaint();
                deltaF--;
                frames++;
            }

            if(System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS:" + FPS + " UPS:" + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    public Player getPlayer(){
        return player;
    }
}

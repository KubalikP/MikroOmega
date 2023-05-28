package window;

import inputs.KeyboardInputs;
import tiles.TileManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GamePanel extends JPanel {
    private Game game;
    private final int ScreenCol = 16;
    private final int ScreenRow = 12;
    private BufferedImage bg;
    public TileManager tm;
    public int gameState;
    public final int menuState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    private UI ui;

    public GamePanel(Game game){
        tm = new TileManager(this);
        this.game = game;
        gameState = menuState;
        loadBg();
        addKeyListener(new KeyboardInputs(this));
        setFocusable(true);
        setDoubleBuffered(true);
        ui = new UI(this);
    }

    //paint method
    public void paint(Graphics g) {
        super.paint(g);

        if(gameState == menuState){
            ui.paint(g, menuState);
        } else {
            g.drawImage(bg, 0, 0, 800, 600, null);
            game.render(g);
        }
    }

    public Game getGame(){
        return game;
    }


    public int getScreenCol() {
        return ScreenCol;
    }

    public int getScreenRow() {
        return ScreenRow;
    }

    //method for loading background image
    public void loadBg(){
        try {
            bg = ImageIO.read(getClass().getResourceAsStream("/BG.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getGameState() {
        return gameState;
    }

    public int getPlayState() {
        return playState;
    }

    public int getMenuState() {
        return menuState;
    }
}

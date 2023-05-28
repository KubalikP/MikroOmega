package window;

import entities.Player;
import inputs.KeyboardInputs;
import inputs.MouseInputs;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GamePanel extends JPanel {
    private MouseInputs mouse;
    private Game game;
    private final int ScreenCol = 16;
    private final int ScreenRow = 12;
    private BufferedImage bg;

    public GamePanel(Game game){
        this.game = game;
        loadBg();
        mouse = new MouseInputs();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        setFocusable(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(bg, 0, 0, 800, 600, null);
        game.render(g);
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

    public void loadBg(){
        try {
            bg = ImageIO.read(getClass().getResourceAsStream("/BG.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

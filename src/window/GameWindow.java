package window;

import javax.swing.*;
import java.awt.*;

public class GameWindow {
    private JFrame j;
    public GameWindow(GamePanel gp){
        j = new JFrame("HHH");
        j.setPreferredSize(new Dimension(800, 600));
        j.setDefaultCloseOperation(3);
        j.setResizable(false);
        j.add(gp);
        j.pack();
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }
}

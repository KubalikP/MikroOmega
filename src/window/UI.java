package window;

import java.awt.*;

public class UI {
    private GamePanel gp;
    private Graphics g;
    private Font arial_40;
    private Font algerian_60;

    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        algerian_60 = new Font("Algerian", Font.PLAIN, 60);
    }

    public void paint(Graphics g, int gameState){
        this.g = g;

        if(gameState == gp.menuState){
            paintMenu();;
        }

        if(gameState == gp.playState){

        }

        if(gameState == gp.pauseState){
            paintPause();
        }
    }

    //method for painting main menu
    private void paintMenu() {
        String text1 = "GAME";
        String text2 = "Press ENTER to start";
        g.setFont(algerian_60);
        g.setColor(Color.BLACK);
        g.drawString(text1, 300 , 200);
        g.drawString(text2, 80, 300);
    }

    //method for painting pause menu;
    public void paintPause(){
        String text = "PAUSED";
        g.setFont(arial_40);
        g.setColor(Color.BLACK);
        g.drawString(text, 300, 270);
    }
}

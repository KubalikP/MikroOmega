package inputs;

import window.GamePanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import entities.Player;

public class KeyboardInputs implements KeyListener {
    private GamePanel gp;

    public KeyboardInputs(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
                break;
            case KeyEvent.VK_S:
                break;
            case KeyEvent.VK_A:
                gp.getGame().getPlayer().setLeft(true);
                break;
            case KeyEvent.VK_D:
                gp.getGame().getPlayer().setRight(true);
                break;
            case KeyEvent.VK_J:
                gp.getGame().getPlayer().setAttacking(true);
                break;
            case KeyEvent.VK_K:
                break;
            case KeyEvent.VK_SPACE:
                gp.getGame().getPlayer().setJumping(true);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
                break;
            case KeyEvent.VK_D:
                gp.getGame().getPlayer().setRight(false);
                break;
            case KeyEvent.VK_A:
                gp.getGame().getPlayer().setLeft(false);
                break;
            case KeyEvent.VK_S:
                break;
        }
    }
}

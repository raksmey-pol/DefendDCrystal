package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GamePanel;

import static utilities.Constants.Directions.*;

public class KeyInputs implements KeyListener {
    private GamePanel gamePanel;

    public KeyInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W:
                 gamePanel.setGame().setPlayer().setUp(true);
                break;
            case KeyEvent.VK_S:
                 gamePanel.setGame().setPlayer().setDown(true);
                break;
            case KeyEvent.VK_A:
                 gamePanel.setGame().setPlayer().setLeft(true);
                break;
            case KeyEvent.VK_D:
                 gamePanel.setGame().setPlayer().setRight(true);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamePanel.setGame().setPlayer().setUp(false);
                break;
            case KeyEvent.VK_S:
                gamePanel.setGame().setPlayer().setDown(false);
                break;
            case KeyEvent.VK_A:
                gamePanel.setGame().setPlayer().setLeft(false);
                break;
            case KeyEvent.VK_D:
                gamePanel.setGame().setPlayer().setRight(false);
                break;
        }
    }

}

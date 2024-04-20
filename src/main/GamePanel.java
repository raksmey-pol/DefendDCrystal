package main;

import javax.swing.JPanel;

import input.KeyInputs;
import input.MouseInputs;

import java.awt.*;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;


public class GamePanel extends JPanel{
    private MouseInputs mouseInputs;
    private Game game;

    public GamePanel(Game game) {

        mouseInputs = new MouseInputs(this);
        this.game = game;

        setBackground(Color.GRAY);
        setPanelSize();
        addKeyListener(new KeyInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }

    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH,GAME_HEIGHT);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void  paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
    }


    public void updateGame() {
    }
    public Game setGame(){
        return game;
    }
}

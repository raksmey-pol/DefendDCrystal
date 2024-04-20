package main;

import background.LevelHandler;
import entities.Crystal;
import entities.Player;

import java.awt.*;

public class Game implements Runnable{
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS = 120;
    private final int UPS = 200;
    private Player player;
    private LevelHandler levelHandler;
    private Crystal crystal;

    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 1.0f;
    public final static float PLAYER_SCALE = 3.0f;
    public final static float CRYSTAL_SCALE = 2.0f;
    public final static int TILES_WIDTH = 40;
    public final static int TILES_HEIGHT = 32;
    public final static int TILES_SIZE = (int)(TILES_DEFAULT_SIZE*SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_HEIGHT;

    public Game() {
        initializeClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();

        startGameLoop();
    }



    private void initializeClasses() {
        player = new Player(200,200,(int)(32*PLAYER_SCALE), (int)(32*PLAYER_SCALE));
        levelHandler = new LevelHandler(this);
        crystal = new Crystal(595,302,(int)(37*SCALE*CRYSTAL_SCALE), (int)(72*SCALE*CRYSTAL_SCALE));
        player.setCrystal(crystal);
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    private void update() {
        player.update();
        levelHandler.update();
        crystal.update();
    }

    public void render(Graphics g) {
        levelHandler.draw(g);
        player.render(g);
        crystal.render(g);

    }

    public void run() {
        int frames = 0;
        int updates = 0;
        double timePerFrame = 1000000000.0 / FPS;
        double timePerUpdate = 1000000000.0 / UPS;
        long previousTime = System.nanoTime();
        long lastCheck = System.currentTimeMillis();
        double deltaUpdate = 0;
        double deltaFrame = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaUpdate += (currentTime - previousTime)/timePerUpdate;
            deltaFrame += (currentTime - previousTime)/timePerFrame;
            previousTime = currentTime;

            if(deltaUpdate >= 1) {
                update();
                updates++;
                deltaUpdate--;
            }

            if (deltaFrame >= 1) {
                gamePanel.repaint();
                frames++;
                deltaFrame--;
            }

            if(System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public Player setPlayer() {
        return  player;
    }
    public void windowFocusLost(){
        player.pauseMovement();
    }
}

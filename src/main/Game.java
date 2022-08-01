package main;

import entities.Player;
import entities.Pipe;

public class Game implements Runnable {

    private final int gravValue = 1;
    private int pipeDelta = 1;

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;

    private final int FPS_LIMIT = 120;

    public Game() {
        // Create the game panel and window
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);

        // Focus on the gamePanel
        gamePanel.requestFocus();

        // Start game loop
        startGameLoop();
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.run();
    }

    @Override 
    public void run() {
        double timePerFrame = 1000000000 / FPS_LIMIT; // in nanoseconds
        
        long now, lastFrame;
        now = lastFrame = System.nanoTime();       

        while (true) {
            now = System.nanoTime(); 
            if (now - lastFrame >= timePerFrame) {
                gamePanel.updateObjects(gravValue, pipeDelta, timePerFrame);
                gamePanel.repaint();
                lastFrame = now;
            }
        }
    }
}

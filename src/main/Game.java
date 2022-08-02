package main;

public class Game implements Runnable {

    public final static float SCALE = 0.5f;

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;

    private final int FPS_LIMIT = 120;
    private final int UPS_LIMIT = 200; 

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
        double timePerFrame = 1000000000.0 / FPS_LIMIT; // in nanoseconds
        double timePerUpdate = 1000000000.0 / UPS_LIMIT;
        
        long previousTime = System.nanoTime();

        double deltaU, deltaF;
        deltaU = deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;

            previousTime = currentTime;
            if (deltaU >= 1) {
                gamePanel.updateObjects(deltaU);
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                deltaF--;
            }
        }
    }
}

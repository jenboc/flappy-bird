package main;
import inputs.KeyboardInputs;
import entities.Player;
import entities.Pipe;

import java.awt.image.BufferedImage;
import java.util.Random;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

    private final int numPipes = 3;
    private final int pipeSpawnX = 750;
    private int pipeYMin;
    private int pipeYMax;

    private BufferedImage background;
    private int width;
    private int height;
    
    public Player player;
    public Pipe[] pipes; 

    private Random random; 
    
    public GamePanel() {
        random = new Random();

        Loader loader = new Loader();
        background = loader.importImg("background-day.png");
        setPanelSize();

        player = new Player(width/2-50, height/2, height);
        addKeyListener(new KeyboardInputs(player));          
        
        int pipeHeight = loader.importImg("pipe-green.png").getHeight();
        pipeYMin = height - pipeHeight;
        pipeYMax = pipeHeight;

        startGame();
    }

    private void startGame() {
        player.move(width/2-50, height/2);
        player.reset();
        initPipes();         
    }

    private int generatePipeY() {
        int maxRandom = (pipeYMax-pipeYMin) + 1;
        int y = random.nextInt(maxRandom);
        y += pipeYMin;

        return y;
    }

    private void initPipes() {
        pipes = new Pipe[numPipes];
        for (int i = 0; i < numPipes; i++) {
            pipes[i] = new Pipe(this);
            int x = width + 250*(i+1);
            int y = generatePipeY();
            pipes[i].spawn(x, y);
        }
    }

    public void resetPipe(Pipe p) {
        int y = generatePipeY();
        p.spawn(pipeSpawnX, y);  
    }

    public void updateObjects(double deltaU) {
        player.update(deltaU, pipes);

        if (player.isMoving()) {
            for (Pipe pipe : pipes) 
                pipe.move();
        }

        if (!player.alive) {
            startGame();
        }
    }

    private void setPanelSize() {
        width = background.getWidth();
        height = background.getHeight();
        Dimension size = new Dimension(width, height);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
 
        for (Pipe pipe : pipes) {
            pipe.draw(g);
        }

        player.draw(g);  
    }
}

package states;

import main.GamePanel;
import main.Loader;

import entities.Player;
import entities.Pipe;

import java.awt.Graphics;
import java.util.Random;

public class GameState extends State {

    private GamePanel gamePanel;
    private Player player;

    private Random random; 

    public Pipe[] pipes; 
    private final int numPipes = 3;
    private final int pipeSpawnX = 750;
    private int pipeYMin;
    private int pipeYMax;

    public GameState(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        player = gamePanel.player;

        random = new Random();
    }  

    public void initialise() {
        Loader loader = new Loader();
        int pipeHeight = loader.importImg("pipe-green.png").getHeight();
        pipeYMin = gamePanel.height - pipeHeight;
        pipeYMax = pipeHeight;

        startGame();
    }

    private void startGame() {
        player.move(gamePanel.width/2-50, gamePanel.height/2);
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
            int x = gamePanel.width + 250*(i+1);
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

    public void paintComponent(Graphics g) {
        for (Pipe pipe : pipes) {
            pipe.draw(g);
        }

        player.draw(g);  
    }
}

package entities;
import main.Game;
import main.Loader;
import main.GamePanel;

import java.awt.Graphics;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;

public class Pipe {

    private final float pipeDelta = 1 * Game.SCALE;

    private GamePanel gamePanel;

    BufferedImage pipeImg; 
    private int gap; 

    private float x, y; // Centre of gap 

    public Pipe(GamePanel gamePanel) {
        this.gamePanel = gamePanel; 

        Loader loader = new Loader();
        pipeImg = loader.importImg("pipe-green.png");
        gap = loader.importImg("yellowbird-midflap.png").getHeight() * 2;
    } 

    public boolean isColliding(Player player) {
        int width = pipeImg.getWidth(); 
        float leftBoundary = x - width; 
        float rightBoundary = x + width;

        float bottomOfTopPipe = y - gap;
        float topOfBottomPipe = y + gap;
        
        // Top and Bottom Collision (using centre coordinates as thats where the model is tallest)
        float playerTop = player.y;
        float playerBottom = player.y + player.height();
        float playerCX = player.x + player.width()/2;
        if ((playerTop <= bottomOfTopPipe || playerBottom >= topOfBottomPipe) && playerCX <= leftBoundary && playerCX >= rightBoundary)
            return true;
        
        // Left collision (no right collision as the bird wont collide that way) 
        float playerRight = player.x + player.width()/5;
        float playerLeft = player.x;
        if ((playerRight >= leftBoundary && playerLeft <= rightBoundary) && (playerBottom >= topOfBottomPipe || playerTop <= bottomOfTopPipe))
            return true; 

        return false;
    }

    public void move() {
        x -= pipeDelta; 

        int width = pipeImg.getWidth();
        if (x <= -width/2) 
            gamePanel.resetPipe(this);
    }

    public void spawn(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        // Coordinates 
        float topX, bottomX;
        topX = bottomX = x - pipeImg.getWidth()/2; 
        float topY = y - (gap + pipeImg.getHeight()); 
        float bottomY = y + gap; 

        // Rotation Filter
        int imgCentreX = pipeImg.getWidth()/2;
        int imgCentreY = pipeImg.getHeight()/2;
        AffineTransform transform = AffineTransform.getRotateInstance(Math.PI, imgCentreX, imgCentreY);
        AffineTransformOp filter = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR); 

        g.drawImage(filter.filter(pipeImg, null), (int)topX, (int)topY, null);
        g.drawImage(pipeImg, (int)bottomX, (int)bottomY, null);
    }
}

package entities;
import main.Loader;
import main.GamePanel;

import java.awt.Graphics;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;

public class Pipe {

    private GamePanel gamePanel;

    BufferedImage pipeImg; 
    private int gap; 

    private int x, y; // Centre of gap 

    public Pipe(GamePanel gamePanel) {
        this.gamePanel = gamePanel; 

        Loader loader = new Loader();
        pipeImg = loader.importImg("pipe-green.png");
        gap = loader.importImg("yellowbird-midflap.png").getHeight() * 2;
    } 

    public boolean isColliding(int playerTop, int playerBottom, int playerRight, int playerLeft) {
        int width = pipeImg.getWidth(); 
        int leftBoundary = x - width; 
        int rightBoundary = x + width;

        int bottomOfTopPipe = y - gap;
        int topOfBottomPipe = y + gap;

        boolean betweenEdges = (playerRight >= leftBoundary && playerRight <= rightBoundary) || (playerLeft >= leftBoundary && playerLeft <= rightBoundary);
        boolean outsideGap = playerTop <= bottomOfTopPipe || playerBottom >= topOfBottomPipe;

        if (betweenEdges && outsideGap)
            return true;

        return false; 
    }

    public void move(double dX) {
        x -= dX; 

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
        int topX, bottomX;
        topX = bottomX = x - pipeImg.getWidth()/2; 
        int topY = y - (gap + pipeImg.getHeight()); 
        int bottomY = y + gap; 

        // Rotation Filter
        int imgCentreX = pipeImg.getWidth()/2;
        int imgCentreY = pipeImg.getHeight()/2;
        AffineTransform transform = AffineTransform.getRotateInstance(Math.PI, imgCentreX, imgCentreY);
        AffineTransformOp filter = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR); 

        g.drawImage(filter.filter(pipeImg, null), topX, topY, null);
        g.drawImage(pipeImg, bottomX, bottomY, null);
    }
}

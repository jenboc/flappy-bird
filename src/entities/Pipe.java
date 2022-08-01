package entities;
import main.Loader;

import java.awt.Graphics;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;

public class Pipe {

    BufferedImage pipeImg;
    private int gap; 

    private int x, y; // Centre of gap 

    public Pipe() {
        Loader loader = new Loader();
        pipeImg = loader.importImg("pipe-green.png");
        gap = loader.importImg("yellowbird-midflap.png").getHeight() * 2;
    } 

    public void move(double dX) {
        x -= dX; 
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

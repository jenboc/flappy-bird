package entities;
import main.Loader;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player {
    private int x, y;
    public int dX, dY;

    private BufferedImage[] flapAnimation; 
    private int aniIndex, aniTick, aniSpeed = 30; 

    public Player(int startingX, int startingY) {
        x = startingX;
        y = startingY;

        dX = dY = 0;
        
        Loader loader = new Loader();
        String[] fileNames = {"yellowbird-upflap.png", "yellowbird-midflap.png", "yellowbird-downflap.png"};
        flapAnimation = loader.loadAnimations(fileNames);
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= flapAnimation.length)
                aniIndex = 0;
        }
    }

    public void draw(Graphics g) {
        updateAnimationTick(); 
        g.drawImage(flapAnimation[aniIndex], x, y, null);
    } 
}

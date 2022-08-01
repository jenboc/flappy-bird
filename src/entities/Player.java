package entities;
import main.Loader;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player {

    private final int flapForce = 25;

    private int x, y;
    public double dY;

    private int bottomOfScreen;

    private BufferedImage[] flapAnimation; 
    private int aniTick, aniSpeed = 30; 
    private int aniIndex = 2;
    private boolean flapping = false;

    public Player(int startingX, int startingY, int bottomOfScreen) {
        x = startingX;
        y = startingY;

        this.bottomOfScreen = bottomOfScreen;

        dY = 0;
        
        Loader loader = new Loader();
        String[] fileNames = {"yellowbird-upflap.png", "yellowbird-midflap.png", "yellowbird-downflap.png"};
        flapAnimation = loader.loadAnimations(fileNames);
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed && flapping) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= flapAnimation.length) {
                aniIndex = flapAnimation.length-1;
                flapping = false;
            } 
        }
    }

    public void flap() {
        dY = -flapForce;
        flapping = true;
        aniTick = 0;
        aniIndex = 0;
    }

    private void applyGravity(double gravValue) {
        dY += gravValue;
    }

    private void checkCollisions() {
        BufferedImage model = flapAnimation[aniIndex];
        int height = model.getHeight();
        int cY = y + height/2;

        if (cY >= bottomOfScreen) {
            System.out.println("Dead");
        }
    }

    public void update(double gravValue, double deltaTime) {
        applyGravity(gravValue);
        y += dY / 10;      

        checkCollisions(); 
    }

    public void draw(Graphics g) {
        updateAnimationTick(); 
        g.drawImage(flapAnimation[aniIndex], x, y, null);
    } 
}

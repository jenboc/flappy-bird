package entities;
import main.Loader;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player {

    private final int flapForce = 25;

    private int x, y;
    private double dY;

    private int bottomOfScreen;

    private BufferedImage[] flapAnimation; 
    private int aniTick, aniSpeed = 30; 
    private int aniIndex = 2;
    private boolean flapping = false;

    public boolean alive = true;
    private boolean canMove = true;

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

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void reset() {
        canMove = true;
        alive = true;
        dY = 0;
    }

    public void flap() {
        if (canMove && alive)
            dY = -flapForce;
        flapping = true;
        aniTick = 0;
        aniIndex = 0;
    }

    private void applyGravity(double gravValue) {
        dY += gravValue;
    }

    private void checkCollisions(Pipe[] pipes) {
        BufferedImage model = flapAnimation[aniIndex];
        int height = model.getHeight();
        int width = model.getWidth();
        
        int cY = y + height/2;
        int bY = y + height/2;
        int rX = x + width/4;
        int lX = x - width/4;
        
        for (Pipe pipe : pipes) {
            boolean collided = pipe.isColliding(y, bY, rX, lX);

            if (collided) {
                canMove = false;
                break;
            }
        }

        if (cY >= bottomOfScreen && alive) {
            alive = false;
            System.out.println("Show menu here");
        }
    }

    public boolean isMoving() {
        return canMove && alive;
    }

    public void update(double gravValue, double deltaTime, Pipe[] pipes) {
        applyGravity(gravValue);
        y += dY / 10;      

        checkCollisions(pipes); 
    }

    public void draw(Graphics g) {
        updateAnimationTick(); 
        g.drawImage(flapAnimation[aniIndex], x, y, null);
    } 
}

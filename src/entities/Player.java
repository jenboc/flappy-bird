package entities;
import main.Game;
import main.Loader;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player {

    private final float flapForce = 4.5f * Game.SCALE;
    private final float gravValue = 0.08f * Game.SCALE;

    public float x, y;
    private float dY;

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

    public int height() {
        return flapAnimation[aniIndex].getHeight();
    }
    public int width() {
        return flapAnimation[aniIndex].getWidth();
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

    private void applyGravity() {
        dY += gravValue;
    }

    private void checkCollisions(Pipe[] pipes) {
        for (Pipe pipe : pipes) {
            boolean collided = pipe.isColliding(this);

            System.out.println(collided);

            if (collided) {
                canMove = false;
                break;
            }
        }

        float cY = y + height()/2;

        if (cY >= bottomOfScreen && alive) {
            alive = false;
            System.out.println("Show menu here");
        }
    }

    public boolean isMoving() {
        return canMove && alive;
    }

    public void update(double deltaTime, Pipe[] pipes) {
        applyGravity();

        y += dY;      

        checkCollisions(pipes); 
    }

    public void draw(Graphics g) {
        updateAnimationTick(); 
        g.drawImage(flapAnimation[aniIndex], (int)x, (int)y, null);
    } 
}

package states;

import java.awt.Graphics;

public abstract class State {
    public abstract void initialise();
    public abstract void updateObjects(double deltaU);
    public abstract void paintComponent(Graphics g);
}
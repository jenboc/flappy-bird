package main;
import inputs.KeyboardInputs;
import entities.Player;

import states.*;

import java.awt.image.BufferedImage;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

    private BufferedImage background;
    public int width;
    public int height;
    
    public Player player;

    private StateManager stateManager;
    private GameState gameState;
    
    public GamePanel() {
        Loader loader = new Loader();
        background = loader.importImg("background-day.png");
        setPanelSize();

        player = new Player(width/2-50, height/2, height);
        addKeyListener(new KeyboardInputs(player));          

        stateManager = new StateManager();
        gameState = new GameState(this);
        stateManager.addState(gameState);
    }

    public void updateObjects(double deltaU) {
        stateManager.currentState().updateObjects(deltaU);
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

        stateManager.currentState().paintComponent(g);        
    }
}

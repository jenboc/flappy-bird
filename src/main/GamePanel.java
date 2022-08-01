package main;
import inputs.KeyboardInputs;
import entities.Player;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

    private final int width = 1280;
    private final int height = 800;
    
    Player player;
    
    public GamePanel() {
        addKeyListener(new KeyboardInputs());          
        setPanelSize();

        player = new Player(width/2-50, height/2);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(width, height);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        player.draw(g);
    }
}

package main;
import inputs.KeyboardInputs;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

    private BufferedImage img;

    public GamePanel() {
        addKeyListener(new KeyboardInputs());          
        setPanelSize();

        importImg(); 
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    private void importImg() {
        // First slash infers it is not in the same directory as the code file
        InputStream is = getClass().getResourceAsStream("/sprites/yellowbird-midflap.png");

        try { 
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(img, 50, 50, null);
    }
}

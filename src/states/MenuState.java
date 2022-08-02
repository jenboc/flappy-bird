package states;

import main.Loader;
import main.GamePanel;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class MenuState extends State {

    private GamePanel gamePanel;
    private BufferedImage menuMessage;

    private int messageX, messageY;

    public MenuState(GamePanel gamePanel) {
        Loader loader = new Loader();
        menuMessage = loader.importImg("message.png");

        this.gamePanel = gamePanel;
    }

    public void initialise() {
        messageX = (gamePanel.width - menuMessage.getWidth()) / 2;
        messageY = (gamePanel.height - menuMessage.getHeight()) / 2; 
    }

    public void updateObjects(double deltaU) 
    {}

    public void paintComponent(Graphics g) {
        g.drawImage(menuMessage, messageX, messageY, null);
    }
}

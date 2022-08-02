package inputs;

import main.GamePanel;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class MouseInputs implements MouseListener {

    private GamePanel gamePanel;

    public MouseInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    } 

    @Override 
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override 
    public void mouseReleased(MouseEvent e) {}

    @Override 
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1)
            gamePanel.inputRecieved(); 
    }
}

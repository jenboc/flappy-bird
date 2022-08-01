package main;

import javax.swing.JFrame;

public class GameWindow {
    private JFrame frame;

    public GameWindow(GamePanel gamePanel) {
        // Create the Frame and add the panel
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gamePanel);
        frame.setResizable(false);
        frame.pack(); // Adjust size relative to components
        
        // Position Frame in centre and make it visible
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }    
}

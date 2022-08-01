package main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class Loader {
    public BufferedImage[] loadAnimations(String[] fileNames) {
        BufferedImage[] ani = new BufferedImage[fileNames.length];

        for (int i = 0; i < fileNames.length; i++) {
            ani[i] = importImg(fileNames[i]);
        }  

        return ani;
    }

    public BufferedImage importImg(String name) {
        // First slash infers it is not in the same directory as the code file
        InputStream is = getClass().getResourceAsStream("/sprites/" + name);

        try { 
            return ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}

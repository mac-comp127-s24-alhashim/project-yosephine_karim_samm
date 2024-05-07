package MyTimer;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This class represents a JPanel with a background image.
 */
public class ImagePanel extends JPanel {
    private BufferedImage backgroundImage;

    /**
     * Constructor. it constructs a new ImagePanel with the desired background image.
     * @param imagePath The path to the background image file.
     */
    public ImagePanel(String imagePath) {
        try {
            backgroundImage = ImageIO.read(new File(imagePath));
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * this method overrides the paintComponent method to draw the background image.
     * @param g The Graphics object used to paint the component.
     */
    protected void paintComponent(Graphics g) {

        int panelWidth = this.getWidth();
        int panelHeight = this.getHeight();
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, panelWidth, panelHeight, null);
        }
    }
}
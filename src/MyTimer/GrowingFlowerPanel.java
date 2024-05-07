package MyTimer;

import javax.swing.*;
import java.awt.*;

public class GrowingFlowerPanel extends JPanel {
    private ImageIcon[] flowerImages;
    private int currentStage;

    public GrowingFlowerPanel() {
        flowerImages = new ImageIcon[3];
        flowerImages[0] = new ImageIcon("src/MyTimer/flower0.png");
        flowerImages[1] = new ImageIcon("src/MyTimer/flower1.png");
        flowerImages[2] = new ImageIcon("src/MyTimer/flower2.png");

        currentStage = 0; 
    }

    public void updateStage(int totalSeconds, int remainingSeconds) {
        int halfway = totalSeconds / 2;

        if (remainingSeconds == 0) {
            currentStage = 2;
        } else if (remainingSeconds <= halfway && remainingSeconds != 0) {
            currentStage = 1;
        } else {
            currentStage = 0;
        }
        repaint();
    }
    @Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (currentStage >= 0 && currentStage < 3) {
        int width = getWidth() / 2; 
        int height = width * flowerImages[currentStage].getIconHeight() / flowerImages[currentStage].getIconWidth(); 
        g.drawImage(flowerImages[currentStage].getImage(), getWidth() / 2 - width / 2, getHeight() - height - 10, width, height, null); 
    }
}

   
}

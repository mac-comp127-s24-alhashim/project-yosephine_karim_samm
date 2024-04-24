package MyTimer;

import javax.swing.*;


public class FlowerPanel extends JPanel {
    private ImageIcon[] flowerIcons; // Array to hold different stages of flower growth
    private int growthStage = 0;

    public FlowerPanel() {
        // Load flower images
        flowerIcons = new ImageIcon[]{
                new ImageIcon("flowe_1.jpeg"),
                new ImageIcon("flowe_2.jpeg"),
                new ImageIcon("flowe_3.jpeg"),
              
                // Add more images for additional growth stages if needed
        };

        // Set initial flower image
        setFlowerIcon(flowerIcons[0]);
    }

    // Method to update the flower image
    public void updateFlower() {
        if (growthStage < flowerIcons.length - 1) {
            growthStage++;
            setFlowerIcon(flowerIcons[growthStage]);
        } else {
            // Flower has reached full growth
            // You can handle this case as per your requirements
        }
    }

    // Method to set the flower icon on the panel
    private void setFlowerIcon(ImageIcon icon) {
        removeAll(); // Clear previous content
        JLabel flowerLabel = new JLabel(icon);
        add(flowerLabel);
        revalidate(); // Refresh panel
        repaint(); // Repaint panel
    }
}

package MyTimer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


/**
 * This class represents a JPanel for taking notes with a text area and a save button.
 */
public class NoteTakingPanel extends JPanel {
    private JTextArea noteTextArea;
    private JButton saveButton;

    /**
     * Constructor. it creates a new NoteTakingPanel with a text area for taking notes and a button for saving.
     */
    public NoteTakingPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Note Taking"));

        noteTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(noteTextArea);
        add(scrollPane, BorderLayout.CENTER);

        saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveToFile();
            }
        });
        add(saveButton, BorderLayout.SOUTH);

        setPreferredSize(new Dimension(250, 300)); // Set preferred size
        setBackground(Color.WHITE); // Set background color
        noteTextArea.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font for note text area
    }

    /**
     * a method that saves the content of the text area to a file with a name containing the current date and time
     */
    private void saveToFile() {
        try {
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM_dd_yyyy_HH_mm_ss");

            String formattedDateTime = currentDateTime.format(formatter); 
            String fileName = "notes_" + formattedDateTime + ".txt";
            FileWriter writer = new FileWriter(fileName);
            writer.write(noteTextArea.getText());
            writer.close();
            JOptionPane.showMessageDialog(this, "File saved successfully as " + fileName);
            
        } 
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}

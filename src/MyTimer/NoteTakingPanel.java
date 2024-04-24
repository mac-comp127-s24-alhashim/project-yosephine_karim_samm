package MyTimer;

import javax.swing.*;
import java.awt.*;

public class NoteTakingPanel extends JPanel {
    private JTextArea noteTextArea;

    public NoteTakingPanel() {
        setLayout(new BorderLayout());

        noteTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(noteTextArea);
        add(scrollPane, BorderLayout.CENTER);
    }
}

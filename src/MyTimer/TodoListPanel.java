package MyTimer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TodoListPanel extends JPanel {
    private static final int NUM_TASKS = 6;
    private JTextField[] taskFields;
    private JButton[] completeButtons;

    public TodoListPanel() {
        setLayout(new GridLayout(NUM_TASKS, 2, 5, 5));
        taskFields = new JTextField[NUM_TASKS];
        completeButtons = new JButton[NUM_TASKS];

        for (int i = 0; i < NUM_TASKS; i++) {
            JTextField taskField = new JTextField();
            taskFields[i] = taskField;
            add(taskField);

            JButton completeButton = new JButton("Complete");
            completeButtons[i] = completeButton;
            add(completeButton);
            final int index = i;
            completeButton.addActionListener(e -> {
                completeTask(index);
            });
        }
    }

        private void completeTask(int index) {
            completeButtons[index].setText("Completed");
            completeButtons[index].setEnabled(false);
            taskFields[index].setEditable(false);
            taskFields[index].setBackground(Color.GREEN);
        }
        
}


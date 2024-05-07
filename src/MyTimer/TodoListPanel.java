package MyTimer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * This class represents a JPanel for managing a to-do list
 * It allows users to enter tasks, mark them as complete, and automatically reset them after a certain time
 */

public class TodoListPanel extends JPanel {
    private static final int NUM_TASKS = 6;
    private JTextField[] taskFields;
    private JButton[] completeButtons;

    /**
     * Constructor. it creates a new TodoListPanel with a grid layout to display task fields and complete buttons
     */
    public TodoListPanel() {
        setLayout(new GridLayout(NUM_TASKS, 2, 5, 5));
        taskFields = new JTextField[NUM_TASKS];
        completeButtons = new JButton[NUM_TASKS];

        initializeTasks();
    }

    /**
     * this method Initializes the task fields and complete buttons
     */
    private void initializeTasks() {
        for (int i = 0; i < NUM_TASKS; i++) {
            JTextField taskField = createTaskField();
            taskFields[i] = taskField;
            add(taskField);

            JButton completeButton = createCompleteButton(i);
            completeButtons[i] = completeButton;
            add(completeButton);
        }
    }

    /**
     * this method creates a new task field with specified properties
     * @return The created JTextField for entering task
     */
    private JTextField createTaskField() {
        JTextField taskField = new JTextField();
        taskField.setBackground(Color.WHITE);
        taskField.setPreferredSize(new Dimension(220, 30));
        taskField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return taskField;
    }

    /**
     * a method that creates a new complete button with specified properties
     * @param index The index of the task relative to the button
     * @return The created JButton 
     */
    private JButton createCompleteButton(int index) {
        JButton completeButton = new JButton("Complete");
        completeButton.addActionListener(e -> completeTask(index));
        completeButton.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
        return completeButton;
    }

    /**
     * a method respnsible about marking the task at the specified index as complete
     * @param index The index of the task to be completed
     */
    private void completeTask(int index) {
        JTextField taskField = taskFields[index];
        JButton completeButton = completeButtons[index];
        
        highlightTask(taskField);
        disableCompleteButton(completeButton);
        scheduleTaskReset(index);
    }

    /**
     * highlights the completed task by changing its background color to green
     * @param taskField The task field to be highlighted
     */
    private void highlightTask(JTextField taskField) {
        taskField.setBackground(Color.GREEN);
    }

    /**
     * disables the complete button
     * @param completeButton the button to be disabled
     */
    private void disableCompleteButton(JButton completeButton) {
        completeButton.setEnabled(false);
    }


    /**
     * schedules the reset of the completed task after a certain time.
     * @param index The index of the completed task.
     */
    private void scheduleTaskReset(int index) {
        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetTask(index);
                addNewTask();
            }
        });
        timer.setRepeats(false); 
        timer.start();
    }

    /**
     * a method to reset the task at the specified index by clearing the text field and enabling the complete button
     * @param index The index of the task to be reset
     */
    private void resetTask(int index) {
        JTextField taskField = taskFields[index];
        JButton completeButton = completeButtons[index];

        taskField.setBackground(Color.WHITE);
        taskField.setText("");
        completeButton.setEnabled(true);
    }

    /**
     * adds a new empty task to the bottom of the list
     */
    private void addNewTask() {
        JTextField lastTaskField = taskFields[NUM_TASKS - 1];
        JButton lastCompleteButton = completeButtons[NUM_TASKS - 1];

        lastTaskField.setText("");
        lastTaskField.setBackground(Color.WHITE);
        lastCompleteButton.setEnabled(true);
    }
}

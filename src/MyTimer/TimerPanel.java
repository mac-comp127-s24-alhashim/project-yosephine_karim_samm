package MyTimer;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * this class represents a JPanel for setting and displaying a countdown timer.
 * it allows users to input the time (hours, minutes, seconds), start and stop the timer, and visualize the countdown progress.
 */
public class TimerPanel extends JPanel {
    private JTextField hoursField, minField, secField;
    private JButton startButton, stopButton;
    private JProgressBar progressBar;
    private Timer timer;
    private Clip clip;
    private GrowingFlowerPanel flowerPanel;

 /**
     * Constructor. it constructs a new TimerPanel with input fields for hours, minutes, and seconds,
     * start and stop buttons for controlling the timer, and a progress bar to visualize countdown.
     */
    public TimerPanel(GrowingFlowerPanel flowerPanel) {
        this.flowerPanel = flowerPanel;
        setLayout(new BorderLayout()); 

        JPanel timePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5)); // FlowLayout with center alignment and spacing
        timePanel.add(new JLabel("Hours:"));
        timePanel.add(hoursField = new JTextField(2));
        timePanel.add(new JLabel("Minutes:"));
        timePanel.add(minField = new JTextField(2));
        timePanel.add(new JLabel("Seconds:"));
        timePanel.add(secField = new JTextField(2));

        startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(100, 40));
        startButton.addActionListener(e -> startTimer());

        stopButton = new JButton("Stop");
        stopButton.setPreferredSize(new Dimension(100, 40));
        stopButton.addActionListener(e -> stopTimer());
        stopButton.setEnabled(false);


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);


        JPanel progressPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        progressPanel.add(progressBar = new JProgressBar());
        progressBar.setPreferredSize(new Dimension(200, 30));


        add(timePanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(progressPanel, BorderLayout.SOUTH);

        timePanel.setOpaque(false);
        buttonPanel.setOpaque(false);
        progressPanel.setOpaque(false);
    }

/**
     * starts the countdown timer based on the input hours, minutes, and seconds
     */
    private void startTimer() {
    if (hoursField.getText().isEmpty()) {
        hoursField.setText("00");
    }
    if (minField.getText().isEmpty()) {
        minField.setText("00");
    }
    if (secField.getText().isEmpty()) {
        secField.setText("00");
    }

    int remainingSeconds = getTimeInSeconds();

    setFieldEditable(false);
    startButton.setEnabled(false);
    stopButton.setEnabled(true);

    progressBar.setMaximum(remainingSeconds);
    progressBar.setValue(remainingSeconds);

    timer = new Timer(1000, new ActionListener() {
        int count = remainingSeconds;

        @Override
        public void actionPerformed(ActionEvent e) {
            count--;

            if (count <= 0) {
                stopTimer();
                count = 0; 
            }

            updateTimerDisplay(count);
         
            flowerPanel.updateStage(remainingSeconds, count);

            if (count == 0) {
                flowerPanel.updateStage(remainingSeconds, count);
            }
        }
    });
    timer.start();
}

/**
     * gets the total time in seconds based on the input hours, minutes, and seconds.
     * @return The total time in seconds.
     */
    private int getTimeInSeconds() {
        return Integer.parseInt(hoursField.getText()) * 3600
                + Integer.parseInt(minField.getText()) * 60
                + Integer.parseInt(secField.getText());
    }

/**
     * sets the editable property of the input fields.
     * @param editable The boolean value to set for editability.
     */
    private void setFieldEditable(boolean editable) {
        hoursField.setEditable(editable);
        minField.setEditable(editable);
        secField.setEditable(editable);
    }

     /**
     * this method stops the countdown timer if running and enables input fields and start button while disabling the stop button.
     */
    private void stopTimer() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
        setFieldEditable(true);
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
    }

/**
     * updates the display of the timer with the remaining time in hours, minutes, and seconds.
     * @param remainingSeconds The remaining time in seconds.
     */
    private void updateTimerDisplay(int remainingSeconds) {
        int remainingHours = remainingSeconds / 3600;
        int remainingMinutes = (remainingSeconds % 3600) / 60;
        int remainingSecs = remainingSeconds % 60;
    
        hoursField.setText(String.format("%02d", remainingHours));
        minField.setText(String.format("%02d", remainingMinutes));
        secField.setText(String.format("%02d", remainingSecs));
    
        progressBar.setValue(remainingSeconds);
    }
    
}

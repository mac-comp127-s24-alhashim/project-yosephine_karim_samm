package MyTimer;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerGUI extends JFrame {
    private Timer timer;
    private JLabel timerLabel;
    private JTextField timeTextField;

    public TimerGUI() {
        setTitle("Timer Example");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create components
        timerLabel = new JLabel("Timer: ");
        timeTextField = new JTextField(10);
        JButton startButton = new JButton("Start");

        // Add components to the frame
        JPanel panel = new JPanel();
        panel.add(new JLabel("Enter time (seconds): "));
        panel.add(timeTextField);
        panel.add(startButton);
        panel.add(timerLabel);
        add(panel);

        // Action listener for the start button
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTimer();
            }
        });
    }

    private void startTimer() {
        try {
            int seconds = Integer.parseInt(timeTextField.getText());
            timerLabel.setText("Timer: " + seconds);

            // Create and start the timer
            timer = new Timer(1000, new ActionListener() {
                int secondsLeft = seconds;

                @Override
                public void actionPerformed(ActionEvent e) {
                    secondsLeft--;
                    timerLabel.setText("Timer: " + secondsLeft);

                    if (secondsLeft <= 0) {
                        stopTimer();
                        JOptionPane.showMessageDialog(TimerGUI.this, "Time's up!");
                    }
                }
            });
            timer.start();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid integer for the time.");
        }
    }

    private void stopTimer() {
        if (timer != null) {
            timer.stop();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TimerGUI timerGUI = new TimerGUI();
                timerGUI.setVisible(true);
            }
        });
    }
}
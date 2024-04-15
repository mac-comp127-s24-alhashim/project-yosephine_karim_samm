package MyTimer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyTimer {
    private int remainingSeconds;
    private JTextField hoursField;
    private JTextField minField;
    private JTextField secField;
    private JButton startButton;
    private JProgressBar progressBar;

    public MyTimer() {
        JFrame frame = new JFrame("Countdown Timer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(2, 3, 5, 5));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel progressPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel hoursLabel = new JLabel("Hours:");
        JLabel minLabel = new JLabel("Minutes:");
        JLabel secLabel = new JLabel("Seconds:");

        hoursField = new JTextField(2);
        minField = new JTextField(2);
        secField = new JTextField(2);

        startButton = new JButton("Start");
        startButton.addActionListener(e -> startTimer());

        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);

        inputPanel.add(hoursLabel);
        inputPanel.add(minLabel);
        inputPanel.add(secLabel);
        inputPanel.add(hoursField);
        inputPanel.add(minField);
        inputPanel.add(secField);

        buttonPanel.add(startButton);
        progressPanel.add(progressBar);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(progressPanel, BorderLayout.SOUTH);

        frame.setPreferredSize(new Dimension(300, 200));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void startTimer() {
        int hours = Integer.parseInt(hoursField.getText());
        int minutes = Integer.parseInt(minField.getText());
        int seconds = Integer.parseInt(secField.getText());
        remainingSeconds = hours * 3600 + minutes * 60 + seconds;

        hoursField.setEditable(false);
        minField.setEditable(false);
        secField.setEditable(false);
        startButton.setEnabled(false);

        progressBar.setMaximum(remainingSeconds);
        progressBar.setValue(remainingSeconds);

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remainingSeconds--;
                progressBar.setValue(remainingSeconds);
                if (remainingSeconds >= 0) {
                    updateDisplay();
                } else {
                    ((Timer) e.getSource()).stop();
                    enableInputs();
                }
            }
        });
        timer.start();
    }

    private void updateDisplay() {
        int hours = remainingSeconds / 3600;
        int minutes = (remainingSeconds % 3600) / 60;
        int seconds = remainingSeconds % 60;
        hoursField.setText(String.format("%02d", hours));
        minField.setText(String.format("%02d", minutes));
        secField.setText(String.format("%02d", seconds));
    }

    private void enableInputs() {
        hoursField.setEditable(true);
        minField.setEditable(true);
        secField.setEditable(true);
        startButton.setEnabled(true);
    }

    public static void main(String[] args) {
        new MyTimer();
    }
}

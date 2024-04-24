package MyTimer;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MyTimer {
    private int remainingSeconds;
    private JTextField hoursField, minField, secField;
    private JButton startButton, stopButton;
    private JProgressBar progressBar;
    private Timer timer;
    private Clip clip;
    private TodoListPanel todoListPanel;
    private NoteTakingPanel noteTakingPanel;
    private QuotePanel quotePanel;

    public MyTimer() {
        JFrame frame = new JFrame("Countdown Timer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImagePanel mainPanel = new ImagePanel("/Users/manih112047/Documents/COMP 127/final-secret-project/src/MyTimer/GARDEN.jpg");

        mainPanel.setLayout(null);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        todoListPanel = new TodoListPanel();
        mainPanel.add(todoListPanel, BorderLayout.EAST);

        noteTakingPanel = new NoteTakingPanel();
        noteTakingPanel.setPreferredSize(new Dimension(200, 300));
        mainPanel.add(noteTakingPanel, BorderLayout.WEST);

        quotePanel = new QuotePanel();
        quotePanel.setPreferredSize(new Dimension(200, 100));
        mainPanel.add(quotePanel, BorderLayout.CENTER);


        hoursField = new JTextField(2);
        minField = new JTextField(2);
        secField = new JTextField(2);
        startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(100, 40));
        startButton.addActionListener(e -> startTimer());
        stopButton = new JButton("Stop");
        stopButton.setPreferredSize(new Dimension(100, 40));
        stopButton.addActionListener(e -> stopTimer());
        stopButton.setEnabled(false);
        progressBar = new JProgressBar();
        progressBar.setPreferredSize(new Dimension(200, 30));

        JPanel inputPanel = new JPanel(new GridLayout(1, 6, 10, 0));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JPanel progressPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));

        setupComponents(inputPanel, buttonPanel, progressPanel);


        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(progressPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setPreferredSize(new Dimension(700, 400));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        hoursField.setOpaque(false);
        minField.setOpaque(false);
        secField.setOpaque(false);
        startButton.setOpaque(false);
        stopButton.setOpaque(false);
        progressBar.setOpaque(false);

        inputPanel.setOpaque(false);
        buttonPanel.setOpaque(false);
        progressPanel.setOpaque(false);

        frame.setContentPane(mainPanel);

        frame.setPreferredSize(new Dimension(700, 400));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void setupComponents(JPanel inputPanel, JPanel buttonPanel, JPanel progressPanel) {
        JLabel[] labels = {new JLabel("Hours:"), new JLabel("Minutes:"), new JLabel("Seconds:")};
        JTextField[] fields = {hoursField, minField, secField};

        for (int i = 0; i < labels.length; i++) {
            inputPanel.add(labels[i]);
            inputPanel.add(fields[i]);
        }

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);

        progressPanel.add(progressBar);
    }

    private void startTimer() {
        remainingSeconds = getTimeInSeconds();

        setFieldEditable(false);
        startButton.setEnabled(false);
        stopButton.setEnabled(true);

        progressBar.setMaximum(remainingSeconds);
        progressBar.setValue(remainingSeconds);

        timer = new Timer(1000, e -> {
            remainingSeconds--;
            progressBar.setValue(remainingSeconds);
            updateDisplay();
            if (remainingSeconds <= 0) {
                stopTimer();
            }
        });
        timer.start();
    }

    private int getTimeInSeconds() {
        return Integer.parseInt(hoursField.getText()) * 3600
                + Integer.parseInt(minField.getText()) * 60
                + Integer.parseInt(secField.getText());
    }

    private void setFieldEditable(boolean editable) {
        hoursField.setEditable(editable);
        minField.setEditable(editable);
        secField.setEditable(editable);
    }

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

    private void updateDisplay() {
        int hours = remainingSeconds / 3600;
        int minutes = (remainingSeconds % 3600) / 60;
        int seconds = remainingSeconds % 60;
        hoursField.setText(String.format("%02d", hours));
        minField.setText(String.format("%02d", minutes));
        secField.setText(String.format("%02d", seconds));
    }

    public static void main(String[] args) {
        new MyTimer();
    }
}
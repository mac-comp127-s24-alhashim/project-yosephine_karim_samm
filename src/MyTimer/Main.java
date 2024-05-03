package MyTimer;
import javax.swing.*;
import java.awt.*;

/**
 * the Main class serves as the entry point for the Productivity Application.
 * it is repsosible about initializing the main JFrame and adding various panels to create the user interface.
 */

public class Main {
    private JFrame frame;
    private TimerPanel timerPanel;
    private TodoListPanel todoListPanel;
    private NoteTakingPanel noteTakingPanel;
    private QuotePanel quotePanel;
    private GrowingFlowerPanel flowerPanel;
    
/**
     * Constructor. it constructs a new instance of the Main class, initializing the JFrame and adding the panels.
     * The JFrame has a timer panel (TimerPanel), a to-do list panel (TodoListPanel), a note-taking panel (NoteTakingPanel), and a quote panel (QuotePanel).
     */

    public Main() {
        frame = new JFrame("Productivity Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImagePanel mainPanel = new ImagePanel("src/MyTimer/imageedit_1_6975826444.png"); 
        mainPanel.setLayout(new BorderLayout());

        JPanel eastPanel = new JPanel(new BorderLayout());
        JPanel centerPanel = new JPanel(new BorderLayout());

        flowerPanel = new GrowingFlowerPanel();
        timerPanel = new TimerPanel(flowerPanel);
    
        todoListPanel = new TodoListPanel();
        noteTakingPanel = new NoteTakingPanel();
        quotePanel = new QuotePanel();
        quotePanel.setPreferredSize(new Dimension(200, 300));

        eastPanel.add(todoListPanel, BorderLayout.NORTH);
        eastPanel.add(noteTakingPanel, BorderLayout.CENTER);

        centerPanel.add(quotePanel, BorderLayout.SOUTH);
        centerPanel.add(flowerPanel, BorderLayout.CENTER);

        mainPanel.add(timerPanel, BorderLayout.NORTH);
        mainPanel.add(eastPanel, BorderLayout.EAST);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        frame.setContentPane(mainPanel);

        quotePanel.setOpaque(false);
        todoListPanel.setOpaque(false);
        timerPanel.setOpaque(false);
        flowerPanel.setOpaque(false);
        eastPanel.setOpaque(false);
        centerPanel.setOpaque(false);

        frame.setPreferredSize(new Dimension(1200, 700));
        frame.pack();
        frame.setVisible(true);
    }
/**
     * Main method to create an instance of the Main class and run the application.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Main(); 
    }
}

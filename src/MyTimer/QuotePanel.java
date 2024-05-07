package MyTimer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * This class represents a panel that displays quotes and provides a button to display to the next quote.
 */
public class QuotePanel extends JPanel {
    private JLabel quoteLabel;
    private JButton nextButton;

    private String[] quotes = {
        "The only way to do great work is to love what you do. - Steve Jobs",
        "Work hard in silence, let success be your noise. - Frank Ocean",
        "In the midst of chaos, there is also opportunity. - Sun Tzu",
        "Believe you can and you're halfway there. - Theodore Roosevelt"
    };
    private int currentQuoteIndex = 0;

    /**
     * Constructor. it constructs a new QuotePanel with quote label and next button.
     */
    public QuotePanel() {
        setLayout(new BorderLayout());

        quoteLabel = new JLabel(quotes[currentQuoteIndex]);
        quoteLabel.setHorizontalAlignment(JLabel.CENTER);
        add(quoteLabel, BorderLayout.CENTER);

        nextButton = new JButton("Next Quote");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayNextQuote();
            }
        });
        add(nextButton, BorderLayout.SOUTH);
    }

    /**
     * Displays the next quote when the next button is clicked.
     */
    private void displayNextQuote() {
        currentQuoteIndex = (currentQuoteIndex + 1) % quotes.length;
        quoteLabel.setText(quotes[currentQuoteIndex]);
    }

}

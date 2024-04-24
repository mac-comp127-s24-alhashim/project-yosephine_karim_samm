package MyTimer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuotePanel extends JPanel {
    private JLabel quoteLabel;
    private JButton quoteButton;
    private Quotes quotes;

    public QuotePanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        quoteLabel = new JLabel("", SwingConstants.CENTER);
        quoteLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        quoteButton = new JButton("Generate Quote");
        quoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateQuoteLabel();
            }
        });

        add(quoteLabel, BorderLayout.CENTER);
        add(quoteButton, BorderLayout.SOUTH);

        quotes = new Quotes();
    }

    private void updateQuoteLabel() {
        String randomQuote = quotes.getRandomQuote();
        quoteLabel.setText(randomQuote);
    }
}

package MyTimer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Quotes {
    private List<String> quotes;
    private Random random;

    public Quotes() {
        quotes = new ArrayList<>();
        random = new Random();

        quotes.add("The only way to do great work is to love what you do. – Steve Jobs");
        quotes.add("Success is not final, failure is not fatal: It is the courage to continue that counts. – Winston Churchill");
        quotes.add("Your time is limited, don’t waste it living someone else’s life. – Steve Jobs");
        quotes.add("Innovation distinguishes between a leader and a follower. – Steve Jobs");
    }

    public String getRandomQuote() {
        if (quotes.isEmpty()) {
            return "No quotes available.";
        }
        int index = random.nextInt(quotes.size());
        return quotes.get(index);
    }
}

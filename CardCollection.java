import java.util.*;

class Card {
    String symbol;
    String value;

    Card(String symbol, String value) {
        this.symbol = symbol;
        this.value = value;
    }

    public String toString() {
        return value + " of " + symbol;
    }
}

public class CardCollection {
    static List<Card> deck = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void addCard() {
        System.out.print("Enter Card Symbol (e.g., Hearts, Spades): ");
        String symbol = sc.next();
        System.out.print("Enter Card Value (e.g., Ace, 2, King): ");
        String value = sc.next();
        deck.add(new Card(symbol, value));
        System.out.println("Card Added!");
    }

    public static void searchBySymbol() {
        System.out.print("Enter Symbol to search: ");
        String symbol = sc.next();
        List<Card> result = new ArrayList<>();
        for (Card card : deck) {
            if (card.symbol.equalsIgnoreCase(symbol)) {
                result.add(card);
            }
        }
        if (result.isEmpty()) {
            System.out.println("No Cards Found for " + symbol);
        } else {
            System.out.println("Cards found: " + result);
        }
    }

    public static void displayAllCards() {
        if (deck.isEmpty()) {
            System.out.println("No Cards Available!");
        } else {
            for (Card card : deck) {
                System.out.println(card);
            }
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Add Card\n2. Search by Symbol\n3. Display All Cards\n4. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> addCard();
                case 2 -> searchBySymbol();
                case 3 -> displayAllCards();
                case 4 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid Choice!");
            }
        }
    }
}

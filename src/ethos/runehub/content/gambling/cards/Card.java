package ethos.runehub.content.gambling.cards;

public class Card {

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "[Suit: " + suit + " Rank: " + rank + "]";
    }

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
        this.value = Rank.getValue(rank);
    }

    private final Suit suit;
    private final Rank rank;
    private int value;
}

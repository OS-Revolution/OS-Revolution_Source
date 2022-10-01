package ethos.runehub.content.gambling.cards;

import java.util.Arrays;
import java.util.Random;

public class Deck {

    public void shuffle() {
        for (int i = 0; i < cards.length; i++) {
            int index = random.nextInt(cards.length);
            Card cardA = cards[index];
            Card cardB = cards[0];

            this.setCard(index, cardB);
            this.setCard(0,cardA);
        }
    }

    public Card[] getCards() {
        return cards;
    }

    public void setCard(int index, Card card) {
        cards[index] = card;
    }

    @Override
    public String toString() {
        return Arrays.toString(cards);
    }

    public Deck(Card[] cards) {
        this.cards = cards; //new Card[Suit.values().length * Rank.values().length];
        this.random = new Random();
    }

    private final Card[] cards;
    private final Random random;
}

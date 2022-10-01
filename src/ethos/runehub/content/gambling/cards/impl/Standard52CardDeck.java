package ethos.runehub.content.gambling.cards.impl;

import ethos.runehub.content.gambling.cards.Card;
import ethos.runehub.content.gambling.cards.Deck;
import ethos.runehub.content.gambling.cards.Rank;
import ethos.runehub.content.gambling.cards.Suit;

public class Standard52CardDeck extends Deck {

    private void populateDeck() {
        int index = 0;
        for (Suit s : Suit.values()) {
            for (Rank r : Rank.values()) {
                Card card = new Card(s, r);
                this.setCard(index, card);
                index++;
            }
        }
    }

    public Standard52CardDeck() {
        super(new Card[Suit.values().length * Rank.values().length]);
        this.populateDeck();
    }
}

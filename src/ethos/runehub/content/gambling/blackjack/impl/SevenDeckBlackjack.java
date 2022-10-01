package ethos.runehub.content.gambling.blackjack.impl;

import ethos.runehub.content.gambling.blackjack.Blackjack;
import ethos.runehub.content.gambling.cards.impl.Standard52CardDeck;

public class SevenDeckBlackjack extends Blackjack {

    public SevenDeckBlackjack() {
        super(new Standard52CardDeck[]{
                new Standard52CardDeck(),
                new Standard52CardDeck(),
                new Standard52CardDeck(),
                new Standard52CardDeck(),
                new Standard52CardDeck(),
                new Standard52CardDeck(),
                new Standard52CardDeck()
        });
    }
}

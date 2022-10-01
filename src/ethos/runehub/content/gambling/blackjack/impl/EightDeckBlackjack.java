package ethos.runehub.content.gambling.blackjack.impl;

import ethos.runehub.content.gambling.blackjack.Blackjack;
import ethos.runehub.content.gambling.cards.impl.Standard52CardDeck;

public class EightDeckBlackjack extends Blackjack {

    public EightDeckBlackjack() {
        super(new Standard52CardDeck[]{
                new Standard52CardDeck(),
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

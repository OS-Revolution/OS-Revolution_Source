package ethos.runehub.content.gambling.blackjack.impl;

import ethos.runehub.content.gambling.blackjack.Blackjack;
import ethos.runehub.content.gambling.cards.impl.Standard52CardDeck;

public class TwoDeckBlackjack extends Blackjack {

    public TwoDeckBlackjack() {
        super(new Standard52CardDeck[] {new Standard52CardDeck(),new Standard52CardDeck()});
    }
}

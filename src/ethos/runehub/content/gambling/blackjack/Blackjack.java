package ethos.runehub.content.gambling.blackjack;

import ethos.Server;
import ethos.runehub.content.gambling.cards.Card;
import ethos.runehub.content.gambling.cards.Deck;
import ethos.runehub.content.gambling.cards.impl.Standard52CardDeck;

import java.util.*;

public abstract class Blackjack {

    public Standard52CardDeck[] getDecks() {
        return decks;
    }

    public Queue<BettingPosition> getPositions() {
        return positions;
    }

    public Queue<Card> getCards() {
        return cards;
    }

    public Card draw() {
        return cards.poll();
    }

    public void start(BettingPosition position) {
        Arrays.stream(decks).filter(Objects::nonNull).forEach(Deck::shuffle);
        Arrays.stream(decks).filter(Objects::nonNull).forEach(deck -> cards.addAll(Arrays.asList(deck.getCards())));
        Server.getEventHandler().submit(new BlackjackSession(position, this));
    }

    public static enum Action {
        HIT,STAND,DOUBLE_DOWN,SURRENDER;
    }

    public Blackjack(Standard52CardDeck[] decks) {
        this.decks = decks;
        this.positions = new ArrayDeque<>(5);
        this.cards = new LinkedList<>();
    }

    private final Standard52CardDeck[] decks;
    private final Queue<BettingPosition> positions;
    private final Queue<Card> cards;
}

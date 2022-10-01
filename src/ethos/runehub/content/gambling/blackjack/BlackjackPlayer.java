package ethos.runehub.content.gambling.blackjack;

import ethos.model.players.Player;
import ethos.runehub.content.gambling.cards.Card;

import java.util.LinkedList;
import java.util.List;

public class BlackjackPlayer {

    public List<Card> getHand() {
        return hand;
    }

    public int getWager() {
        return wager;
    }

    public void setWager(int wager) {
        this.wager = wager;
    }

    public Player getPlayer() {
        return player;
    }

    public int getHandTotal() {
        return hand.stream().mapToInt(Card::getValue).sum();
    }

    public Blackjack.Action getTurnAction() {
        return turnAction;
    }

    public void setTurnAction(Blackjack.Action turnAction) {
        this.turnAction = turnAction;
    }

    @Override
    public String toString() {
        return player == null ? "Dealer" : player.playerName;
    }

    public BlackjackPlayer(Player player) {
        this.hand = new LinkedList<>();
        this.player = player;
    }

    private final List<Card> hand;
    private int wager;
    private Blackjack.Action turnAction;
    private final Player player;
}

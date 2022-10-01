package ethos.runehub.content.gambling.blackjack;

import ethos.model.players.Player;

import java.util.ArrayList;
import java.util.List;

public class BettingPosition {

    public List<BlackjackPlayer> getPlayers() {
        return players;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public BettingPosition() {
        this.players = new ArrayList<>(3);
        this.open = true;
    }

    private final List<BlackjackPlayer> players;
    private boolean open;
}

package ethos.runehub.event.player;

import ethos.model.players.Player;

public class PlayerUpdateService implements Runnable{

    @Override
    public void run() {

    }

    public PlayerUpdateService(Player player) {
        this.player = player;
    }

    private final Player player;
}

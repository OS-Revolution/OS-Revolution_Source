package ethos.runehub.event.player;

import ethos.Server;
import ethos.event.Event;
import ethos.model.players.Player;

public class StunPlayerEvent extends Event<Player> {
    public StunPlayerEvent(Player attachment, int ticks) {
        super("stun", attachment, ticks);
    }

    @Override
    public void initialize() {
        this.getAttachment().getAttributes().setMovementResricted(true);
        this.getAttachment().getAttributes().setActionLocked(true);
    }

    @Override
    public void execute() {
        this.getAttachment().getAttributes().setMovementResricted(false);
        this.getAttachment().getAttributes().setActionLocked(false);
        this.stop();
    }
}

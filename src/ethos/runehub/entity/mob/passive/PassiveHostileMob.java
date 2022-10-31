package ethos.runehub.entity.mob.passive;

import ethos.model.players.Player;
import ethos.runehub.action.click.npc.ClickNpcAction;
import ethos.runehub.entity.mob.hostile.HostileMob;

public abstract class PassiveHostileMob extends HostileMob {

    public abstract ClickNpcAction talkTo(Player player, int index);

    public int getId() {
        return id;
    }

    public PassiveHostileMob(int id) {
        super(id);
        this.id = id;
    }

    private final int id;
}

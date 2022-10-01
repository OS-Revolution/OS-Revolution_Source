package ethos.runehub.entity.mob.passive;

import ethos.model.players.Player;
import ethos.runehub.action.click.npc.ClickNpcAction;
import ethos.runehub.entity.mob.Mob;

public abstract class PassiveMob extends Mob {

    public abstract ClickNpcAction talkTo(Player player, int index);

    public int getId() {
        return id;
    }

    public PassiveMob(int id) {
        this.id = id;
    }

    private final int id;
}

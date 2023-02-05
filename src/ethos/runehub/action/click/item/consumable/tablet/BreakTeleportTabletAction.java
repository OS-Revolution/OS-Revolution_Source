package ethos.runehub.action.click.item.consumable.tablet;

import ethos.model.players.Player;
import ethos.runehub.action.click.item.ClickConsumableItemAction;
import ethos.runehub.entity.item.impl.TeleportTablet;
import ethos.runehub.entity.item.impl.TeleportTabletCache;
import ethos.runehub.skill.Skill;
import org.runehub.api.util.math.geometry.Point;

public class BreakTeleportTabletAction extends ClickConsumableItemAction {

    @Override
    protected void onActionStart() {
        this.getActor().teleporting = true;
        this.getActor().lastTeleport = System.currentTimeMillis();
        this.getActor().startAnimation(4731);
        this.getActor().gfx0(678);
        this.getActor().getPlayerAction().canWalk(false);
    }


    @Override
    protected void consume() {
        final TeleportTablet tablet = TeleportTabletCache.getInstance().read(this.getItemId());
        final Point selectedPoint = tablet.getTeleportArea().getAllPoints().get(Skill.SKILL_RANDOM.nextInt(tablet.getTeleportArea().getAllPoints().size()));
        this.getActor().teleportToX = selectedPoint.getX();
        this.getActor().teleportToY = selectedPoint.getY();
        this.getActor().heightLevel = 0;
        this.getActor().teleporting = false;
        this.getActor().gfx0(-1);
        this.getActor().startAnimation(65535);
        this.getActor().getPlayerAction().canWalk(true);
    }

    public BreakTeleportTabletAction(Player attachment, int itemId, int itemAmount, int itemSlot) {
        super(attachment, 3, itemId, itemAmount, itemSlot);
    }
}

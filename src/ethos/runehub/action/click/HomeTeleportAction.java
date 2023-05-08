package ethos.runehub.action.click;

import com.google.common.base.Preconditions;
import ethos.Config;
import ethos.Server;
import ethos.event.Event;
import ethos.model.players.Player;
import ethos.runehub.content.instance.BossArenaInstanceController;
import ethos.runehub.content.instance.impl.rift.RiftInstanceController;
import ethos.util.PreconditionUtils;
import org.runehub.api.util.math.geometry.Point;

import java.util.logging.Logger;

public abstract class HomeTeleportAction extends Event<Player> {

    public static final long TELEPORT_DELAY = 30 * 60000L;

    protected abstract void onActionStart();

    protected abstract void onActionStop();

    protected abstract void onTick();

    protected abstract void onUpdate();

    protected void validate() {
//        if (this.getActor().getAttributes().getInstanceId() != -1) {
//            if (BossArenaInstanceController.getInstance().getInstance(this.getActor().getAttributes().getInstanceId()) != null) {
//                Preconditions.checkArgument(PreconditionUtils.isFalse((this.getActor().getAttributes().getInstanceId() != -1 && BossArenaInstanceController.getInstance().getInstance(this.getActor().getAttributes().getInstanceId())
//                        .getArea().contains(new Point(this.getActor().absX, this.getActor().absY)))), "You must use the book to exit.");
//            }
//        }
        Preconditions.checkArgument(PreconditionUtils.isFalse(this.getActor().getAttributes().getActiveInstance() != null &&
                this.getActor().getAttributes().getActiveInstance().getArea().contains(new Point(this.getActor().absX, this.getActor().absY))),"You can not teleport out of this instance.");
        Preconditions.checkArgument(PreconditionUtils.isFalse(this.getActor().getAttributes().isActionLocked()), "Please finish what you are doing.");
        Preconditions.checkArgument(PreconditionUtils.isFalse(this.getActor().isInHouse),"Please use the house settings to leave your house");
        Preconditions.checkArgument(PreconditionUtils.isFalse(this.getActor().teleTimer > 0),"A magic force stops you from teleporting.");
        Preconditions.checkArgument(PreconditionUtils.isFalse(this.getActor().wildLevel > Config.NO_TELEPORT_WILD_LEVEL),"You can't teleport above " + Config.NO_TELEPORT_WILD_LEVEL + " in the wilderness.");
        Preconditions.checkArgument(PreconditionUtils.notNull(this.getActor()), "Actor is Null");
        Preconditions.checkArgument(PreconditionUtils.notNull(this.getActor().getSession()), "Session is Null");
        Preconditions.checkArgument(PreconditionUtils.isFalse(this.getActor().disconnected), "Actor is Disconnected");
        Preconditions.checkArgument(this.hasBeen(this.getActor().getContext().getPlayerSaveData().getLastHomeTeleportTimestamp() + getTeleportDelay(), System.currentTimeMillis()),
                "You must wait another "
                        + this.getMSAsMinutes(
                        this.timeBetweenMs(
                                this.getActor().getContext().getPlayerSaveData().getLastHomeTeleportTimestamp() + getTeleportDelay(),
                                System.currentTimeMillis()
                                ))
                        + " minutes before doing this.");
    }


    protected long getTeleportDelay() {
        return (long) (TELEPORT_DELAY - (TELEPORT_DELAY * this.getActor().getTeleportRechargeReduction()));
    }


    protected boolean checkPrerequisites() {
        try {
            this.validate();
        } catch (Exception e) {
            e.printStackTrace();
            this.getActor().sendMessage(e.getMessage());
            this.stop();
            return false;
        }
        return true;
    }

    @Override
    public void execute() {
        Logger.getGlobal().fine("Executing Event Tick");
        this.checkPrerequisites();
        this.onTick();
        this.stop();
    }

    @Override
    public void stop() {
        Logger.getGlobal().fine("Stopping Event");
        super.stop();
        this.onActionStop();
        if (attachment != null) {
            this.getActor().stopAnimation();
        }
    }

    @Override
    public void initialize() {
        Logger.getGlobal().fine("Starting Event");
        super.initialize();
        Server.getEventHandler().stop("skillAction");
        if (this.checkPrerequisites())
            this.onActionStart();
    }

    @Override
    public void update() {
        Logger.getGlobal().fine("Updating Event Tick");
        super.update();
        this.checkPrerequisites();
        this.onUpdate();
    }

    private long timeBetweenMs(long timestamp1, long timestamp2) {
        return timestamp1 - timestamp2;
    }

    private int getMSAsMinutes(long ms) {
        return (int) (ms / 60000);
    }

    private boolean hasBeen(long timeMS, long timestamp) {
        return timestamp > timeMS;
    }

    protected Player getActor() {
        return this.getAttachment();
    }

    public HomeTeleportAction(Player attachment, int ticks) {
        super("teleport",attachment, ticks);
    }
}

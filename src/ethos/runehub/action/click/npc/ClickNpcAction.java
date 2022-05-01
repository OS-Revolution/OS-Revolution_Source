package ethos.runehub.action.click.npc;

import com.google.common.base.Preconditions;
import ethos.Server;
import ethos.event.Event;
import ethos.model.players.Player;
import ethos.util.PreconditionUtils;

import java.util.logging.Logger;

public abstract class ClickNpcAction extends Event<Player> {

    protected abstract void onActionStart();

    protected abstract void onActionStop();

    protected abstract void onTick();

    protected abstract void onUpdate();

    protected void validate() {

    }

    protected void validateNode() {
        Preconditions.checkArgument(PreconditionUtils.isFalse(Server.npcHandler.getNPCs()[npcIndex].isDead),
                "This npc does not exist.");
    }

    protected boolean checkPrerequisites() {
        Logger.getGlobal().fine("Checking Prerequisites");
        try {
            this.validate();
            Preconditions.checkArgument(PreconditionUtils.isFalse(this.getActor().getAttributes().isActionLocked()), "Please finish what you are doing.");
            this.validateNode();
            canPerform = true;
        } catch (Exception e) {
            canPerform = false;
            this.getActor().sendMessage(e.getMessage());
            this.stop();
            return false;
        }
        return true;
    }

    @Override
    public void execute() {
        if(canPerform) {
            Logger.getGlobal().fine("Executing Event Tick");
            this.checkPrerequisites();
            this.onTick();
        }
    }

    @Override
    public void stop() {
        Logger.getGlobal().fine("Stopping Event");
        super.stop();
        this.onActionStop();
        if (attachment != null) {
            this.getActor().stopAnimation();
//            this.getActor().getAttributes().setInteractingWithNodeId(0);
        }
    }

    @Override
    public void initialize() {
        Logger.getGlobal().fine("Starting Event");
        super.initialize();
        if (this.checkPrerequisites()) {
            this.onActionStart();
        }
    }

    protected Player getActor() {
        return this.getAttachment();
    }

    public int getNpcId() {
        return npcId;
    }

    public int getNpcIndex() {
        return npcIndex;
    }

    public ClickNpcAction(Player attachment, int ticks, int npcId, int npcIndex) {
        super(attachment, ticks);
        this.npcId = npcId;
        this.npcIndex = npcIndex;
    }

    private final int npcId, npcIndex;
    private boolean canPerform;
}

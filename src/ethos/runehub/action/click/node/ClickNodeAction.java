package ethos.runehub.action.click.node;

import com.google.common.base.Preconditions;
import ethos.Server;
import ethos.event.Event;
import ethos.model.players.Player;
import ethos.util.PreconditionUtils;

import java.util.logging.Logger;

public abstract class ClickNodeAction extends Event<Player> {

    protected abstract void onActionStart();

    protected abstract void onActionStop();

    protected abstract void onTick();

    protected abstract void onUpdate();

    protected void validate() {

    }

    protected void validateNode() {
        Preconditions.checkArgument(PreconditionUtils.isFalse(Server.getGlobalObjects().exists(nodeId, nodeX, nodeY)),
                "This node does not exist.");
    }

    protected boolean checkPrerequisites() {
        try {
            this.validate();
            this.validateNode();
        } catch (Exception e) {
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
        if (this.checkPrerequisites())
            this.onActionStart();
    }

    protected Player getActor() {
        return this.getAttachment();
    }

    public int getNodeId() {
        return nodeId;
    }

    public int getNodeX() {
        return nodeX;
    }

    public int getNodeY() {
        return nodeY;
    }

    public ClickNodeAction(Player attachment, int ticks, int nodeId, int nodeX, int nodeY) {
        super(attachment, ticks);
        this.nodeId = nodeId;
        this.nodeX = nodeX;
        this.nodeY = nodeY;
    }

    private final int nodeId, nodeX, nodeY;
}

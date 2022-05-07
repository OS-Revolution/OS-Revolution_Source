package ethos.runehub.action.click.node.impl.first;

import ethos.model.players.Player;
import ethos.runehub.action.click.node.ClickNodeAction;
import ethos.runehub.entity.node.door.DoorController;
import ethos.runehub.skill.support.thieving.action.StealFromStallAction;

import java.util.List;

public class ClickStallAction extends ClickNodeAction {

    @Override
    protected void validateNode() {

    }

    @Override
    protected void onActionStart() {

    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {
        this.getActor().getSkillController().getThieving().train(new StealFromStallAction(this.getActor(),this.getNodeId(),this.getNodeX(),this.getNodeY(),nodeZ));
        this.stop();
    }

    @Override
    protected void onUpdate() {

    }

    public ClickStallAction(Player attachment, int nodeId, int nodeX, int nodeY, int nodeZ) {
        super(attachment, 1, nodeId, nodeX, nodeY);
        this.nodeZ = nodeZ;
    }

    private final int nodeZ;
}

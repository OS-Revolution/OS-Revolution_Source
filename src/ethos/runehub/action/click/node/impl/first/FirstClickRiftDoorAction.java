package ethos.runehub.action.click.node.impl.first;

import ethos.model.players.Player;
import ethos.runehub.action.click.node.ClickNodeAction;
import ethos.runehub.entity.node.door.DoorController;

public class FirstClickRiftDoorAction extends ClickNodeAction {

    @Override
    protected void onActionStart() {

    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {
        DoorController.getInstance().openDoorForPlayer(this.getActor(), this.getNodeId(), this.getNodeX(), this.getNodeY());
        this.stop();
    }

    @Override
    protected void onUpdate() {

    }

    public FirstClickRiftDoorAction(Player attachment, int nodeId, int nodeX, int nodeY, int nodeZ) {
        super(attachment, 2, nodeId, nodeX, nodeY);
        this.nodeZ = nodeZ;
    }

    private final int nodeZ;
}

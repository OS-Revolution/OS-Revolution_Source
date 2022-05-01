package ethos.runehub.action.click.node.impl.first;

import ethos.model.players.Player;
import ethos.runehub.action.click.node.ClickNodeAction;
import ethos.runehub.entity.node.door.DoorController;

import java.util.List;

public class CloseDoorAction extends ClickNodeAction {

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
        if(this.getNodeX() == 3097 && this.getNodeY() == 3226) {
            DoorController.getInstance().closeDoorForPlayer(this.getActor(), this.getNodeId(), this.getNodeX(), this.getNodeY());
        } else if(List.of(4963).contains(this.getNodeId())) {
            DoorController.getInstance().closeDoorForPlayer(this.getActor(), this.getNodeId(), this.getNodeX(), this.getNodeY());
        } else {
            DoorController.getInstance().closeDoorForEveryone(this.getActor(), this.getNodeId(), this.getNodeX(), this.getNodeY());
        }
        this.stop();
    }

    @Override
    protected void onUpdate() {

    }

    public CloseDoorAction(Player attachment, int nodeId, int nodeX, int nodeY, int nodeZ) {
        super(attachment, 1, nodeId, nodeX, nodeY);
        this.nodeZ = nodeZ;
    }

    private final int nodeZ;
}

package ethos.runehub.action.click.node.impl.first;

import ethos.model.players.Player;
import ethos.runehub.action.click.node.ClickNodeAction;
import ethos.runehub.content.rift.RiftFloor;

public class FirstClickOpenRiftPortal extends ClickNodeAction {


    @Override
    protected void onActionStart() {

    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {
        if (this.getActor().getAttributes().getRift() == null || this.getActor().getAttributes().getRift().getFloors().isEmpty()) {
            this.getActor().getAttributes().getRift().start(this.getActor());
        } else if(this.getActor().getAttributes().getRift() != null && !this.getActor().getAttributes().getRift().getFloors().isEmpty()) {
            this.getActor().getAttributes().getRift().reenterRift(this.getActor());
        }
        this.stop();
    }

    @Override
    protected void onUpdate() {

    }

    public FirstClickOpenRiftPortal(Player attachment, int nodeId, int nodeX, int nodeY) {
        super(attachment, 1, nodeId, nodeX, nodeY);
    }
}

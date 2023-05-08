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
        if (this.getActor().getAttributes().getJobController() == null || this.getActor().getAttributes().getRiftInstance().getFloors().isEmpty()) {
            this.getActor().getAttributes().getRiftInstance().start(this.getActor());
        } else if(this.getActor().getAttributes().getRiftInstance() != null && !this.getActor().getAttributes().getRiftInstance().getFloors().isEmpty()) {
            this.getActor().getAttributes().getRiftInstance().reenterRift(this.getActor());
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

package ethos.runehub.action.click.node.impl;

import ethos.model.players.Player;
import ethos.runehub.action.click.node.ClickNodeAction;

public class LadderAction extends ClickNodeAction {

    @Override
    protected void onActionStart() {
        if (this.getNodeId() == 10)
            this.getActor().startAnimation(827);
        else if (this.getNodeId() == 11)
            this.getActor().startAnimation(828);
//        this.getActor().sendMessage("Welcome to the $Hall $of $Heroes!");
//        Region.getWorldObject(this.getNodeId(), this.getNodeX(), this.getNodeY(), this.getActor().height).get()

    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {
        if (this.getNodeId() == 11) {
            this.getActor().getPA().movePlayer(this.getNodeX(), this.getNodeY()+1, nodeZ + 1);
        } else if (this.getNodeId() == 10) {
            this.getActor().getPA().movePlayer(this.getNodeX(), this.getNodeY()-1, nodeZ - 1);
        }
        this.stop();
    }

    @Override
    protected void onUpdate() {
    }

    public LadderAction(Player attachment, int nodeId, int nodeX, int nodeY, int nodeZ) {
        super(attachment, 2, nodeId, nodeX, nodeY);
        this.nodeZ = nodeZ;
    }

    private final int nodeZ;

}

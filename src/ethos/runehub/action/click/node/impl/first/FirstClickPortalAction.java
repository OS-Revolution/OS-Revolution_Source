package ethos.runehub.action.click.node.impl.first;

import ethos.model.players.Player;
import ethos.runehub.action.click.node.ClickNodeAction;

public class FirstClickPortalAction extends ClickNodeAction {

    @Override
    protected void onActionStart() {

    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {
        this.getActor().getPA().movePlayer(teleportX,teleportY,teleportZ);
        this.stop();
    }

    @Override
    protected void onUpdate() {

    }

    public FirstClickPortalAction(Player attachment, int nodeId, int nodeX, int nodeY, int nodeZ, int teleportX, int teleportY, int teleportZ) {
        super(attachment, 1, nodeId, nodeX, nodeY);
        this.nodeZ = nodeZ;
        this.teleportX = teleportX;
        this.teleportY = teleportY;
        this.teleportZ = teleportZ;
        attachment.objectDistance = 2;
    }

    private final int nodeZ, teleportX, teleportY, teleportZ;
}

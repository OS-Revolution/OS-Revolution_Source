package ethos.runehub.entity.player.action.impl.node;

import ethos.model.players.Player;

public class ClickClosedManholeNodeAction extends ClickNodeAction{

    @Override
    public void perform() {
//        final Optional<WorldObject> worldObjectOptional = Region.getWorldObject(nodeId,nodeX,nodeY,player.heightLevel);
            player.getPA().checkObjectSpawn(882, nodeX, nodeY, 3, 10);
    }

    public ClickClosedManholeNodeAction(Player player, int nodeX, int nodeY, int nodeId) {
        super(player, nodeX, nodeY, nodeId);
    }
}

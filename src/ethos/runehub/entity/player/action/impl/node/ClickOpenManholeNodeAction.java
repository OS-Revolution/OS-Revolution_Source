package ethos.runehub.entity.player.action.impl.node;

import ethos.model.players.Player;

public class ClickOpenManholeNodeAction extends ClickNodeAction{

    @Override
    public void perform() {
//        final Optional<WorldObject> worldObjectOptional = Region.getWorldObject(nodeId,nodeX,nodeY,player.heightLevel);
            player.getPA().movePlayer(3237 ,9858,0);
            player.startAnimation(828);
            player.sendMessage("You enter the $Draynor_Sewers");
    }

    public ClickOpenManholeNodeAction(Player player, int nodeX, int nodeY, int nodeId) {
        super(player, nodeX, nodeY, nodeId);
    }
}

package ethos.runehub.entity.player.action.impl.node;

import ethos.model.players.Player;
import ethos.runehub.entity.player.action.impl.node.ClickNodeAction;

public class RemoveLecternAction extends ClickNodeAction {


    @Override
    public void perform() {
        player.getContext().getPlayerSaveData().setLecternHotspot(15420);
        player.getPA().checkObjectSpawn(
                15420,
                3092,
                3223,
                1,
                10
        );
    }

    public RemoveLecternAction(Player player, int nodeX, int nodeY, int nodeId) {
        super(player, nodeX, nodeY, nodeId);
    }
}

package ethos.runehub.entity.player.action.impl.node;

import ethos.model.players.Player;
import ethos.runehub.entity.player.action.impl.node.ClickNodeAction;

public class FirstClickFishingPlatformBoatExit extends ClickNodeAction {


    @Override
    public void perform() {
        player.getPA().movePlayer(3081,3211);
    }

    public FirstClickFishingPlatformBoatExit(Player player, int nodeX, int nodeY, int nodeId) {
        super(player, nodeX, nodeY, nodeId);
        //2615 3440 boat to platform 3081 3211 from platform
    }
}

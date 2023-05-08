package ethos.runehub.entity.player.action;

import ethos.model.players.Player;
import ethos.runehub.content.instance.BossArenaInstanceController;
import ethos.runehub.entity.merchant.MerchantCache;
import ethos.runehub.entity.player.action.impl.node.*;

import java.util.logging.Logger;

public class SecondClickNodeActionFactory {

    public static ClickNodeAction getAction(Player player, int nodeX, int nodeY, int nodeId) {
        Logger.getGlobal().fine("Second Click Action - " + nodeId + " " + nodeX + " " + nodeY);
        switch (nodeId) {
            case 7528:
                return new ClickNodeAction(player,nodeX,nodeY,nodeId) {
                    @Override
                    public void perform() {
                        MerchantCache.getInstance().read(nodeId).openShop(player);
                    }
                };
            case 6799:
            case 878:
            case 13542:
            case 15468:
            case 11017:
                return new SecondClickSkillStationAction(player, nodeX, nodeY, nodeId);
            case 11398:
                 return new ClickNodeAction(player,nodeX,nodeY,nodeId) {
                @Override
                public void perform() {
                    player.getPA().startLeverTeleport(3107 ,3228, 0);
                }
            };
            case 13620:
                return new ClickNodeAction(player,nodeX,nodeY,nodeId) {
                    @Override
                    public void perform() {
                        BossArenaInstanceController.getInstance().closeInstanceForPlayer(player);
                    }
                };
            case 20926:
            case 20927:
            case 20928:
            case 20929:
                return new SecondClickFishingSpotAction(player,nodeX,nodeY,nodeId);
        }
        return null;
    }


}

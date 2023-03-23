package ethos.runehub.entity.player.action;

import ethos.model.players.Player;
import ethos.runehub.entity.player.action.impl.ClickItemAction;
import ethos.runehub.entity.player.action.impl.item.FirstClickEnchantedGemAction;
import ethos.runehub.entity.player.action.impl.item.SecondClickEnchantedGemAction;
import ethos.runehub.entity.player.action.impl.item.SecondClickGoldAccumulator;
import ethos.runehub.entity.player.action.impl.item.SecondClickWoodcuttingSkillRing;
import ethos.runehub.entity.player.action.impl.node.FirstClickPortableSkillingStation;

import java.util.logging.Logger;

public class SecondClickItemActionFactory {

    public static ClickItemAction getAction(Player player, int nodeX, int nodeY, int nodeId) {
        Logger.getGlobal().fine("Second Click Action - " + nodeId + " " + nodeX + " " + nodeY);
        switch (nodeId) {
            case 8122:
            case 8123:
            case 8124:
            case 8125:
                return new SecondClickWoodcuttingSkillRing(player, nodeX, nodeY, nodeId);
            case 4155:
                return new SecondClickEnchantedGemAction(player,nodeX,nodeY,nodeId);
            case 8411:
            case 8412:
            case 8413:
                return new SecondClickGoldAccumulator(player,nodeX,nodeY,nodeId);
        }
        return null;
    }


}

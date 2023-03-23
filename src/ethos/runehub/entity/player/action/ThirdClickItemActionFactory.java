package ethos.runehub.entity.player.action;

import ethos.model.players.Player;
import ethos.runehub.entity.player.action.impl.ClickItemAction;
import ethos.runehub.entity.player.action.impl.item.FirstClickEnchantedGemAction;
import ethos.runehub.entity.player.action.impl.item.SecondClickWoodcuttingSkillRing;
import ethos.runehub.entity.player.action.impl.item.ThirdClickEnchantedGemAction;
import ethos.runehub.entity.player.action.impl.item.ThirdClickWoodcuttingSkillRing;

import java.util.logging.Logger;

public class ThirdClickItemActionFactory {

    public static ClickItemAction getAction(Player player, int nodeX, int nodeY, int nodeId) {
        Logger.getGlobal().fine("Third Click Action - " + nodeId + " " + nodeX + " " + nodeY);
        switch (nodeId) {
            case 8122:
            case 8123:
            case 8124:
                return new ThirdClickWoodcuttingSkillRing(player, nodeX, nodeY, nodeId);
            case 4155:
                return new ThirdClickEnchantedGemAction(player,nodeX,nodeY,nodeId);
        }
        return null;
    }


}

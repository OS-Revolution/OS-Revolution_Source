package ethos.runehub.entity.player.action;

import ethos.model.players.Player;
import ethos.runehub.entity.player.action.impl.node.ClickNodeAction;
import ethos.runehub.entity.player.action.impl.node.RemoveLecternAction;
import ethos.runehub.entity.player.action.impl.node.SecondClickSkillStationAction;
import ethos.runehub.entity.player.action.impl.node.ThirdClickSkillStationAction;

import java.util.logging.Logger;

public class ThirdClickNodeActionFactory {

    public static ClickNodeAction getAction(Player player, int nodeX, int nodeY, int nodeId) {
        Logger.getGlobal().fine("Third Click Action - " + nodeId + " " + nodeX + " " + nodeY);
        switch (nodeId) {
            case 6799:
            case 878:
            case 13542:
            case 15468:
            case 11017:
                return new ThirdClickSkillStationAction(player, nodeX, nodeY, nodeId);
        }
        return null;
    }


}

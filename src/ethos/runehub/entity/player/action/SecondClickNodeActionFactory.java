package ethos.runehub.entity.player.action;

import ethos.model.players.Player;
import ethos.runehub.entity.player.action.impl.ClickNodeAction;
import ethos.runehub.entity.player.action.impl.FirstClickFishingSpotAction;
import ethos.runehub.entity.player.action.impl.SecondClickFishingSpotAction;

import java.util.logging.Logger;

public class SecondClickNodeActionFactory {

    public static ClickNodeAction getAction(Player player, int nodeX, int nodeY, int nodeId) {
        Logger.getGlobal().fine("Second Click Action - " + nodeId + " " + nodeX + " " + nodeY);
        switch (nodeId) {
            case 20926:
            case 20927:
            case 20928:
            case 20929:
                return new SecondClickFishingSpotAction(player,nodeX,nodeY,nodeId);
        }
        return null;
    }


}

package ethos.runehub.entity.player.action;

import ethos.model.players.Player;
import ethos.runehub.entity.player.action.impl.FirstClickFishingPlatformBoat;
import ethos.runehub.entity.player.action.impl.FirstClickFishingPlatformBoatExit;
import ethos.runehub.entity.player.action.impl.FirstClickFishingSpotAction;
import ethos.runehub.entity.player.action.impl.ClickNodeAction;

import java.util.logging.Logger;

public class FirstClickNodeActionFactory {

    public static ClickNodeAction getAction(Player player, int nodeX, int nodeY, int nodeId) {
        Logger.getGlobal().fine("First Click Action - " + nodeId + " " + nodeX + " " + nodeY);
        switch (nodeId) {
            case 20926:
            case 20927:
            case 20928:
            case 20929:
            case 20930:
                return new FirstClickFishingSpotAction(player,nodeX,nodeY,nodeId);
            case 30377:
                return new FirstClickFishingPlatformBoatExit(player, nodeX, nodeY, nodeId);
            case 30376:
                return new FirstClickFishingPlatformBoat(player, nodeX, nodeY, nodeId);
        }
        return null;
    }


}

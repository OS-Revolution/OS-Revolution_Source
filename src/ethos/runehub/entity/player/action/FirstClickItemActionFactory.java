package ethos.runehub.entity.player.action;

import ethos.model.players.Player;
import ethos.runehub.entity.player.action.impl.ClickItemAction;
import ethos.runehub.entity.player.action.impl.node.FirstClickPortableSkillingStation;

import java.util.logging.Logger;

public class FirstClickItemActionFactory {

    public static ClickItemAction getAction(Player player, int nodeX, int nodeY, int nodeId) {
        Logger.getGlobal().fine("Fourth Click Action - " + nodeId + " " + nodeX + " " + nodeY);
        switch (nodeId) {
            case 8554:
            case 8528:
            case 8530:
            case 8532:
            case 8534:
                return new FirstClickPortableSkillingStation(player, nodeX, nodeY, nodeId);
        }
        return null;
    }


}

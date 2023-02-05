package ethos.runehub.entity.player.action;

import ethos.model.players.Player;
import ethos.runehub.entity.player.action.impl.node.ClickNodeAction;
import ethos.runehub.entity.player.action.impl.node.RemoveLecternAction;

import java.util.logging.Logger;

public class FourthClickNodeActionFactory {

    public static ClickNodeAction getAction(Player player, int nodeX, int nodeY, int nodeId) {
        Logger.getGlobal().fine("Fourth Click Action - " + nodeId + " " + nodeX + " " + nodeY);
        switch (nodeId) {
            case 13642:
            case 13643:
            case 13644:
            case 13645:
            case 13646:
            case 13647:
            case 13648:
            case 18245:
            case 5260:
                return new RemoveLecternAction(player, nodeX, nodeY, nodeId);
        }
        return null;
    }


}

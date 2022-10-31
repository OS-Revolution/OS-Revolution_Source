package ethos.runehub.entity.player.action;

import ethos.model.players.Player;
import ethos.runehub.entity.player.action.impl.*;

import java.util.logging.Logger;

public class FirstClickNPCActionFactory {

    public static ClickNPCAction getAction(Player player, int nodeX, int nodeY, int nodeId, int npcIndex) {
        Logger.getGlobal().fine("First Click NPC Action - " + nodeId + " " + nodeX + " " + nodeY);
        switch (nodeId) {
            case 7727:
                return new FirstClickOpenShopAction(player, nodeX, nodeY, nodeId,npcIndex);
        }
        return null;
    }


}

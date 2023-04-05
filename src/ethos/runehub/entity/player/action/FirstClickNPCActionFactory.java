package ethos.runehub.entity.player.action;

import ethos.model.players.Player;
import ethos.runehub.entity.player.action.impl.*;
import ethos.runehub.entity.player.action.impl.npc.FirstClickMartinAction;

import java.util.logging.Logger;

public class FirstClickNPCActionFactory {

    public static ClickNPCAction getAction(Player player, int nodeX, int nodeY, int nodeId, int npcIndex) {
        Logger.getGlobal().fine("First Click NPC Action - " + nodeId + " " + nodeX + " " + nodeY);
        switch (nodeId) {
            case 7727:
            case 5567:
            case 506:
                return new ClickOpenShopAction(player, nodeX, nodeY, nodeId, npcIndex);
            case 637:
            case 1328:
            case 401:
            case 1329:
                return new FirstClickDialogueAction(player,nodeX,nodeY,nodeId,npcIndex);
            case 5832:
                return new FirstClickMartinAction(player,nodeX,nodeY,nodeId,npcIndex);

        }
        return null;
    }


}

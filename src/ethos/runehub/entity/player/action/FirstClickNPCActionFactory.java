package ethos.runehub.entity.player.action;

import ethos.model.players.Player;
import ethos.runehub.entity.player.action.impl.*;
import ethos.runehub.entity.player.action.impl.npc.*;

import java.util.logging.Logger;

public class FirstClickNPCActionFactory {

    public static ClickNPCAction getAction(Player player, int nodeX, int nodeY, int nodeId, int npcIndex) {
        Logger.getGlobal().fine("First Click NPC Action - " + nodeId + " " + nodeX + " " + nodeY);
        switch (nodeId) {
            case 4274:
                return new FirstClickLeelaAction(player,nodeX,nodeY,nodeId,npcIndex);
            case 1430:
                return new FirstClickShipwrightAction(player,nodeX,nodeY,nodeId,npcIndex);
            case 6999:
                return new FirstClickPortMasterAction(player,nodeX,nodeY,nodeId,npcIndex);
            case 7727:
            case 5567:
            case 506:
                return new ClickOpenShopAction(player, nodeX, nodeY, nodeId, npcIndex);
            case 637:
            case 1328:
            case 401:
            case 1329:
            case 1039:
            case 306:
                return new FirstClickDialogueAction(player,nodeX,nodeY,nodeId,npcIndex);
            case 5832:
                return new FirstClickMartinAction(player,nodeX,nodeY,nodeId,npcIndex);
            case 5422:
                return new FirstClickSawmillOperatorAction(player,nodeX,nodeY,nodeId,npcIndex);
            case 2658:
                return new FirstClickHeadChefAction(player,nodeX,nodeY,nodeId,npcIndex);

        }
        return null;
    }


}

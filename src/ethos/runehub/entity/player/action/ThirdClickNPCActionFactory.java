package ethos.runehub.entity.player.action;

import ethos.model.players.Player;
import ethos.runehub.entity.player.action.impl.ClickNPCAction;
import ethos.runehub.entity.player.action.impl.ClickOpenShopAction;
import ethos.runehub.skill.support.thieving.action.PickpocketAction;
import ethos.runehub.ui.impl.SlayerKnowledgeRewardUI;

import java.util.logging.Logger;

public class ThirdClickNPCActionFactory {

    public static ClickNPCAction getAction(Player player, int nodeX, int nodeY, int nodeId, int npcIndex) {
        Logger.getGlobal().fine("Third Click NPC Action - " + nodeId + " " + nodeX + " " + nodeY);
        switch (nodeId) {
            case 401:
            case 402:
            case 403:
            case 404:
            case 405:
            case 6797:
                return new ClickOpenShopAction(player,nodeX,nodeY,nodeId,npcIndex);
            case 6988:

                return new ClickNPCAction(player,nodeX,nodeY,nodeId,npcIndex) {
                    @Override
                    public void perform() {
                        player.getSkillController().getThieving().train(new PickpocketAction(
                                player,nodeId,npcIndex
                        ));
                    }
                };
        }
        return null;
    }


}

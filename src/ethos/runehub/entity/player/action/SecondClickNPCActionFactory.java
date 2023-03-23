package ethos.runehub.entity.player.action;

import ethos.model.players.Player;
import ethos.runehub.entity.player.action.impl.ClickNPCAction;
import ethos.runehub.skill.support.thieving.action.PickpocketAction;

import java.util.logging.Logger;

public class SecondClickNPCActionFactory {

    public static ClickNPCAction getAction(Player player, int nodeX, int nodeY, int nodeId, int npcIndex) {
        Logger.getGlobal().fine("Second Click NPC Action - " + nodeId + " " + nodeX + " " + nodeY);
        switch (nodeId) {
            case 401:
                return new ClickNPCAction(player,nodeX,nodeY,nodeId,npcIndex) {
                    @Override
                    public void perform() {
                        player.getSkillController().getSlayer().assignTask(nodeId);
                    }
                };
            case 6988:
            case 3086:
            case 6992:
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

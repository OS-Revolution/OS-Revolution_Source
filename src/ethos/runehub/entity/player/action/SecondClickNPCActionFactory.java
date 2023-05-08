package ethos.runehub.entity.player.action;

import ethos.model.players.Player;
import ethos.runehub.entity.player.action.impl.ClickNPCAction;
import ethos.runehub.entity.player.action.impl.ClickOpenShopAction;
import ethos.runehub.skill.support.thieving.action.PickpocketAction;
import ethos.runehub.ui.impl.JourneySelectionUI;

import java.util.logging.Logger;

public class SecondClickNPCActionFactory {

    public static ClickNPCAction getAction(Player player, int nodeX, int nodeY, int nodeId, int npcIndex) {
        Logger.getGlobal().fine("Second Click NPC Action - " + nodeId + " " + nodeX + " " + nodeY);
        switch (nodeId) {
            case 306:
                return new ClickNPCAction(player,nodeX,nodeY,nodeId,npcIndex) {
                    @Override
                    public void perform() {
                        player.sendUI(new JourneySelectionUI(player));
                    }
                };
            case 1039:
                return new ClickOpenShopAction(player,nodeX,nodeY,nodeId,npcIndex);
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

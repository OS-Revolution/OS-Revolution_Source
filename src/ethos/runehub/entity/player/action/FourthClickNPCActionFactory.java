package ethos.runehub.entity.player.action;

import ethos.model.players.Player;
import ethos.runehub.entity.merchant.MerchantCache;
import ethos.runehub.entity.player.action.impl.ClickNPCAction;
import ethos.runehub.ui.impl.SlayerKnowledgeRewardUI;

import java.util.logging.Logger;

public class FourthClickNPCActionFactory {

    public static ClickNPCAction getAction(Player player, int nodeX, int nodeY, int nodeId, int npcIndex) {
        Logger.getGlobal().fine("Fourth Click NPC Action - " + nodeId + " " + nodeX + " " + nodeY);
        switch (nodeId) {
            case 6999:
                return new ClickNPCAction(player,nodeX,nodeY,nodeId,npcIndex) {
                    @Override
                    public void perform() {
                        player.getPA().closeAllWindows();
                        player.getOutStream().createFrame(30);
                    }
                };
            case 401:
                return new ClickNPCAction(player,nodeX,nodeY,nodeId,npcIndex) {
                    @Override
                    public void perform() {
                        player.sendUI(new SlayerKnowledgeRewardUI(player));
                    }
                };
        }
        return null;
    }


}

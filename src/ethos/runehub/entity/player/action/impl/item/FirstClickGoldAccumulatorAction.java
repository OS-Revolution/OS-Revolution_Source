package ethos.runehub.entity.player.action.impl.item;

import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.entity.player.action.impl.ClickItemAction;

public class FirstClickGoldAccumulatorAction extends ClickItemAction {

    @Override
    public void perform() {
        boolean state = false;
        if (itemId == 8411) {
            player.getContext().getPlayerSaveData().setGoldAccumulatorActive(!player.getContext().getPlayerSaveData().isGoldAccumulatorActive());
            state = player.getContext().getPlayerSaveData().isGoldAccumulatorActive();
        } else if(itemId == 8412) {
            player.getContext().getPlayerSaveData().setAdvancedGoldAccumulatorActive(!player.getContext().getPlayerSaveData().isAdvancedGoldAccumulatorActive());
            state = player.getContext().getPlayerSaveData().isAdvancedGoldAccumulatorActive();
        }else if(itemId == 8413) {
            player.getContext().getPlayerSaveData().setMasterGoldAccumulatorActive(!player.getContext().getPlayerSaveData().isMasterGoldAccumulatorActive());
            state = player.getContext().getPlayerSaveData().isMasterGoldAccumulatorActive();
        }
        player.sendMessage("You toggle your @" + itemId + " " + RunehubUtils.getBooleanAsOnOrOff(state));
    }

    public FirstClickGoldAccumulatorAction(Player player, int playerX, int playerY, int itemId) {
        super(player, playerX, playerY, itemId);
    }
}

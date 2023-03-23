package ethos.runehub.entity.player.action.impl.item;

import ethos.model.players.Player;
import ethos.runehub.entity.player.action.impl.ClickItemAction;

public class SecondClickGoldAccumulator extends ClickItemAction {

    @Override
    public void perform() {
        if (itemId == 8411) {
            player.sendMessage("Your @" + itemId + " has accumulated #" + player.getContext().getPlayerSaveData().getGoldAccumulatorAccumulated() + " / #" + 1000000 + " coins.");
        } else if (itemId == 8412) {
            player.sendMessage("Your @" + itemId + " has accumulated #" + player.getContext().getPlayerSaveData().getAdvancedGoldAccumulatorAccumulated() + " coins.");
        } else if (itemId == 8413) {
            player.sendMessage("Your @" + itemId + " has generated #" + player.getContext().getPlayerSaveData().getMasterGoldAccumulatorAccumulated() + " jewels.");
        }
    }

    public SecondClickGoldAccumulator(Player player, int playerX, int playerY, int itemId) {
        super(player, playerX, playerY, itemId);
    }
}

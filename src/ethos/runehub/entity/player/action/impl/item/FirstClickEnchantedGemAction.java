package ethos.runehub.entity.player.action.impl.item;

import ethos.model.players.Player;
import ethos.runehub.entity.player.action.impl.ClickItemAction;

public class FirstClickEnchantedGemAction extends ClickItemAction {


    @Override
    public void perform() {
        System.out.println(this.getClass().getName());
    }

    public FirstClickEnchantedGemAction(Player player, int playerX, int playerY, int itemId) {
        super(player, playerX, playerY, itemId);
    }
}

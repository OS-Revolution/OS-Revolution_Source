package ethos.runehub.entity.player.action.impl;

import ethos.model.players.Player;
import ethos.runehub.entity.merchant.MerchantCache;

public class ClickOpenShopAction extends ClickNPCAction{

    @Override
    public void perform() {
        MerchantCache.getInstance().read(npcId).openShop(player);
    }

    public ClickOpenShopAction(Player player, int npcX, int npcY, int npcId, int npcIndex) {
        super(player, npcX, npcY, npcId, npcIndex);
    }
}

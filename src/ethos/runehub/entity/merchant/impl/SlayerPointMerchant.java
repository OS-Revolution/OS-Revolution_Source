package ethos.runehub.entity.merchant.impl;

import ethos.model.players.Player;
import ethos.runehub.entity.merchant.MerchandiseSlot;
import ethos.runehub.entity.merchant.Merchant;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.io.load.impl.LootTableLoader;

import java.util.List;
import java.util.stream.Collectors;

public class SlayerPointMerchant extends Merchant {

    @Override
    public boolean sellItemToPlayer(int itemId, int amount, int slot, Player player) {
        final int price = this.getPriceMerchantWillSellFor(itemId) * amount;
        if (amount <= this.getMerchandise().get(slot).getAmount()) {
            if (player.getSlayerSave().getSlayerPoints() >= price) {
                if (player.getItems().freeSlots() > (ItemIdContextLoader.getInstance().read(itemId).isStackable() ? 0 : amount)) {
                        player.getSlayerSave().subtractSlayerPoints(price);
                        player.getItems().addItem(itemId, amount);
                        player.getItems().resetItems(3823);
                        player.sendMessage("You bought #" + amount + " @" + itemId + " for #" + price + " $Slayer $points.");
//                    this.getMerchandiseSlot(itemId).setAmount(this.getMerchandiseSlot(itemId).getAmount() - amount);
                        this.updateShop(player);
                        return true;
                } else {
                    player.sendMessage("You do not have enough inventory space.");
                }
            } else {
                player.sendMessage("You need at least $" + price + " $Slayer $points to buy this.");
            }
        }
        return false;
    }

    @Override
    protected int getPriceMerchantWillSellFor(int itemId) {
        switch (itemId) {
            case 4155:
                return 0;
            case 8411:
                return 500;
            case 8412:
                return 1000;
            default:
                return 100;
        }
    }

    @Override
    public String getPriceForItemBeingBoughtFromShop(int itemId) {
        return "The shop will sell @" + itemId + " for #" + this.getPriceMerchantWillSellFor(itemId) + " Slayer points each.";
    }

    public SlayerPointMerchant() {
        super(LootTableLoader.getInstance().read(-1157382438987938368L).getLootTableEntries().stream()
                .map(lootTableEntry -> new MerchandiseSlot(lootTableEntry.getId(), lootTableEntry.getAmount().getMax(), false, 0.0D, ItemIdContextLoader.getInstance().read(lootTableEntry.getId()).isMembers()))
                .collect(Collectors.toList()),
                        -1, 401, "Slayer Point Shop", -1157382438987938368L, List.of());
    }
}

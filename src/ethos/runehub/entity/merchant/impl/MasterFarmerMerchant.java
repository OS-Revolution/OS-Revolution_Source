package ethos.runehub.entity.merchant.impl;

import ethos.model.players.Player;
import ethos.runehub.RunehubConstants;
import ethos.runehub.content.journey.JourneyStepType;
import ethos.runehub.entity.merchant.MerchandiseSlot;
import ethos.runehub.entity.merchant.Merchant;
import org.runehub.api.io.load.impl.ItemIdContextLoader;

import java.util.List;
import java.util.Optional;

public class MasterFarmerMerchant extends Merchant {

    @Override
    public int getPriceMerchantWillSellFor(int itemId) {
        int baseValue = ItemIdContextLoader.getInstance().read(itemId).getValue();
        switch (itemId) {
            case 6033:
                baseValue = 8;
                break;
            case 6035:
                baseValue = 12;
                break;
            case 21484:
                baseValue = 16;
                break;
            case 6036:
                baseValue = 15;
                break;
        }
        int stock = this.getMerchandiseSlot(itemId) == null ? 0 : this.getMerchandiseSlot(itemId).getAmount();
        double maxMarkupPrice = (baseValue * (130.0D - 3.0D * stock)) / 100.0D;
        double minMarkupPrice = (30.0D * baseValue) / 100.0D;
        double buyPrice = Math.round(Math.ceil(Math.max(maxMarkupPrice, minMarkupPrice)));

        return Math.toIntExact(Math.round(buyPrice));
    }

    @Override
    public int getPriceMerchantWillBuyFor(int itemId) {
        int baseValue = ItemIdContextLoader.getInstance().read(itemId).getValue();
        int stock = this.getMerchandiseSlot(itemId) == null ? 0 : this.getMerchandiseSlot(itemId).getAmount();
        int sellPrice = Math.toIntExact(Math.round(Math.ceil(((40 - 3 * Math.min(stock, 10)) / 100.0D) * baseValue)));
        return sellPrice;

    }

    protected void initializeStock() {
        this.getLootTable().getLootTableEntries().forEach(lootTableEntry -> {
            this.getMerchandise().add(new MerchandiseSlot(
                    lootTableEntry.getId(),
                    lootTableEntry.getAmount().getMax(),
                    false,
                    0.D,
                    false));
        });
        this.getMerchandise().addAll(RunehubConstants.GENERAL_STORE_ITEMS);
    }

    public boolean sellItemToPlayer(int itemId, int amount, int slot, Player player) {
        final int price = this.getPriceMerchantWillSellFor(itemId) * amount;
        if (amount <= this.getMerchandise().get(slot).getAmount()) {
            if (player.getItems().playerHasItem(this.getCurrencyId(), price)) {
                if (player.getItems().freeSlots() > (ItemIdContextLoader.getInstance().read(itemId).isStackable() ? 0 : amount)) {
                    player.getItems().deleteItem(this.getCurrencyId(), price);
                    player.getItems().addItem(itemId, amount);
                    player.getItems().resetItems(3823);
                    player.sendMessage("You bought #" + amount + " @" + itemId + " for #" + price + " @" + this.getCurrencyId());
                    player.getAttributes().getJourneyController().checkJourney(itemId,amount, JourneyStepType.BUY_ID_FROM_SHOP);
                    this.getMerchandiseSlot(itemId).setAmount(this.getMerchandiseSlot(itemId).getAmount() - amount);
                    this.updateShop(player);
                    return true;
                } else {
                    player.sendMessage("You do not have enough inventory space.");
                }
            } else {
                player.sendMessage("Come back when you're a little bit...richer!");
            }
        }
        return false;
    }

    public MasterFarmerMerchant() {
        super(6055, 5832, "Martin's Farming Supplies", 1564597973272841095L, List.of());
    }
}

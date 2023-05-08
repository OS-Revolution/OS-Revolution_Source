package ethos.runehub.entity.merchant.impl;

import ethos.model.players.Player;
import ethos.runehub.RunehubConstants;
import ethos.runehub.content.journey.JourneyStepType;
import ethos.runehub.entity.merchant.MerchandiseSlot;
import ethos.runehub.entity.merchant.Merchant;
import org.runehub.api.io.load.impl.ItemIdContextLoader;

import java.util.List;

public class JewelMerchant extends Merchant {

    @Override
    public int getPriceMerchantWillSellFor(int itemId) {
        int baseValue = ItemIdContextLoader.getInstance().read(itemId).getValue();
        switch (itemId) {
            case 6824:
                baseValue = 200;
                break;
            case 6823:
                baseValue = 150;
                break;
            case 6822:
                baseValue = 100;
                break;
            case 85:
            case 1464:
                baseValue = 50;
                break;
            case 19730:
            case 7411:
            case 6825:
                baseValue = 500;
                break;
            case 7478:
                baseValue = 2500;
                break;
            case 13190:
                baseValue = 5000;
                break;
            case 1459:
            case 8152:
                baseValue = 10000;
                break;
            case 1038:
            case 1040:
            case 1042:
            case 1044:
            case 1046:
            case 1048:
                baseValue = 150000;
                break;
        }
        return baseValue;
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
//                    this.getMerchandiseSlot(itemId).setAmount(this.getMerchandiseSlot(itemId).getAmount() - amount);
//                    this.updateShop(player);
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

    public JewelMerchant() {
        super(1459, 1039, "Jewel Merchant", 480140510972824701L, List.of());
    }
}

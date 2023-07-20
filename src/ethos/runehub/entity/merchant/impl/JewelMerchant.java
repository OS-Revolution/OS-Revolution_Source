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
            case 13190:
                return 8000;
            case 85:
                return 300;
            case 6825:
                return 260;
            case 6824:
                return 235;
            case 6823:
                return 160;
            case 6822:
                return 85;
            case 2396: //teleport charge scrolls worth 1 instant teleports
                return 15;
            case 7478:
                return 5000;
            case 3103:
                return 10;
            case 8115:
            case 8116:
            case 8117:
            case 8118:
            case 8119:
            case 8120:
            case 8121:
            case 8246:
            case 8247:
            case 8248:
            case 8380:
            case 8381:
            case 8382:
                return 40;
            case 8152:
                return 10000;
            case 3495:
                return 40;
            case 1464:
                return 30;
            case 5020:
            case 5021:
            case 5022:
            case 5023:
            case 8548:
                return 1500;
            case 19887:
                return 500;

        }
        return (int) Math.round(baseValue * 0.5714);
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
//        if (amount <= this.getMerchandise().get(slot).getAmount()) {
        if (player.getItems().playerHasItem(this.getCurrencyId(), price)) {
            if (player.getItems().freeSlots() > (ItemIdContextLoader.getInstance().read(itemId).isStackable() ? 0 : amount)) {
                player.getItems().deleteItem(this.getCurrencyId(), price);
                player.getItems().addItem(itemId, amount);
                player.getItems().resetItems(3823);
                player.sendMessage("You bought #" + amount + " @" + itemId + " for #" + price + " @" + this.getCurrencyId());
                player.getAttributes().getJourneyController().checkJourney(itemId, amount, JourneyStepType.BUY_ID_FROM_SHOP);
//                    this.getMerchandiseSlot(itemId).setAmount(this.getMerchandiseSlot(itemId).getAmount() - amount);
//                    this.updateShop(player);
                return true;
            } else {
                player.sendMessage("You do not have enough inventory space.");
            }
        } else {
            player.sendMessage("Come back when you're a little bit...richer!");
        }
//        }
        return false;
    }

    public JewelMerchant() {
        super(1459, 1039, "Jewel Merchant", 480140510972824701L, List.of());
    }
}

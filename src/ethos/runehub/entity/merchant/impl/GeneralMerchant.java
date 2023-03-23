package ethos.runehub.entity.merchant.impl;

import ethos.model.players.Player;
import ethos.runehub.RunehubConstants;
import ethos.runehub.entity.merchant.MerchandiseSlot;
import ethos.runehub.entity.merchant.Merchant;
import ethos.runehub.skill.Skill;
import ethos.runehub.skill.combat.magic.spell.Rune;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.io.load.impl.LootTableLoader;

import java.util.List;
import java.util.Optional;

public class GeneralMerchant extends Merchant {

    @Override
    public int getPriceMerchantWillSellFor(int itemId) {
        int baseValue = ItemIdContextLoader.getInstance().read(itemId).getValue();
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

    @Override
    protected void updateStock(int itemId, int amount) {
        if (this.getMerchandiseSlot(itemId) == null) {
            MerchandiseSlot merchandiseSlot = new MerchandiseSlot(itemId, amount, false, 0.0D, false);
            this.getMerchandise().add(merchandiseSlot);
            RunehubConstants.GENERAL_STORE_ITEMS.add(merchandiseSlot);
        } else {
            this.getMerchandiseSlot(itemId).setAmount(this.getMerchandiseSlot(itemId).getAmount() + amount);
            Optional<MerchandiseSlot> merchandise = RunehubConstants.GENERAL_STORE_ITEMS.stream().filter(merchandiseSlot -> merchandiseSlot.getItemId() == itemId).findAny();
            merchandise.ifPresent(merchandiseSlot -> merchandiseSlot.setAmount(merchandiseSlot.getAmount() + amount));
        }
    }

//    public boolean buyItemFromPlayer(int itemId, int amount, int slot, Player player) {
//        final int price = this.getPriceMerchantWillBuyFor(itemId) * amount;
//        if (player.getItems().playerHasItem(itemId, amount)) {
//            if (player.getItems().freeSlots() > (ItemIdContextLoader.getInstance().read(getCurrencyId()).isStackable() ? 0 : amount)) {
//                player.getItems().deleteItem2(itemId, amount);
//                player.getItems().addItem(this.getCurrencyId(), price);
//                player.getItems().resetItems(3823);
//                player.sendMessage("You sell your #" + amount + " @" + itemId + " in exchange for #"
//                        + price + " @" + this.getCurrencyId());
//
//                this.updateShop(player);
//                return true;
//            } else {
//                player.sendMessage("You do not have enough inventory space.");
//            }
//        } else {
//            player.sendMessage("You can't sell what you don't have.");
//        }
//        return false;
//    }

//    @Override
//    public boolean sellItemToPlayer(int itemId, int amount, int slot, Player player) {
//        final int price = this.getPriceMerchantWillSellFor(itemId) * amount;
//        if (amount <= this.getMerchandise().get(slot).getAmount()) {
//            if (player.getItems().playerHasItem(getCurrencyId(), price) || (itemId == 1459 && player.getItems().playerHasItem(995, price))) {
//                if (player.getItems().freeSlots() > (ItemIdContextLoader.getInstance().read(itemId).isStackable() ? 0 : amount)) {
//                    if (itemId == 1459) {
//                        player.getItems().deleteItem(995, price);
//                        player.sendMessage("You bought #" + amount + " @" + itemId + " for #" + price + " @" + 995);
//                    } else {
//                        player.getItems().deleteItem(getCurrencyId(), price);
//                        player.sendMessage("You bought #" + amount + " @" + itemId + " for #" + price + " @" + getCurrencyId());
//                    }
//                    player.getItems().addItem(itemId, amount);
//                    player.getItems().resetItems(3823);
//                    this.getMerchandiseSlot(itemId).setAmount(this.getMerchandiseSlot(itemId).getAmount() - amount);
//                    this.updateShop(player);
//                    return true;
//                } else {
//                    player.sendMessage("You do not have enough inventory space.");
//                }
//            } else {
//                player.sendMessage("Come back when you're a little bit...richer!");
//            }
//        }
//        return false;
//    }

    public GeneralMerchant() {
        super(995, 506, "General Store", 988149699123037161L, List.of(-1));
    }
}

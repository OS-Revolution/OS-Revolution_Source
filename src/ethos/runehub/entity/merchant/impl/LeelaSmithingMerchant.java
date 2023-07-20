package ethos.runehub.entity.merchant.impl;

import ethos.model.players.Player;
import ethos.runehub.RunehubConstants;
import ethos.runehub.entity.merchant.MerchandiseSlot;
import ethos.runehub.entity.merchant.Merchant;
import org.runehub.api.io.load.impl.ItemIdContextLoader;

import java.util.List;
import java.util.Optional;

public class LeelaSmithingMerchant extends Merchant {

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


    @Override
    public boolean buyItemFromPlayer(int itemId, int amount, int slot, Player player) {
        return false;
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

    @Override
    public boolean sellItemToPlayer(int itemId, int amount, int slot, Player player) {
        if (super.sellItemToPlayer(itemId,amount,slot,player)) {
            return true;
        }
        return false;
    }

    public LeelaSmithingMerchant() {
        super(995, 4274, "Leela's Weapons Cache", -7704328892551313893L, List.of(-1));
    }
}

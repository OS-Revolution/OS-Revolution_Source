package ethos.runehub.entity.merchant.impl;

import ethos.model.players.Player;
import ethos.runehub.entity.merchant.MerchandiseSlot;
import ethos.runehub.skill.Skill;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.io.load.impl.LootTableLoader;

import java.util.List;

public class CommodityMerchant extends RotatingStockMerchant {

    private static final List<Integer> COMMODITIES = List.of(7472, 2365);
    private static final int MAX_STOCK = (int) (COMMODITIES.size() + (LootTableLoader.getInstance().read(4225145936390343693L).getLootTableEntries().size() * 0.2f));

    @Override
    public int getPriceMerchantWillSellFor(int itemId) {
        int baseValue = ItemIdContextLoader.getInstance().read(itemId).getValue();
        switch (itemId) {
            case 6825:
                baseValue = 100;
                break;
            case 19730:
            case 7411:
                baseValue = 500;
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
        return itemId == saleItemId ? (int) (baseValue * sale) : baseValue;
    }

    @Override
    public String getPriceForItemBeingBoughtFromShop(int itemId) {
        if (itemId == saleItemId)
            return "@" + itemId + " on sale for #" + this.getPriceMerchantWillSellFor(itemId) + " @" + getCurrencyId() + " each"
                    + " was #" + ItemIdContextLoader.getInstance().read(itemId).getValue() + " @" + getCurrencyId() + " each.";
        else if (itemId == 1459)
            return "The shop will sell @" + itemId + " for #" + this.getPriceMerchantWillSellFor(itemId) + " @"
                    + 995 + " each.";
        return "The shop will sell @" + itemId + " for #" + this.getPriceMerchantWillSellFor(itemId) + " @"
                + getCurrencyId() + " each.";
    }

    protected void initializeStock() {
        this.getLootTable().roll(this.getMerchandiseCapacity(), this.getBaseMagicFind()).forEach(loot -> {
            if (this.getMerchandise().size() < this.getMerchandiseCapacity() && this.getMerchandise().stream().noneMatch(merchandiseSlot -> merchandiseSlot.getItemId() == loot.getId()))
                this.getMerchandise().add(new MerchandiseSlot(
                        (int) loot.getId(),
                        (int) loot.getAmount(),
                        true,
                        0.5D,
                        false
                ));
        });

        this.saleItemId = this.getSaleItemId();
        this.sale = this.getSale(saleItemId);
    }

    @Override
    public boolean sellItemToPlayer(int itemId, int amount, int slot, Player player) {
        final int price = this.getPriceMerchantWillSellFor(itemId) * amount;
//        if (amount <= this.getMerchandise().get(slot).getAmount()) {
            if (player.getItems().playerHasItem(getCurrencyId(), price) || (itemId == 1459 && player.getItems().playerHasItem(995, price))) {
                if (player.getItems().freeSlots() > (ItemIdContextLoader.getInstance().read(itemId).isStackable() ? 0 : amount)) {
                    if (itemId == 1459) {
                        player.getItems().deleteItem(995, price);
                        player.sendMessage("You bought #" + amount + " @" + itemId + " for #" + price + " @" + 995);
                    } else {
                        player.getItems().deleteItem(getCurrencyId(), price);
                        player.sendMessage("You bought #" + amount + " @" + itemId + " for #" + price + " @" + getCurrencyId());
                    }
                    player.getItems().addItem(itemId, amount);
                    player.getItems().resetItems(3823);
                    this.updateShop(player);
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

    @Override
    public int getSaleItemId() {
        int itemId = this.getMerchandise().get(Skill.SKILL_RANDOM.nextInt(this.getMerchandise().size())).getItemId();
        if (COMMODITIES.stream().anyMatch(commodity -> commodity == itemId) && this.getMerchandiseSlot(itemId).isSalePossible() || itemId == 1459) {
            return getSaleItemId();
        }
        return itemId;
    }

    public CommodityMerchant() {
        super(1459, 1328, "Jewel Shop", 4225145936390343693L, COMMODITIES, 1.0f, MAX_STOCK);
    }

    private double sale;
    private int saleItemId;
}

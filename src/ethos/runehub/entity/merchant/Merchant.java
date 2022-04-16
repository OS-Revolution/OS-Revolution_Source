package ethos.runehub.entity.merchant;

import ethos.model.players.Player;
import ethos.runehub.entity.CommodityTrader;
import ethos.runehub.skill.Skill;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.io.load.impl.LootTableLoader;
import org.runehub.api.model.entity.item.loot.LootTable;
import org.runehub.api.model.math.impl.DoubleRange;

import java.util.List;
import java.util.stream.Collectors;

public class Merchant {

    protected void initializeMerchandise() {
        this.getLootTable().getLootTableEntries().forEach(lootTableEntry -> {
            merchandise.add(new MerchandiseSlot(
                    lootTableEntry.getId(),
                    lootTableEntry.getAmount().getMax(),
                    false,
                    0.D));
        });
    }

    protected int getPriceMerchantWillSellFor(int itemId) {
        return ItemIdContextLoader.getInstance().read(itemId).getValue();
    }

    protected int getPriceMerchantWillBuyFor(int itemId) {
        return ItemIdContextLoader.getInstance().read(itemId).getValue();
    }

    public void openShop(Player player) {
        player.getItems().resetItems(3823);
        player.isShopping = true;
        player.myShopId = merchantId;
        player.getPA().sendFrame248(64000, 3822);
        player.getPA().sendFrame126(name, 64003);
        player.getPA().sendFrame171(1, 64017);
        player.getOutStream().createFrameVarSizeWord(53);
        player.getOutStream().writeWord(64016);
        player.getOutStream().writeWord(merchandise.size());
        merchandise.forEach(merchandiseSlot -> {
            player.getOutStream().writeByte(merchandiseSlot.getAmount());
            player.getOutStream().writeWordBigEndianA(merchandiseSlot.getItemId() + 1);
        });
        player.getOutStream().writeWordBigEndianA(0);
        player.getOutStream().endFrameVarSizeWord();
        player.flushOutStream();
    }

    public boolean buyItemFromPlayer(int itemId, int amount, int slot, Player player) {
        final int price = this.getPriceMerchantWillBuyFor(itemId) * amount;
        if (player.getItems().playerHasItem(itemId, amount)) {
            if (player.getItems().freeSlots() > (ItemIdContextLoader.getInstance().read(itemId).isStackable() ? 0 : amount)) {
                player.getItems().deleteItem(itemId, slot, amount);
                player.getItems().addItem(currencyId, price);
                player.getItems().resetItems(3823);
                player.sendMessage("You sell your #" + amount + " @" + itemId + " in exchange for #"
                        + price + " @" + currencyId);
                return true;
            } else {
                player.sendMessage("You do not have enough inventory space.");
            }
        } else {
            player.sendMessage("You can't sell what you don't have.");
        }
        return false;
    }

    public boolean sellItemToPlayer(int itemId, int amount, int slot, Player player) {
        final int price = this.getPriceMerchantWillSellFor(itemId) * amount;
        if (player.getItems().playerHasItem(currencyId, price)) {
            if (player.getItems().freeSlots() > (ItemIdContextLoader.getInstance().read(itemId).isStackable() ? 0 : amount)) {
                player.getItems().deleteItem(currencyId, price);
                player.getItems().addItem(itemId, amount);
                player.getItems().resetItems(3823);
                player.sendMessage("You bought #" + amount + " @" + itemId + " for #" + price + " @" + currencyId);
                return true;
            } else {
                player.sendMessage("You do not have enough inventory space.");
            }
        } else {
            player.sendMessage("Come back when you're a little bit...richer!");
        }
        return false;
    }


    public String getPriceForItemBeingSoldToShop(int itemId) {
        if (buyBackIds.contains(-1) || buyBackIds.contains(itemId))
            return "The shop will pay #" + this.getPriceMerchantWillBuyFor(itemId) + " @"
                    + currencyId + " per @" + itemId;
        return "The shop will not buy this";
    }

    public String getPriceForItemBeingBoughtFromShop(int itemId) {
        return "The shop will sell @" + itemId + " for #" + this.getPriceMerchantWillSellFor(itemId) + " @"
                + currencyId + " each.";
    }

    public List<MerchandiseSlot> getMerchandise() {
        return merchandise;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public String getName() {
        return name;
    }

    public long getMerchandiseTableId() {
        return merchandiseTableId;
    }

    protected LootTable getLootTable() {
        return LootTableLoader.getInstance().read(merchandiseTableId);
    }

    public double getSale(int itemId) {
        final double sale = new DoubleRange(0.5, 0.95).getRandomValue();
        return this.getMerchandiseSlot(itemId).getMaxDiscount() <= sale ? sale : getSale(itemId);
    }

    public int getSaleItemId() {
        return this.getMerchandise().get(Skill.SKILL_RANDOM.nextInt(this.getMerchandise().size())).getItemId();
    }

    protected MerchandiseSlot getMerchandiseSlot(int itemId) {
        return merchandise.stream().filter(merchandiseSlot -> merchandiseSlot.getItemId() == itemId).findAny().orElse(null);
    }

    public int getMerchantId() {
        return merchantId;
    }

    public Merchant(int currencyId, int shopId, String name, long merchandiseTableId, List<Integer> buyBackIds) {
        this(
                LootTableLoader.getInstance().read(merchandiseTableId).getLootTableEntries().stream()
                        .map(lootTableEntry -> new MerchandiseSlot(lootTableEntry.getId(), lootTableEntry.getAmount().getMax(), false, 0.0D))
                        .collect(Collectors.toList()),
                currencyId,
                shopId,
                name,
                merchandiseTableId,
                buyBackIds
        );
    }

    public Merchant(List<MerchandiseSlot> merchandise, int currencyId, int shopId, String name, long merchandiseTableId, List<Integer> buyBackIds) {
        this.merchandise = merchandise;
        this.currencyId = currencyId;
        this.merchantId = shopId;
        this.name = name;
        this.merchandiseTableId = merchandiseTableId;
        this.buyBackIds = buyBackIds;
    }

    private final List<MerchandiseSlot> merchandise;
    private final List<Integer> buyBackIds;
    private final int currencyId;
    private final int merchantId;
    private final String name;
    private final long merchandiseTableId;
    private double sale;
    private int saleItemId;
}

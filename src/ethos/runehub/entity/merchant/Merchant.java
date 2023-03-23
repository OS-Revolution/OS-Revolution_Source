package ethos.runehub.entity.merchant;

import ethos.model.players.Player;
import ethos.model.players.PlayerHandler;
import ethos.runehub.RunehubConstants;
import ethos.runehub.entity.CommodityTrader;
import ethos.runehub.skill.Skill;
import ethos.runehub.ui.impl.ShopUI;
import ethos.world.ShopHandler;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.io.load.impl.LootTableLoader;
import org.runehub.api.model.entity.item.loot.LootTable;
import org.runehub.api.model.math.impl.DoubleRange;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Merchant {

    protected void initializeMerchandise() {
        this.getLootTable().getLootTableEntries().forEach(lootTableEntry -> {
            merchandise.add(new MerchandiseSlot(
                    lootTableEntry.getId(),
                    lootTableEntry.getAmount().getMax(),
                    false,
                    0.D,
                    false));
        });
    }

    protected int getPriceMerchantWillSellFor(int itemId) {
        return ItemIdContextLoader.getInstance().read(itemId).getValue();
    }

    protected int getPriceMerchantWillBuyFor(int itemId) {
        return ItemIdContextLoader.getInstance().read(itemId).getValue();
    }

    public void openShop(Player player) {
        Logger.getGlobal().info("Opening Shop");
        player.getItems().resetItems(3823);
        player.isShopping = true;
        player.myShopId = merchantId;
        player.sendUI(new ShopUI(player, name));
//        player.getPA().sendFrame248(64000, 3822);
//        player.getPA().sendFrame126(name, 64003);
//        player.getPA().sendFrame171(1, 64017);
        player.getOutStream().createFrameVarSizeWord(53);
        player.getOutStream().writeWord(64016);
        player.getOutStream().writeWord(merchandise.size());
        merchandise.forEach(merchandiseSlot -> {
            if (merchandiseSlot.getAmount() > 254) {
                player.getOutStream().writeByte(255);
                player.getOutStream().writeDWord_v2(merchandiseSlot.getAmount());
            } else {
                player.getOutStream().writeByte(merchandiseSlot.getAmount());
            }
            player.getOutStream().writeWordBigEndianA(merchandiseSlot.getItemId() + 1);
        });
        player.getOutStream().writeWordBigEndianA(0);
        player.getOutStream().endFrameVarSizeWord();
        player.flushOutStream();
    }

    public boolean buyItemFromPlayer(int itemId, int amount, int slot, Player player) {
        if (ItemIdContextLoader.getInstance().read(itemId).isTradable() && itemId != currencyId) {
            final int price = this.getPriceMerchantWillBuyFor(itemId) * amount;
            if (player.getItems().playerHasItem(itemId, amount)) {
                if (player.getItems().freeSlots() > (ItemIdContextLoader.getInstance().read(currencyId).isStackable() ? 0 : amount)) {
                    player.getItems().deleteItem2(itemId, amount);
                    player.getItems().addItem(currencyId, price);
                    player.getItems().resetItems(3823);
                    player.sendMessage("You sell your #" + amount + " @" + itemId + " in exchange for #"
                            + price + " @" + currencyId);
                    this.updateStock(itemId, amount);
                    this.updateShop(player);
                    return true;
                } else {
                    player.sendMessage("You do not have enough inventory space.");
                }
            } else {
                player.sendMessage("You can't sell what you don't have.");
            }
        } else {
            player.sendMessage("The shop will not buy this");
        }
        return false;
    }

    public boolean sellItemToPlayer(int itemId, int amount, int slot, Player player) {
        final int price = this.getPriceMerchantWillSellFor(itemId) * amount;
        if (amount <= this.getMerchandise().get(slot).getAmount()) {
            if (player.getItems().playerHasItem(currencyId, price)) {
                if (player.getItems().freeSlots() > (ItemIdContextLoader.getInstance().read(itemId).isStackable() ? 0 : amount)) {
                    player.getItems().deleteItem(currencyId, price);
                    player.getItems().addItem(itemId, amount);
                    player.getItems().resetItems(3823);
                    player.sendMessage("You bought #" + amount + " @" + itemId + " for #" + price + " @" + currencyId);
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


    protected void updateStock(int itemId, int amount) {
        if (this.getMerchandiseSlot(itemId) == null) {
            MerchandiseSlot merchandiseSlot = new MerchandiseSlot(itemId, amount, false, 0.0D, false);
            this.getMerchandise().add(merchandiseSlot);
        } else {
            this.getMerchandiseSlot(itemId).setAmount(this.getMerchandiseSlot(itemId).getAmount() + amount);
        }
    }

    protected void updateShop(Player player) {
        player.getOutStream().createFrameVarSizeWord(53);
        player.getOutStream().writeWord(64016);
        player.getOutStream().writeWord(this.getMerchandise().size());
        this.getMerchandise().forEach(merchandiseSlot -> {
            if (merchandiseSlot.getAmount() > 254) {
                player.getOutStream().writeByte(255);
                player.getOutStream().writeDWord_v2(merchandiseSlot.getAmount());
            } else {
                player.getOutStream().writeByte(merchandiseSlot.getAmount());
            }
            player.getOutStream().writeWordBigEndianA(merchandiseSlot.getItemId() + 1);
        });
        player.getOutStream().writeWordBigEndianA(0);
        player.getOutStream().endFrameVarSizeWord();
        player.flushOutStream();
    }

    public void restock() {
        this.getLootTable().getLootTableEntries().forEach(lootTableEntry -> {
            int itemId = lootTableEntry.getId();
            if (this.getMerchandiseSlot(itemId) != null && this.getMerchandiseSlot(itemId).getAmount() < lootTableEntry.getAmount().getMax()) {
                this.getMerchandiseSlot(itemId).setAmount(this.getMerchandiseSlot(itemId).getAmount() + 1);
            } else if (this.getMerchandiseSlot(itemId) == null) {
                this.getMerchandise().add(new MerchandiseSlot(itemId, 1, false, 0.0D, false));
            }
        });
        PlayerHandler.getPlayers().stream().filter(player -> player.myShopId == merchantId).forEach(this::updateShop);
    }

    public String getPriceForItemBeingSoldToShop(int itemId) {
        if ((buyBackIds.contains(-1) || buyBackIds.contains(itemId)) && itemId != currencyId && ItemIdContextLoader.getInstance().read(itemId).isTradable())
            return "The shop will pay #" + this.getPriceMerchantWillBuyFor(itemId) + " @"
                    + currencyId + " per @" + itemId;
        return "The shop will not buy this";
    }

    public String getPriceForItemBeingBoughtFromShop(int itemId) {
        return "The shop will sell @" + itemId + " for #" + this.getPriceMerchantWillSellFor(itemId) + " @"
                + currencyId + " each.";
    }

    public List<Integer> getBuyBackIds() {
        return buyBackIds;
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
                        .map(lootTableEntry -> new MerchandiseSlot(lootTableEntry.getId(), lootTableEntry.getAmount().getMax(), false, 0.0D, false))
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

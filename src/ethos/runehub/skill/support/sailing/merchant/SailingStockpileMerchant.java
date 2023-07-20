package ethos.runehub.skill.support.sailing.merchant;

import ethos.model.players.Player;
import ethos.runehub.entity.item.GameItem;
import ethos.runehub.entity.merchant.MerchandiseSlot;
import ethos.runehub.entity.merchant.Merchant;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.model.entity.item.ItemContext;
import org.runehub.api.model.math.impl.AdjustableInteger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SailingStockpileMerchant extends Merchant {

    @Override
    public void openShop(Player player) {
        this.initializeMerchandiseForPlayer(player);
        super.openShop(player);
    }

    @Override
    public String getPriceForItemBeingSoldToShop(int itemId) {
        if (this.getBuyBackIds().contains(itemId) || (ItemIdContextLoader.getInstance().read(itemId).isNoted() && this.getBuyBackIds().contains(ItemIdContextLoader.getInstance().read(itemId).getLinkedId())))
            return "These can be added to the stockpile";
        return "These are not valid trade goods";
    }

    @Override
    public boolean buyItemFromPlayer(int itemId, int amount, int slot, Player player) {
        if (this.getBuyBackIds().contains(itemId) || this.getBuyBackIds().isEmpty()) {
            if (player.getItems().playerHasItem(itemId, amount)) {
                ItemContext context = ItemIdContextLoader.getInstance().read(itemId);
                int itemIdToAdd = context.isNoted() ? context.getLinkedId() : itemId;
                player.getItems().deleteItem2(itemId, amount);
                player.getItems().resetItems(3823);
                player.sendMessage("You add your #" + amount + " @" + itemId + " to the stockpile.");
                this.updateStock(itemIdToAdd, amount);
                this.updateShop(player);
                this.updateDatabase(player, itemIdToAdd, amount,true);
                return true;
            } else {
                player.sendMessage("You can't sell what you don't have.");
            }
        } else {
            player.sendMessage("The shop will not buy this");
        }
        return false;
    }

    @Override
    public boolean sellItemToPlayer(int itemId, int amount, int slot, Player player) {
        if (amount <= this.getMerchandise().get(slot).getAmount()) {
            if (player.getItems().freeSlots() > (ItemIdContextLoader.getInstance().read(itemId).isStackable() ? 0 : amount)
                    || ItemIdContextLoader.getInstance().read(itemId).isNoteable()) {
                ItemContext context = ItemIdContextLoader.getInstance().read(itemId);
                if (ItemIdContextLoader.getInstance().read(itemId).isNoteable()) {
                    player.getItems().addItem(context.getLinkedIdNoted(), amount);
                } else {
                    player.getItems().addItem(itemId, amount);
                }
                player.getItems().resetItems(3823);
                player.sendMessage("You collected #" + amount + " @" + itemId);
                this.getMerchandiseSlot(itemId).setAmount(this.getMerchandiseSlot(itemId).getAmount() - amount);
                this.updateShop(player);
                this.updateDatabase(player, itemId, amount,false);
                return true;
            } else {
                player.sendMessage("You do not have enough inventory space.");
            }
        }
        return false;
    }

    protected void initializeMerchandiseForPlayer(Player player) {
        this.getMerchandise().clear();
        final Map<Integer, AdjustableInteger> itemMap = new HashMap<>();
        long[] encodedCargo = player.getSailingSaveData().getCargo();
        List<GameItem> items = new ArrayList<>();

        for (long l : encodedCargo) {
            GameItem cargo = GameItem.decodeGameItem(l);
            if (cargo.getId() != 0) {
                System.out.println("Loading item: " + cargo);
                items.add(cargo);
            }
        }
        items.forEach(gameItem -> {
            if (itemMap.containsKey(gameItem.getId())) {
                itemMap.get(gameItem.getId()).add(gameItem.getAmount());
            } else {
                itemMap.put(gameItem.getId(), new AdjustableInteger(gameItem.getAmount()));
            }
        });

        itemMap.keySet().forEach(itemId -> {
            this.getMerchandise().add(new MerchandiseSlot(
                    itemId,
                    itemMap.get(itemId).value(),
                    false,
                    0.D,
                    false));
        });
    }

    private List<GameItem> updateDatabaseSelling(List<GameItem> items, int itemId, int amount) {
        items
                .stream()
                .filter(item -> item.getId() == itemId)
                .findAny()
                .ifPresentOrElse(
                        item -> item.setAmount(item.getAmount() - amount),
                        () -> {
                            items.add(new GameItem(itemId, amount));
                        }
                );
        return items;
    }

    private void updateDatabase(Player player, int itemId, int amount,boolean buying) {
        long[] encodedCargo = player.getSailingSaveData().getCargo();
        List<GameItem> items = new ArrayList<>();

        //converts the stored longs to GameItem objects and adds them to the list
        for (long l : encodedCargo) {
            GameItem cargo = GameItem.decodeGameItem(l);
            if (cargo.getId() != 0)
                items.add(cargo);
        }

        if (buying) {
            updateDatabaseBuying(items, itemId, amount);
        } else {
            updateDatabaseSelling(items, itemId, amount);
        }

        for (int i = 0; i < items.size(); i++) {
            GameItem item = items.get(i);
            if (item.getAmount() > 0) {
                player.getSailingSaveData().setCargo(i, items.get(i).encodeGameItem());
            } else {
                player.getSailingSaveData().setCargo(i, new GameItem(0, 0).encodeGameItem());
            }
        }

    }

    private List<GameItem> updateDatabaseBuying(List<GameItem> items, int itemId, int amount) {
        items
                .stream()
                .filter(item -> item.getId() == itemId)
                .findAny()
                .ifPresentOrElse(
                        item -> item.setAmount(item.getAmount() + amount),
                        () -> {
                            items.add(new GameItem(itemId, amount));
                        }
                );
        return items;
    }

    public SailingStockpileMerchant() {
        super(new ArrayList<>(), 995, 50002, "Stockpile", -1, new ArrayList<>());
    }
}

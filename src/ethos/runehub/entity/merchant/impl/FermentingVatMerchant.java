package ethos.runehub.entity.merchant.impl;

import ethos.model.players.Player;
import ethos.runehub.entity.item.GameItem;
import ethos.runehub.entity.merchant.MerchandiseSlot;
import ethos.runehub.entity.merchant.Merchant;
import ethos.runehub.entity.player.IdleBrewingStation;
import ethos.runehub.entity.player.IdleBrewingStationDAO;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.model.math.impl.AdjustableInteger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FermentingVatMerchant extends Merchant {

    @Override
    public void openShop(Player player) {
        if (IdleBrewingStationDAO.getInstance().read(player.getContext().getId()) == null)
            IdleBrewingStationDAO.getInstance().create(new IdleBrewingStation(
                    player.getContext().getId(),
                    new ArrayList<>()
            ));
        this.initializeMerchandiseForPlayer(player);
        player.getSkillController().addImmutableXP(7,player.getContext().getPlayerSaveData().getIdleBrewedXp());
        player.getContext().getPlayerSaveData().setIdleBrewedXp(0);
        super.openShop(player);
    }

    @Override
    public String getPriceForItemBeingSoldToShop(int itemId) {
        if (this.getBuyBackIds().contains(itemId) || (ItemIdContextLoader.getInstance().read(itemId).isNoted() && this.getBuyBackIds().contains(ItemIdContextLoader.getInstance().read(itemId).getLinkedId())))
            return "These can be added to the fermenting vat.";
        return "The fermenting vat can't use this.";
    }

    @Override
    public boolean buyItemFromPlayer(int itemId, int amount, int slot, Player player) {
        if (this.getBuyBackIds().contains(itemId)) {
            if (player.getItems().playerHasItem(itemId, amount)) {
                player.getItems().deleteItem2(itemId, amount);
                player.getItems().resetItems(3823);
                player.sendMessage("You add your #" + amount + " @" + itemId + " to the fermenting vat.");
                this.updateStock(itemId, amount);
                this.updateShop(player);
                this.updateDatabaseBuying(player,itemId,amount);
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
            if (player.getItems().freeSlots() > (ItemIdContextLoader.getInstance().read(itemId).isStackable() ? 0 : amount)) {
                if (ItemIdContextLoader.getInstance().read(itemId).isNoteable()) {
                    player.getItems().addItem(itemId + 1, amount);
                } else {
                    player.getItems().addItem(itemId, amount);
                }
                player.getItems().resetItems(3823);
                player.sendMessage("You collected #" + amount + " @" + itemId);
                this.getMerchandiseSlot(itemId).setAmount(this.getMerchandiseSlot(itemId).getAmount() - amount);
                this.updateShop(player);
                this.updateDatabaseSelling(player, itemId, amount);
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

        List<GameItem> items = player.getContext().getPlayerSaveData().getIdleBrewingStation();
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

    private void updateDatabaseSelling(Player player, int itemId, int amount) {
        List<GameItem> items = player.getContext().getPlayerSaveData().getIdleBrewingStation();
        items
                .stream()
                .filter(item -> item.getId() == itemId)
                .findAny()
                .ifPresentOrElse(
                        item -> item.setAmount(item.getAmount() - amount),
                        () -> {
                            items.add(new GameItem(itemId,amount));
                        }
                );

        player.save();
    }

    private void updateDatabaseBuying(Player player, int itemId, int amount) {
        List<GameItem> items = player.getContext().getPlayerSaveData().getIdleBrewingStation();
        items
                .stream()
                .filter(item -> item.getId() == itemId)
                .findAny()
                .ifPresentOrElse(
                        item -> item.setAmount(item.getAmount() + amount),
                        () -> {
                            items.add(new GameItem(itemId,amount));
                        }
                );

        player.save();
    }

    public FermentingVatMerchant() {
        super(new ArrayList<>(),995, 7528, "Fermenting Vat", -1, List.of(1929, 6008, 8988,
                1919, 5992, 5994, 5996, 255, 5998, 6000, 6004, 6043, 1975, 6002,5767));
    }
}

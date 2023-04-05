package ethos.runehub.entity.merchant.impl;

import ethos.model.players.Player;
import ethos.runehub.content.journey.JourneyStepType;
import ethos.runehub.entity.merchant.MerchandiseSlot;
import ethos.runehub.entity.merchant.Merchant;
import ethos.runehub.entity.merchant.impl.exchange.ExchangeAccount;
import ethos.runehub.entity.merchant.impl.exchange.ExchangeAccountDatabase;
import org.runehub.api.io.load.impl.ItemIdContextLoader;

import java.util.List;

public class DeathMerchant extends Merchant {

    @Override
    protected void initializeMerchandise() {
    }

    @Override
    public void openShop(Player player) {
        this.initializeMerchandiseForPlayer(player);
        super.openShop(player);
        player.getAttributes().getJourneyController().checkJourney(5567,1, JourneyStepType.OPEN_SHOP);
    }

    @Override
    public boolean sellItemToPlayer(int itemId, int amount, int slot, Player player) {
        final int price = player.getAttributes().isMember() ?(this.getMemberPriceMerchantWillSellFor(itemId) * amount) : (this.getPriceMerchantWillSellFor(itemId) * amount);
        if (amount <= this.getMerchandise().get(slot).getAmount()) {
            if (player.getItems().playerHasItem(this.getCurrencyId(), price) || player.getItems().playerHasItem(1464,amount)) {
                if (player.getItems().freeSlots() > (ItemIdContextLoader.getInstance().read(itemId).isStackable() ? 0 : amount)) {
                    if(!player.getItems().playerHasItem(1464,amount)) {
                        player.getItems().deleteItem(this.getCurrencyId(), price);
                    } else {
                        player.getItems().deleteItem(1464, amount);
                    }
                    player.getItems().addItem(itemId, amount);
                    player.getItems().resetItems(3823);
                    this.updateReclaimableItems(player, itemId, amount);
                    player.sendMessage("You re-claimed #" + amount + " @" + itemId + " for #" + price + " @" + this.getCurrencyId());
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

    protected int getMemberPriceMerchantWillSellFor(int itemId) {
        final int baseValue = ItemIdContextLoader.getInstance().read(itemId).getValue();
        if (baseValue > 0) {
            return (int) (baseValue * 0.1D);
        }
        return 10000;
    }

    protected int getPriceMerchantWillSellFor(int itemId) {
        final int baseValue = ItemIdContextLoader.getInstance().read(itemId).getValue();
        if (baseValue > 0) {
            return (int) (baseValue * 0.15D);
        }
        return 10000;
    }

    private void initializeMerchandiseForPlayer(Player player) {
        this.getMerchandise().clear();
        player.getContext().getPlayerSaveData().getReclaimableItems().forEach(gameItem -> this.getMerchandise().add(
                new MerchandiseSlot(
                        gameItem.getId(),
                        gameItem.getAmount(),
                        false,
                        0D,
                        false
                )
        ));
    }

    private void updateReclaimableItems(Player player,int idBought, int amountBought) {
//        final List<>
        player.getContext().getPlayerSaveData().getReclaimableItems().stream().filter(gameItem -> gameItem.getId() == idBought).findFirst().ifPresent(gameItem -> {
            gameItem.setAmount(gameItem.getAmount() - amountBought);
            if (gameItem.getAmount() <= 0) {
                player.getContext().getPlayerSaveData().getReclaimableItems().remove(gameItem);
            }
        });
        this.getMerchandise().clear();
        player.getContext().getPlayerSaveData().getReclaimableItems().forEach(gameItem -> this.getMerchandise().add(
                new MerchandiseSlot(
                        gameItem.getId(),
                        gameItem.getAmount(),
                        false,
                        0D,
                        false
                )
        ));

        this.updateShop(player);
    }

    public DeathMerchant() {
        super(995, 5567, "Death's Item Retrieval", 8281273871449345314L, List.of());
    }
}

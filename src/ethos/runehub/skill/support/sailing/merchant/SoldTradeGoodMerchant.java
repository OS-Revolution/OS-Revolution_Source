package ethos.runehub.skill.support.sailing.merchant;

import ethos.model.players.Player;
import ethos.runehub.entity.item.GameItem;
import ethos.runehub.entity.merchant.MerchandiseSlot;
import ethos.runehub.entity.merchant.Merchant;
import ethos.runehub.skill.support.sailing.SailingUtils;
import ethos.runehub.skill.support.sailing.voyage.TradeGood;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.model.math.impl.AdjustableInteger;

import java.util.*;

public class SoldTradeGoodMerchant extends Merchant {

    @Override
    public String getPriceForItemBeingBoughtFromShop(int itemId) {
        return "You can buy @" + itemId + " for #" + this.getPriceMerchantWillSellFor(itemId) + " @"
                + this.getCurrencyId() + " each.";
    }

    @Override
    protected int getPriceMerchantWillSellFor(int itemId) {
        return priceMap.get(itemId);
    }

    @Override
    public void openShop(Player player) {
        if (this.getMerchandise().isEmpty())
            this.initializeMerchandiseForPlayer(player);
        super.openShop(player);
    }

    protected void initializeMerchandiseForPlayer(Player player) {
        this.getMerchandise().clear();
        final Map<Integer, AdjustableInteger> itemMap = new HashMap<>();
        long[] encodedTradeGoods = player.getSailingSaveData().getVoyageSoldGoods()[player.getAttributes().getSelectedVoyageIndex()];

        List<TradeGood> tradeGoods = new ArrayList<>();
        Arrays.stream(encodedTradeGoods).forEach(encodedItem -> tradeGoods.add(TradeGood.fromLong(encodedItem)));

        tradeGoods.forEach(gameItem -> {
            System.out.println(gameItem);
            if (gameItem.getItemId() != 0) {
                if (itemMap.containsKey(gameItem.getItemId())) {
                    itemMap.get(gameItem.getItemId()).add(gameItem.getStock());
                } else {
                    itemMap.put(gameItem.getItemId(), new AdjustableInteger(gameItem.getStock()));
                    priceMap.put(gameItem.getItemId(), gameItem.getBasePrice());
                }
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

    @Override
    public boolean sellItemToPlayer(int itemId, int amount, int slot, Player player) {
        if (amount <= this.getMerchandise().get(slot).getAmount()) {
            double addedWeight = ItemIdContextLoader.getInstance().read(itemId).getWeight() * amount;
            double availableWeight = player.getSkillController().getSailing().getShip(player.getAttributes().getSelectedShipSlot()).getWeightCapacity();
            double newWeight = player.getSkillController().getSailing().getIncomingCargoWeight() + addedWeight;
            if (newWeight <= availableWeight) {
                if (player.getSailingSaveData().getCoffer() >= amount) {
                    try {
                        this.updateDatabaseSelling(player, itemId, amount);
                        player.getItems().resetItems(3823);
//                    player.getAttributes().getSelectedBuyOffers()[player.getAttributes().getSelectedBuySlot()] = new GameItem(itemId, amount);
                        player.getAttributes().setCargoWeight(player.getAttributes().getCargoWeight() + addedWeight);
                        this.getMerchandiseSlot(itemId).setAmount(this.getMerchandiseSlot(itemId).getAmount() - amount);
                        this.updateShop(player);
                        player.sendMessage("You add #" + amount + " @" + itemId + " to your order.");
                        return true;
                    } catch (IllegalArgumentException e) {
                        player.sendMessage(e.getMessage());
                    }
                } else {
                    player.sendMessage("You don't have enough coins in your coffers.");
                }
            } else {
                player.sendMessage("You can't add that many your ship will be overweight.");
            }
        }
        return false;
    }

    private void updateDatabaseSelling(Player player, int itemId, int amount) {
        TradeGood tradeGood = player.getSailingSaveData().getSoldTradeGoods(player.getAttributes().getSelectedVoyageIndex()).get(itemId);
        TradeGood selectedTradeGood = new TradeGood(tradeGood.getItemId(),amount,tradeGood.getBasePrice());
        long[] items = player.getAttributes().getSelectedBuyOffers();
        int availableSlot = player.getSailingSaveData().getNextAvailableCargoIndex(
                items
        );
        player.getAttributes().setBuyOffer(availableSlot, selectedTradeGood.toLong());
    }

    public SoldTradeGoodMerchant() {
        super(new ArrayList<>(), 995, 50001, "Select what you will be buying", -1, List.of());
        this.priceMap = new HashMap<>();
    }

    private final Map<Integer, Integer> priceMap;
}

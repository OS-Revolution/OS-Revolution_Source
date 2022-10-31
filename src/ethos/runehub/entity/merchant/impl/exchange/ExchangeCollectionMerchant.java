package ethos.runehub.entity.merchant.impl.exchange;

import ethos.model.players.Player;
import ethos.model.players.PlayerHandler;
import ethos.runehub.entity.merchant.MerchandiseSlot;
import ethos.runehub.entity.merchant.Merchant;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.model.math.AdjustableNumber;
import org.runehub.api.model.math.impl.AdjustableInteger;
import org.runehub.api.util.IDManager;

import java.util.*;
import java.util.stream.Collectors;


public class ExchangeCollectionMerchant extends Merchant {


    @Override
    protected void initializeMerchandise() {
    }

    @Override
    public void openShop(Player player) {
        if (ExchangeAccountDatabase.getInstance().read(player.getContext().getId()) == null)
            ExchangeAccountDatabase.getInstance().create(new ExchangeAccount(
                    player.getContext().getId(),
                    0L, 0L, 0L, 0L, 0L
            ));
        this.initializeMerchandiseForPlayer(player);
        super.openShop(player);
    }

    @Override
    protected int getPriceMerchantWillSellFor(int itemId) {
        return ItemIdContextLoader.getInstance().read(itemId).getExchange();
    }

    @Override
    protected int getPriceMerchantWillBuyFor(int itemId) {
        return ItemIdContextLoader.getInstance().read(itemId).getExchange();
    }

    @Override
    public String getPriceForItemBeingSoldToShop(int itemId) {
        if (!this.getBuyBackIds().contains(itemId) && ItemIdContextLoader.getInstance().read(itemId).isTradable()
                && (ItemIdContextLoader.getInstance().read(itemId).isNoteable() || ItemIdContextLoader.getInstance().read(itemId).isNoted()))
            return "These will list for #" + this.getPriceMerchantWillBuyFor(itemId) + " @"
                    + this.getCurrencyId() + " per @" + itemId;
        return "The shop will not buy this";
    }

    @Override
    public boolean buyItemFromPlayer(int itemId, int amount, int slot, Player player) {
        return false;
    }


    @Override
    public boolean sellItemToPlayer(int itemId, int amount, int slot, Player player) {
        final int unitPrice = this.getPriceMerchantWillSellFor(itemId);
        if (player.getItems().freeSlots() > 1) {
            final int amountSold = this.makeExchange(player, itemId, amount, unitPrice);
            if (ItemIdContextLoader.getInstance().read(itemId).isNoteable()) {
                player.getItems().addItem(itemId + 1, amountSold);
            } else {
                player.getItems().addItem(itemId, amountSold);
            }
            player.getItems().resetItems(3823);
            player.sendMessage("You collected #" + amountSold + " @" + itemId);
            return true;
        } else {
            player.sendMessage("You do not have enough inventory space.");
        }
        return false;
    }

    protected void initializeMerchandiseForPlayer(Player player) {
        this.getMerchandise().clear();
        final Map<Integer, AdjustableInteger> itemMap = new HashMap<>();
        final ExchangeAccount account = ExchangeAccountDatabase.getInstance().read(player.getContext().getId());
        ExchangeOfferDatabase.getInstance().getAllEntries().stream()
                .filter(exchangeOffer -> exchangeOffer.getUserId() == player.getContext().getId())
                .forEach(exchangeOffer -> {
                    if (itemMap.containsKey(exchangeOffer.getItemId())) {
//                if (itemMap.get(exchangeOffer.getItemQuantity()).add(exchangeOffer.getItemQuantity()) <= (Integer.MAX_VALUE - 1))
                        itemMap.get(exchangeOffer.getItemId()).add(exchangeOffer.getItemQuantity());
                    } else {
                        itemMap.put(exchangeOffer.getItemId(), new AdjustableInteger(exchangeOffer.getItemQuantity()));
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
        if (account.getAvailableRevenue() <= Integer.MAX_VALUE) {
            this.getMerchandise().add(new MerchandiseSlot(
                    995,
                    Math.toIntExact(account.getAvailableRevenue()),
                    false,
                    0.0D,
                    false
            ));
        } else {
            final int platinumTokens = Math.toIntExact(account.getAvailableRevenue() / 1000);
            final int coins = Math.toIntExact(account.getAvailableRevenue() % platinumTokens);
            System.out.println("Platinum: " + platinumTokens);
            System.out.println("Gold: " + coins);

            this.getMerchandise().add(new MerchandiseSlot(
                    13204,
                    platinumTokens,
                    false,
                    0.0D,
                    false
            ));
            this.getMerchandise().add(new MerchandiseSlot(
                    995,
                    coins,
                    false,
                    0.0D,
                    false
            ));
        }
    }

    private int makeExchange(Player player, int itemId, int amount, int unitPrice) {
        ExchangeAccount account = ExchangeAccountDatabase.getInstance().read(player.getContext().getId());
        final Collection<ExchangeOffer> matchingOffers = ExchangeOfferDatabase.getInstance().getAllEntries().stream()
                .filter(exchangeOffer -> exchangeOffer.getUserId() == player.getContext().getId())
                .filter(exchangeOffer -> exchangeOffer.getItemId() == itemId)
                .collect(Collectors.toList());
        final AdjustableNumber<Integer> amountSold = new AdjustableInteger(0);

        if (itemId == 995 || itemId == 13204) {
            int multiplier = itemId == 995 ? 1 : 1000;
            long value = account.getAvailableRevenue() - ((long) amount * multiplier);
            if (value < 0) {
                value = 0;
                amountSold.setValue(0);
            } else {
                amountSold.setValue(amount);
            }
            account.setAvailableRevenue(value);

            ExchangeAccountDatabase.getInstance().delete(account);
            ExchangeAccountDatabase.getInstance().create(account);
        }
            matchingOffers.forEach(exchangeOffer -> {

                if ((amountSold.value() + exchangeOffer.getItemQuantity()) <= amount) {
                    amountSold.add(exchangeOffer.getItemQuantity());

                    ExchangeOfferDatabase.getInstance().delete(exchangeOffer);

                    ExchangeAccountDatabase.getInstance().delete(account);
                    ExchangeAccountDatabase.getInstance().create(account);
                } else {
                    int remainder = amount - amountSold.value();
                    amountSold.add(remainder);

                    exchangeOffer.setItemQuantity(exchangeOffer.getItemQuantity() - remainder);

                    ExchangeAccountDatabase.getInstance().delete(account);
                    ExchangeAccountDatabase.getInstance().create(account);

                    ExchangeOfferDatabase.getInstance().delete(exchangeOffer);
                    ExchangeOfferDatabase.getInstance().create(exchangeOffer);
                }
            });
        return amountSold.value();
    }

    private void messagePlayer(long id, String message) {
        PlayerHandler.getPlayers().stream().filter(player1 -> player1.getContext().getId() == id)
                .forEach(player1 -> player1.sendMessage(message));
    }

    private void listOffer(Player player, int itemId, int amount, int unitPrice) {
        ExchangeOfferDatabase.getInstance().create(new ExchangeOffer(
                IDManager.getUUID(),
                player.getContext().getId(),
                itemId,
                amount,
                unitPrice,
                System.currentTimeMillis(),
                ExchangeOffer.SELL
        ));
    }

    public ExchangeCollectionMerchant() {
        super(new ArrayList<>(), 995, 2149, "The Exchange", -1, new ArrayList<>());
    }
}

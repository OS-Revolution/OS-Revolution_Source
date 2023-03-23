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


public class ExchangeMerchant extends Merchant {


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
//        final int sumOfOffers = ExchangeOfferDatabase.getInstance().getAllEntries().stream()
//                .filter(exchangeOffer -> exchangeOffer.getItemId() == itemId)
//                .filter(exchangeOffer -> exchangeOffer.getOfferType() == ExchangeOffer.SELL)
//                .mapToInt(ExchangeOffer::getItemQuantity).sum();
//        final int totalOffers = sumOfOffers / (ItemIdContextLoader.getInstance().read(itemId).getBuyLimit() * BUY_LIMIT_MULTIPLIER);
//        final int basePrice = ItemIdContextLoader.getInstance().read(itemId).getValue();
//        final int exchangePrice = ItemIdContextLoader.getInstance().read(itemId).getExchange();
//        final int retailPrice = (int) (exchangePrice - (totalOffers * (exchangePrice * 0.15d)));
//        if (retailPrice <= 0)
//            return (int) (basePrice * 0.5);
//        return adjustedPrice;
        return ItemIdContextLoader.getInstance().read(itemId).getExchange();
    }

    @Override
    protected int getPriceMerchantWillBuyFor(int itemId) {
//        int adjustedItemId = ItemIdContextLoader.getInstance().read(itemId).isNoted() ? itemId -= 1 : itemId;
//        final int sumOfOffers = ExchangeOfferDatabase.getInstance().getAllEntries().stream()
//                .filter(exchangeOffer -> exchangeOffer.getItemId() == adjustedItemId)
//                .filter(exchangeOffer -> exchangeOffer.getOfferType() == ExchangeOffer.SELL)
//                .mapToInt(ExchangeOffer::getItemQuantity).sum();
//        final int totalOffers = sumOfOffers / (ItemIdContextLoader.getInstance().read(adjustedItemId).getBuyLimit() * BUY_LIMIT_MULTIPLIER);
//        final int basePrice = ItemIdContextLoader.getInstance().read(itemId).getValue();
//        final int adjustedPrice = (int) (basePrice - (totalOffers * (basePrice * 0.15d)));
//        if (adjustedPrice <= 0)
//            return (int) (basePrice * 0.5);
//        return adjustedPrice;
        return ItemIdContextLoader.getInstance().read(itemId).getExchange();
    }

    @Override
    public String getPriceForItemBeingSoldToShop(int itemId) {
        if (!this.getBuyBackIds().contains(itemId) && ItemIdContextLoader.getInstance().read(itemId).isTradable()
                && ((ItemIdContextLoader.getInstance().read(itemId).isNoteable() || ItemIdContextLoader.getInstance().read(itemId).isNoted()))
                || ItemIdContextLoader.getInstance().read(itemId).isStackable())
            return "These will list for #" + this.getPriceMerchantWillBuyFor(itemId) + " @"
                    + this.getCurrencyId() + " per @" + itemId;
        return "The shop will not buy this";
    }

    @Override
    public boolean buyItemFromPlayer(int itemId, int amount, int slot, Player player) {
        final int unitPrice = this.getPriceMerchantWillBuyFor(itemId);
        if (player.getAttributes().getExchangeSlots() > ExchangeAccountDatabase.getInstance().read(player.getContext().getId()).getTotalActiveOffers()) {
            if (!this.getBuyBackIds().contains(itemId) && ItemIdContextLoader.getInstance().read(itemId).isTradable()
                    && ((ItemIdContextLoader.getInstance().read(itemId).isNoteable() || ItemIdContextLoader.getInstance().read(itemId).isNoted()))
                    || ItemIdContextLoader.getInstance().read(itemId).isStackable()) {
                if (player.getItems().playerHasItem(itemId, amount)) {
                    player.getItems().deleteItem2(itemId, amount);
                    if (!ItemIdContextLoader.getInstance().read(itemId).isNoted())
                        this.listOffer(player, itemId, amount, unitPrice);
                    else
                        this.listOffer(player, itemId - 1, amount, unitPrice);
                    player.sendMessage("You list your #" + amount + " @" + itemId + " in exchange for #"
                            + unitPrice + " @" + this.getCurrencyId() + " ea.");

                    player.getItems().resetItems(3823);
                    return true;
                } else {
                    player.sendMessage("You can't sell what you don't have.");
                }
            } else {
                player.sendMessage("The shop will not buy this");
            }
        } else {
            player.sendMessage("You've reached your maximum offer limit of #" + player.getAttributes().getExchangeSlots());
        }
        return false;
    }


    @Override
    public boolean sellItemToPlayer(int itemId, int amount, int slot, Player player) {
        final int price = this.getPriceMerchantWillSellFor(itemId) * amount;
        final int unitPrice = this.getPriceMerchantWillSellFor(itemId);

        if (player.getItems().playerHasItem(this.getCurrencyId(), price)) {
            if (player.getItems().freeSlots() > 1) {
                final int amountSold = this.makeExchange(player, itemId, amount, unitPrice);
                player.getItems().deleteItem(this.getCurrencyId(), unitPrice * amountSold);
                player.getItems().addItem(ItemIdContextLoader.getInstance().read(itemId).isStackable() ? itemId : itemId + 1, amountSold);
                player.getItems().resetItems(3823);
                this.getMerchandiseSlot(itemId).setAmount(this.getMerchandiseSlot(itemId).getAmount() - amountSold);
                PlayerHandler.getPlayers().forEach(this::updateShop);
                player.sendMessage("You bought #" + amountSold + " @" + itemId + " for #" + (unitPrice * amountSold) + " @" + this.getCurrencyId());
                return true;
            } else {
                player.sendMessage("You do not have enough inventory space.");
            }
        } else {
            player.sendMessage("Come back when you're a little bit...richer!");
        }
        return false;
    }

    protected void initializeMerchandiseForPlayer(Player player) {
        this.getMerchandise().clear();
        final Map<Integer, AdjustableInteger> itemMap = new HashMap<>();
        ExchangeOfferDatabase.getInstance().getAllEntries().stream()
                .filter(exchangeOffer -> exchangeOffer.getUserId() != player.getContext().getId())
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
    }

    private int makeExchange(Player player, int itemId, int amount, int unitPrice) {
        final Collection<ExchangeOffer> matchingOffers = ExchangeOfferDatabase.getInstance().getAllEntries().stream()
                .filter(exchangeOffer -> exchangeOffer.getUserId() != player.getContext().getId())
                .filter(exchangeOffer -> exchangeOffer.getItemId() == itemId)
//                .filter(exchangeOffer -> exchangeOffer.getPricePerItem() <= unitPrice)
                .collect(Collectors.toList());
        final List<ExchangeOffer> selectedOffers = new ArrayList<>();

        while (!matchingOffers.isEmpty() && selectedOffers.stream().mapToInt(ExchangeOffer::getItemQuantity).sum() < amount) {
            final ExchangeOffer oldestMatchingOffer = Collections.min(matchingOffers, new ExchangeOfferTimestampComparator());
            matchingOffers.remove(oldestMatchingOffer);
            selectedOffers.add(oldestMatchingOffer);
        }

        final AdjustableNumber<Integer> amountSold = new AdjustableInteger(0);
        selectedOffers.forEach(exchangeOffer -> {
            ExchangeAccount account = ExchangeAccountDatabase.getInstance().read(exchangeOffer.getUserId());
            if ((amountSold.value() + exchangeOffer.getItemQuantity()) <= amount) {
                amountSold.add(exchangeOffer.getItemQuantity());
                ExchangeOfferDatabase.getInstance().delete(exchangeOffer);

                account.setAvailableRevenue(
                        account.getAvailableRevenue() + ((long) exchangeOffer.getItemQuantity() * this.getPriceMerchantWillSellFor(exchangeOffer.getItemId()))
                );

                ExchangeAccountDatabase.getInstance().delete(account);
                ExchangeAccountDatabase.getInstance().create(account);

                this.messagePlayer(exchangeOffer.getUserId(), "^Exchange Finished selling #" + exchangeOffer.getItemQuantity() + " of your @" + exchangeOffer.getItemId()
                        + " for #" + (exchangeOffer.getItemQuantity() * this.getPriceMerchantWillSellFor(exchangeOffer.getItemId())) + " / #" + (amount * exchangeOffer.getPricePerItem()) + " GP."
                );
                this.messagePlayer(exchangeOffer.getUserId(), "^Exchange Total Profit: #"
                        + ((this.getPriceMerchantWillSellFor(exchangeOffer.getItemId()) * exchangeOffer.getItemQuantity()) - (exchangeOffer.getPricePerItem() * exchangeOffer.getItemQuantity())) + " GP.");
            } else {
                int remainder = amount - amountSold.value();
                amountSold.add(remainder);
                this.messagePlayer(exchangeOffer.getUserId(),
                        "^Exchange Sold #" + remainder + " / #" + exchangeOffer.getItemQuantity() + " of your @" + exchangeOffer.getItemId()
                                + " for #" + (remainder * this.getPriceMerchantWillSellFor(exchangeOffer.getItemId())) + " / #" + (remainder * exchangeOffer.getPricePerItem()) + " GP."
                );

                this.messagePlayer(exchangeOffer.getUserId(), "^Exchange Total Profit: #"
                        + ((this.getPriceMerchantWillSellFor(exchangeOffer.getItemId()) * remainder) - (exchangeOffer.getPricePerItem() * remainder)) + " GP.");
                exchangeOffer.setItemQuantity(exchangeOffer.getItemQuantity() - remainder);

                account.setAvailableRevenue(
                        account.getAvailableRevenue() + ((long) remainder * this.getPriceMerchantWillSellFor(exchangeOffer.getItemId()))
                );

                ExchangeAccountDatabase.getInstance().delete(account);
                ExchangeAccountDatabase.getInstance().create(account);

                ExchangeOfferDatabase.getInstance().delete(exchangeOffer);
                ExchangeOfferDatabase.getInstance().create(exchangeOffer);
            }
            this.messagePlayer(exchangeOffer.getUserId(), "^Exchange See $Exchange $Clerk to collect #"
                    + ExchangeAccountDatabase.getInstance().read(exchangeOffer.getUserId()).getAvailableRevenue() + " GP.");
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

    public ExchangeMerchant() {
        super(new ArrayList<>(), 995, 2148, "The Exchange", -1, List.of(995, 13204));
    }
}

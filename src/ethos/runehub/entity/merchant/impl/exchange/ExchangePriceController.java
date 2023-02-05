package ethos.runehub.entity.merchant.impl.exchange;

import ethos.model.players.PlayerHandler;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.model.entity.item.ItemContext;

public class ExchangePriceController {

    private static final int UPDATE_RATE_MINUTES = 1440;
    private static final int BUY_LIMIT_MULTIPLIER = 1;
    public static final double MAX_PRICE_INCREASE = 11.5;
    public static final double MAX_PRICE_DECREASE = 0.5;

    private static ExchangePriceController instance = null;

    public static ExchangePriceController getInstance() {
        if (instance == null)
            instance = new ExchangePriceController();
        return instance;
    }

    public void updatePrices() {
        ExchangeOfferDatabase.getInstance().getAllEntries().forEach(exchangeOffer -> {
            ItemContext context = ItemIdContextLoader.getInstance().read(exchangeOffer.getItemId());
            ItemContext notedContext = ItemIdContextLoader.getInstance().read(exchangeOffer.getItemId() + 1);
            int itemId = exchangeOffer.getItemId();
            final int sumOfOffers = ExchangeOfferDatabase.getInstance().getAllEntries().stream()
                    .filter(offer -> offer.getItemId() == itemId)
                    .filter(offer -> offer.getOfferType() == ExchangeOffer.SELL)
                    .mapToInt(ExchangeOffer::getItemQuantity).sum();
            final int totalOffers = sumOfOffers / (ItemIdContextLoader.getInstance().read(itemId).getBuyLimit() * BUY_LIMIT_MULTIPLIER);
            final int basePrice = ItemIdContextLoader.getInstance().read(itemId).getValue();
            final int exchangePrice = ItemIdContextLoader.getInstance().read(itemId).getExchange();

            int updatedPrice = (int) (exchangePrice - (totalOffers * (exchangePrice * 0.15d)));
            if (updatedPrice < (int) (basePrice * MAX_PRICE_DECREASE) && totalOffers > 1) {
                updatedPrice = (int) (basePrice * MAX_PRICE_DECREASE);
            } else if(totalOffers <= 1 && (exchangePrice * 1.15d) < basePrice * MAX_PRICE_INCREASE) {
                if ((int) (exchangePrice * 1.15d) == exchangePrice) {
                    updatedPrice = exchangePrice+1;
                } else {
                    updatedPrice = (int) (exchangePrice * 1.15d);
                }
            }
            System.out.println("Updated Price of " + context.getName() + " from " + context.getExchange() + " to " + updatedPrice);
            context.setExchange(updatedPrice);
            notedContext.setExchange(updatedPrice);

            ItemIdContextLoader.getInstance().deleteAndPush(itemId);
            ItemIdContextLoader.getInstance().createAndPush(itemId,context);

            ItemIdContextLoader.getInstance().deleteAndPush(itemId+1);
            ItemIdContextLoader.getInstance().createAndPush(itemId+1,notedContext);

        });
        PlayerHandler.executeGlobalMessage("^Exchange Prices have been updated!");
    }

    private ExchangePriceController(){}
}

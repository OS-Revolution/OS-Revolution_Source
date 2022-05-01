package ethos.runehub.event.shop.impl;

import ethos.event.Event;
import ethos.model.players.PlayerHandler;
import ethos.runehub.entity.merchant.impl.exchange.ExchangeOffer;
import ethos.runehub.entity.merchant.impl.exchange.ExchangeOfferDatabase;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.model.entity.item.ItemContext;

import java.util.List;

public class ExchangePriceUpdateEvent extends Event<List<ExchangeOffer>> {

    private static final int UPDATE_RATE_MINUTES = 1440;
    private static final int BUY_LIMIT_MULTIPLIER = 1;

    @Override
    public void execute() {
        attachment.forEach(exchangeOffer -> {
            ItemContext context = ItemIdContextLoader.getInstance().read(exchangeOffer.getItemId());
            ItemContext notedContext = ItemIdContextLoader.getInstance().read(exchangeOffer.getItemId() + 1);
            int itemId = exchangeOffer.getItemId();
            final int sumOfOffers = attachment.stream()
                    .filter(offer -> offer.getItemId() == itemId)
                    .filter(offer -> offer.getOfferType() == ExchangeOffer.SELL)
                    .mapToInt(ExchangeOffer::getItemQuantity).sum();
            final int totalOffers = sumOfOffers / (ItemIdContextLoader.getInstance().read(itemId).getBuyLimit() * BUY_LIMIT_MULTIPLIER);
            final int basePrice = ItemIdContextLoader.getInstance().read(itemId).getValue();
            final int exchangePrice = ItemIdContextLoader.getInstance().read(itemId).getExchange();

            int updatedPrice = (int) (exchangePrice - (totalOffers * (exchangePrice * 0.15d)));
            if (updatedPrice < (int) (basePrice * 0.5) && totalOffers > 1) {
                updatedPrice = (int) (basePrice * 0.5);
            } else if(totalOffers <= 1 && (exchangePrice * 1.15d) < basePrice * 2.15) {
                updatedPrice = (int) (exchangePrice * 1.15d);
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

    public ExchangePriceUpdateEvent() {
        super("exchange-update", ExchangeOfferDatabase.getInstance().getAllEntries(), 100 * UPDATE_RATE_MINUTES);
    }
}

package ethos.runehub.event.shop.impl;

import ethos.event.Event;
import ethos.model.players.PlayerHandler;
import ethos.runehub.entity.merchant.impl.exchange.ExchangeOffer;
import ethos.runehub.entity.merchant.impl.exchange.ExchangeOfferDatabase;
import ethos.runehub.entity.merchant.impl.exchange.ExchangePriceController;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.model.entity.item.ItemContext;

import java.util.List;

public class ExchangePriceUpdateEvent extends Event<List<ExchangeOffer>> {

    private static final int UPDATE_RATE_MINUTES = 1440;

    @Override
    public void execute() {
        ExchangePriceController.getInstance().updatePrices();
    }

    public ExchangePriceUpdateEvent() {
        super("exchange-update", ExchangeOfferDatabase.getInstance().getAllEntries(), 100 * UPDATE_RATE_MINUTES);
    }

}

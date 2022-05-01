package ethos.runehub.entity.merchant.impl.exchange;

import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class ExchangeOfferDatabase extends BetaAbstractDataAcessObject<ExchangeOffer> {

    private static ExchangeOfferDatabase instance = null;

    public static ExchangeOfferDatabase getInstance() {
        if (instance == null)
            instance = new ExchangeOfferDatabase();
        return instance;
    }

    private ExchangeOfferDatabase() {
        super("./Data/runehub/db/exchange.db", ExchangeOffer.class);
    }
}

package ethos.runehub.entity.merchant.impl.exchange;

import ethos.runehub.RunehubConstants;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class ExchangeOfferDatabase extends BetaAbstractDataAcessObject<ExchangeOffer> {

    private static ExchangeOfferDatabase instance = null;

    public static ExchangeOfferDatabase getInstance() {
        if (instance == null)
            instance = new ExchangeOfferDatabase();
        return instance;
    }

    private ExchangeOfferDatabase() {
        super(RunehubConstants.EXCHANGE_DB, ExchangeOffer.class);
    }
}

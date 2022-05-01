package ethos.runehub.entity.merchant.impl.exchange;

import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class ExchangeAccountDatabase extends BetaAbstractDataAcessObject<ExchangeAccount> {

    private static ExchangeAccountDatabase instance = null;

    public static ExchangeAccountDatabase getInstance() {
        if (instance == null)
            instance = new ExchangeAccountDatabase();
        return instance;
    }

    private ExchangeAccountDatabase() {
        super("./Data/runehub/db/exchange.db", ExchangeAccount.class);
    }
}

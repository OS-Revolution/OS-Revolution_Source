package ethos.runehub.entity.merchant;

import ethos.runehub.entity.merchant.impl.MinnowMerchant;
import ethos.runehub.entity.merchant.impl.exchange.ExchangeCollectionMerchant;
import ethos.runehub.entity.merchant.impl.exchange.ExchangeMerchant;
import org.runehub.api.io.load.LazyLoader;
import ethos.runehub.entity.merchant.impl.CommodityMerchant;

public class MerchantCache extends LazyLoader<Integer,Merchant> {

    private static MerchantCache instance = null;

    public static MerchantCache getInstance() {
        if (instance == null)
            instance = new MerchantCache();
        return instance;
    }

    @Override
    protected Merchant load(Integer shopId) {
        switch (shopId) {
            case 2148:
                return new ExchangeMerchant();
            case 2149:
                return new ExchangeCollectionMerchant();
            case 1328:
                return new CommodityMerchant();
            case 7727:
                return new MinnowMerchant();
            default: return null;
        }
    }

    private MerchantCache() {
        super(null);
    }
}

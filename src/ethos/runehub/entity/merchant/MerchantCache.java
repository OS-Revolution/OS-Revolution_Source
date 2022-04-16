package ethos.runehub.entity.merchant;

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
            case 1328:
                return new CommodityMerchant();
            default: return null;
        }
    }

    private MerchantCache() {
        super(null);
    }
}

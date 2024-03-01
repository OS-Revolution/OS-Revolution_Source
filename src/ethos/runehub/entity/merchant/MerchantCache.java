package ethos.runehub.entity.merchant;

import ethos.runehub.entity.merchant.impl.*;
import ethos.runehub.entity.merchant.impl.exchange.ExchangeCollectionMerchant;
import ethos.runehub.entity.merchant.impl.exchange.ExchangeMerchant;
import ethos.runehub.skill.support.sailing.merchant.BoughtTradeGoodMerchant;
import ethos.runehub.skill.support.sailing.merchant.SailingStockpileMerchant;
import ethos.runehub.skill.support.sailing.merchant.SoldTradeGoodMerchant;
import org.runehub.api.io.load.LazyLoader;

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
            case 1039:
                return new JewelMerchant();
            case 2148:
                return new ExchangeMerchant();
            case 2149:
                return new ExchangeCollectionMerchant();
            case 1328:
                return new CommodityMerchant();
            case 7727:
                return new MinnowMerchant();
            case 5567:
                return new DeathMerchant();
            case 506:
                return new GeneralMerchant();
            case 921:
                return new HolyMerchant();
            case 637:
                return new AubreyMerchant();
            case 6059:
                return new HomeArchMerchant();
            case 2471:
                return new HomeWeaponMerchant();
            case 1079:
                return new HomeClothingMerchant();
            case 401:
            case 402:
            case 403:
            case 404:
            case 405:
            case 6797:
                return new SlayerPointMerchant();
            case 5832:
                return new MasterFarmerMerchant();
            case 7528:
                return new FermentingVatMerchant();
            case 50000:
                return new BoughtTradeGoodMerchant();
            case 50001:
                return new SoldTradeGoodMerchant();
            case 50002:
                return new SailingStockpileMerchant();
            case 2658:
                return new HeadChefMerchant();
            case 4274:
                return new LeelaSmithingMerchant();
            default: return null;
        }
    }

    private MerchantCache() {
        super(null);
    }
}

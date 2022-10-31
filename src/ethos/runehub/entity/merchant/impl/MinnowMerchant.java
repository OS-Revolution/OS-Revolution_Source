package ethos.runehub.entity.merchant.impl;

import ethos.runehub.entity.merchant.Merchant;
import org.runehub.api.io.load.impl.ItemIdContextLoader;

import java.util.List;

public class MinnowMerchant extends Merchant {

    @Override
    public int getPriceMerchantWillSellFor(int itemId) {
        switch (itemId) {
            case 384:
                return 40;
        }
        return ItemIdContextLoader.getInstance().read(itemId).getValue();
    }

    public MinnowMerchant( ) {
        super(21356, 7727, "Minnow Merchant", 8281273871449345314L, List.of());
    }
}

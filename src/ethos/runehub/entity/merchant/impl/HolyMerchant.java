package ethos.runehub.entity.merchant.impl;

import ethos.runehub.entity.merchant.Merchant;
import java.util.List;

import org.runehub.api.io.load.impl.ItemIdContextLoader;

public class HolyMerchant extends Merchant {
	
    @Override
    public int getPriceMerchantWillSellFor(int itemId) {
        switch (itemId) {
        case 201:
            return 150;
        case 20199:
            return 150000;
        case 20202:
            return 150000;
        }
        
        return ItemIdContextLoader.getInstance().read(itemId).getValue();
    }

	  public HolyMerchant( ) {
	        super(995, 921, "Holy & Unholy Goods", 3668749851966194263L, List.of());
	    }
	}

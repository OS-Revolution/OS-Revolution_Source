package ethos.runehub.entity.merchant.impl;

import ethos.runehub.entity.merchant.Merchant;
import java.util.List;

import org.runehub.api.io.load.impl.ItemIdContextLoader;

public class HomeClothingMerchant extends Merchant {
	
    @Override
    public int getPriceMerchantWillSellFor(int itemId) {
        switch (itemId) {
        case 9005:
            return 1000000;
        case 9006:
            return 25000;
        }
        
        return ItemIdContextLoader.getInstance().read(itemId).getValue();
    }

	  public HomeClothingMerchant( ) {
	        super(995, 1079, "Clothes & Fits", 3298885000194181467L, List.of());
	    }
	}

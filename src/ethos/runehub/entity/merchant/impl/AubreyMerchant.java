package ethos.runehub.entity.merchant.impl;

import ethos.runehub.entity.merchant.Merchant;
import java.util.List;

import org.runehub.api.io.load.impl.ItemIdContextLoader;

public class AubreyMerchant extends Merchant {
	
    @Override
    public int getPriceMerchantWillSellFor(int itemId) {
        switch (itemId) {
        case 1438: //AIR
        case 1448: //MIND
        case 1446: //BODY
        case 1440: //EARTH
        case 1444: //WATER
        case 1442: //FIRE
            return 5000;
        case 1452: //CHAOS
        case 1454: //COSMIC
        case 1456: //DEATH
        case 1458: //LAW
        case 1462: //NATURE
        	return 10000;
        }
        
        return ItemIdContextLoader.getInstance().read(itemId).getValue();
    }

	  public AubreyMerchant( ) {
	        super(995, 637, "Runecrafting Supplies", -8951720902772504283L, List.of());
	    }
	}

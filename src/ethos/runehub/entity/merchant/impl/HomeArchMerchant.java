package ethos.runehub.entity.merchant.impl;

import ethos.runehub.entity.merchant.Merchant;
import java.util.List;

import org.runehub.api.io.load.impl.ItemIdContextLoader;

public class HomeArchMerchant extends Merchant {
	
    @Override
    public int getPriceMerchantWillSellFor(int itemId) {
        switch (itemId) {
        case 3749: //ARCHERS HELM
            return 25000;
        case 6733: //ARCHERERS RING
        	return 65000;
        case 10499: //AVA'S ACCUMULATOR
        	return 50000;
        case 10498: //AVA'S ATTRACTOR
        	return 25000;
        case 22109: //AVA'S ASSEMBLER
        	return 100000;
        }
        
        return ItemIdContextLoader.getInstance().read(itemId).getValue();
    }

	  public HomeArchMerchant( ) {
	        super(995, 6059, "Get It N' Go Archery Supplies", -8054511562649025053L, List.of());
	    }
	}

package ethos.runehub.entity.merchant.impl;

import ethos.runehub.entity.merchant.Merchant;
import java.util.List;

import org.runehub.api.io.load.impl.ItemIdContextLoader;

public class HomeWeaponMerchant extends Merchant {
	
    @Override
    public int getPriceMerchantWillSellFor(int itemId) {
        switch (itemId) {

        }
        
        return ItemIdContextLoader.getInstance().read(itemId).getValue();
    }

	  public HomeWeaponMerchant( ) {
	        super(995, 2471, "Weapons & More", 1806864748365433983L, List.of());
	    }
	}

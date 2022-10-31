package ethos.runehub.entity.merchant.impl;

import ethos.runehub.entity.merchant.MerchandiseSlot;
import ethos.runehub.entity.merchant.Merchant;

import java.util.List;

public class RotatingStockMerchant extends Merchant {

    public void rotateStock() {
        this.getMerchandise().clear();
        this.initializeStock();
    }

    protected void initializeStock() {
        this.getLootTable().roll(merchandiseCapacity, baseMagicFind).forEach(loot -> {
            if (this.getMerchandise().size() < merchandiseCapacity && this.getMerchandise().stream().noneMatch(merchandiseSlot -> merchandiseSlot.getItemId() == loot.getId()))
                this.getMerchandise().add(new MerchandiseSlot(
                        (int) loot.getId(),
                        (int) loot.getAmount(),
                        false,
                        0.0D,
                        false
                ));
        });
    }

    protected float getBaseMagicFind() {
        return baseMagicFind;
    }

    protected int getMerchandiseCapacity() {
        return merchandiseCapacity;
    }

    public RotatingStockMerchant(int currencyId, int shopId, String name, long merchandiseTableId, List<Integer> buyBackIds, float baseMagicFind,
                                 int merchandiseCapacity) {
        super(currencyId, shopId, name, merchandiseTableId, buyBackIds);
        this.baseMagicFind = baseMagicFind;
        this.merchandiseCapacity = merchandiseCapacity;
    }

    private final float baseMagicFind;
    private final int merchandiseCapacity;
}

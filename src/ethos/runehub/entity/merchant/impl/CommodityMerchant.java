package ethos.runehub.entity.merchant.impl;

import ethos.runehub.entity.merchant.MerchandiseSlot;
import ethos.runehub.skill.Skill;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.io.load.impl.LootTableLoader;
import org.runehub.api.model.math.impl.DoubleRange;

import java.util.List;

public class CommodityMerchant extends RotatingStockMerchant {

    private static final List<Integer> COMMODITIES = List.of(7472, 2365);
    private static final int MAX_STOCK = (int) (COMMODITIES.size() + (LootTableLoader.getInstance().read(4225145936390343693L).getLootTableEntries().size() * 0.1f));

    @Override
    public int getPriceMerchantWillSellFor(int itemId) {
        return itemId == saleItemId ? (int) (ItemIdContextLoader.getInstance().read(itemId).getValue() * sale) :
                ItemIdContextLoader.getInstance().read(itemId).getValue();
    }

    protected void initializeStock() {
        this.getLootTable().roll(this.getMerchandiseCapacity(), this.getBaseMagicFind()).forEach(loot -> {
            if (this.getMerchandise().size() < this.getMerchandiseCapacity() && this.getMerchandise().stream().noneMatch(merchandiseSlot -> merchandiseSlot.getItemId() == loot.getId()))
                this.getMerchandise().add(new MerchandiseSlot(
                        (int) loot.getId(),
                        (int) loot.getAmount(),
                        true,
                        0.5D
                ));
        });

        this.saleItemId = this.getSaleItemId();
        this.sale = this.getSale(saleItemId);
    }

    @Override
    public int getSaleItemId() {
        int itemId = this.getMerchandise().get(Skill.SKILL_RANDOM.nextInt(this.getMerchandise().size())).getItemId();
        if (COMMODITIES.stream().anyMatch(commodity -> commodity == itemId) && this.getMerchandiseSlot(itemId).isSalePossible()) {
            return getSaleItemId();
        }
        return itemId;
    }

    public CommodityMerchant() {
        super(1459, 1328, "Jewel Shop", 4225145936390343693L, COMMODITIES, 1.0f, MAX_STOCK);
    }

    private double sale;
    private int saleItemId;
}

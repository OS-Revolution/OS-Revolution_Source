package ethos.runehub.event.shop.impl;

import ethos.runehub.entity.merchant.MerchantCache;
import ethos.runehub.event.FixedScheduleEvent;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class SpecialtyShopRestockEvent extends FixedScheduleEvent {

    @Override
    public void execute() {
       MerchantCache.getInstance().read(4274).restock();
        MerchantCache.getInstance().read(2658).restock();
    }

    public SpecialtyShopRestockEvent() {
        super(Duration.of(60, ChronoUnit.MINUTES).toMillis(),"specialty restock");
    }

}

package ethos.runehub.event.shop.impl;

import ethos.runehub.entity.merchant.MerchantCache;
import ethos.runehub.event.FixedScheduleEvent;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class GeneralStoreRestockEvent extends FixedScheduleEvent {

    @Override
    public void execute() {
       MerchantCache.getInstance().read(506).restock();
    }

    public GeneralStoreRestockEvent() {
        super(Duration.of(10, ChronoUnit.MINUTES).toMillis(),"general store restock");
    }

}

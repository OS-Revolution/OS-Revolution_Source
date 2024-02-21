package ethos.runehub.event;

import ethos.model.players.PlayerHandler;
import ethos.runehub.TimeUtils;
import ethos.runehub.db.PlayerCharacterContextDataAccessObject;
import ethos.runehub.entity.merchant.impl.exchange.ExchangePriceController;
import ethos.runehub.event.chest.PromotionalChestEventImpl;
import ethos.runehub.event.dnd.WeekendBonusFixedScheduleEvent;
import ethos.runehub.world.MembershipController;
import ethos.runehub.world.WorldSettingsController;

import java.time.*;

public abstract class WeeklyResetEvent extends FixedScheduleEvent {

    public abstract void onExecute();

    @Override
    public void execute() {
        System.out.println("Weekly Reset");
        WorldSettingsController.getInstance().getWorldSettings().setWeeklyResetTimestamp(System.currentTimeMillis());
//        FixedScheduledEventController.getInstance().forceEvent(new PromotionalChestEventImpl());
        WorldSettingsController.getInstance().saveSettings();
    }

    @Override
    protected void onInitialize() {
        final long lastReset = WorldSettingsController.getInstance().getWorldSettings().getWeeklyResetTimestamp();
        if (TimeUtils.getDurationBetween(lastReset, ZonedDateTime.now(ZoneId.of("UTC")).toInstant().toEpochMilli()).toDays() >= 7
        || ZonedDateTime.ofInstant(Instant.ofEpochMilli(lastReset),ZoneId.of("UTC")).getDayOfYear() <
                ZonedDateTime.now(ZoneId.of("UTC")).getDayOfYear()) {
            System.out.println("Executing late-start weekly reset");
            this.execute();
        }
    }

    public WeeklyResetEvent() {
        super(Duration.ofDays(7).toMillis(), "weekly-reset");
    }
}

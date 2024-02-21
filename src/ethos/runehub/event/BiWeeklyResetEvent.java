package ethos.runehub.event;

import ethos.runehub.TimeUtils;
import ethos.runehub.event.chest.PromotionalChestEventImpl;
import ethos.runehub.world.WorldSettingsController;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class BiWeeklyResetEvent extends FixedScheduleEvent {

    @Override
    public void execute() {
        System.out.println("Bi-Weekly Reset");
        WorldSettingsController.getInstance().getWorldSettings().setWeeklyResetTimestamp(System.currentTimeMillis());
        FixedScheduledEventController.getInstance().forceEvent(new PromotionalChestEventImpl());
        WorldSettingsController.getInstance().saveSettings();
    }

    @Override
    protected void onInitialize() {
        final long lastReset = WorldSettingsController.getInstance().getWorldSettings().getWeeklyResetTimestamp();
        if (TimeUtils.getDurationBetween(lastReset, ZonedDateTime.now(ZoneId.of("UTC")).toInstant().toEpochMilli()).toDays() >= 14
        || ZonedDateTime.ofInstant(Instant.ofEpochMilli(lastReset),ZoneId.of("UTC")).getDayOfYear() <
                ZonedDateTime.now(ZoneId.of("UTC")).getDayOfYear()) {
            System.out.println("Executing late-start bi-weekly reset");
            this.execute();
        }
    }

    public BiWeeklyResetEvent() {
        super(Duration.ofDays(14).toMillis(), "bi-weekly-reset");
    }
}

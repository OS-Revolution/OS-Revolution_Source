package ethos.runehub.event;

import ethos.model.players.PlayerHandler;
import ethos.runehub.TimeUtils;
import ethos.runehub.db.PlayerCharacterContextDataAccessObject;
import ethos.runehub.entity.merchant.impl.exchange.ExchangePriceController;
import ethos.runehub.event.chest.PromotionalChestEventImpl;
import ethos.runehub.event.dnd.WeekendBonusFixedScheduleEvent;
import ethos.runehub.world.MembershipController;
import ethos.runehub.world.WorldSettingsController;

import java.sql.Time;
import java.time.*;
import java.time.temporal.ChronoUnit;

public class DailyResetEvent extends FixedScheduleEvent {

    @Override
    public void execute() {
        System.out.println("Resetting Dailies");
        WorldSettingsController.getInstance().getWorldSettings().setLastDailyResetTimestamp(System.currentTimeMillis());
        ExchangePriceController.getInstance().updatePrices();
        PlayerCharacterContextDataAccessObject.getInstance().getAllEntries().forEach(ctx -> {
            if (PlayerHandler.isPlayerOn(ctx.getId())) {
                PlayerHandler.getPlayer(ctx.getId()).ifPresent(player -> {
                    MembershipController.getInstance().updateMembership(player);
                    player.getContext().getPlayerSaveData().setDailiesAvailable(true);
                    player.save();
                    player.sendMessage("Your daily activities have been refreshed! Please log back in to do them.");
                });
            } else {
                MembershipController.getInstance().updateMembership(ctx);
                ctx.getPlayerSaveData().setDailiesAvailable(true);
                PlayerCharacterContextDataAccessObject.getInstance().update(ctx);
            }
        });

        if (ZonedDateTime.now(ZoneId.of("UTC")).getDayOfWeek().getValue() >= DayOfWeek.FRIDAY.getValue() && WorldSettingsController.getInstance().getWorldSettings().getWeekendEventId() <= 0) {
            FixedScheduledEventController.getInstance().forceEvent(new WeekendBonusFixedScheduleEvent());
        } else if (ZonedDateTime.now(ZoneId.of("UTC")).getDayOfWeek().getValue() < DayOfWeek.FRIDAY.getValue()) {
            WorldSettingsController.getInstance().getWorldSettings().setWeekendEventId(0);
        }

//        Instant lastEventTimestamp = Instant.ofEpochMilli(WorldSettingsController.getInstance().getWorldSettings().getLastChestEventTimestamp());
//        Instant now = Instant.now();
//
//        Duration durationBetween = Duration.between(lastEventTimestamp, now);
//        if (durationBetween.toDays() >= 14) {
//            FixedScheduledEventController.getInstance().forceEvent(new PromotionalChestEventImpl());
//        }


        WorldSettingsController.getInstance().saveSettings();
    }

    @Override
    protected void onInitialize() {
        final long lastReset = WorldSettingsController.getInstance().getWorldSettings().getLastDailyResetTimestamp();
        if (TimeUtils.getDurationBetween(lastReset, ZonedDateTime.now(ZoneId.of("UTC")).toInstant().toEpochMilli()).toDays() >= 1
        || ZonedDateTime.ofInstant(Instant.ofEpochMilli(lastReset),ZoneId.of("UTC")).getDayOfYear() <
                ZonedDateTime.now(ZoneId.of("UTC")).getDayOfYear()) {
            System.out.println("Executing late-start daily reset");
            this.execute();
        }
    }

    public DailyResetEvent() {
        super(Duration.ofDays(1).toMillis(), "daily-reset");
    }
}

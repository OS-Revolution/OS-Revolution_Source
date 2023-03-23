package ethos.runehub.event.dnd;

import ethos.model.players.PlayerHandler;
import ethos.runehub.TimeUtils;
import ethos.runehub.event.FixedScheduleEvent;
import ethos.runehub.skill.Skill;
import ethos.runehub.world.WorldSettingsController;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class WeekendBonusEndFixedScheduleEvent extends FixedScheduleEvent {

    @Override
    public void execute() {
        this.setRate(TimeUtils.getDurationUntilDayOfWeek(DayOfWeek.MONDAY));
        WorldSettingsController.getInstance().getWorldSettings().setWeekendEventId(this.getEventId());
        WorldSettingsController.getInstance().saveSettings();
        PlayerHandler.executeGlobalMessage("$" + WorldSettingsController.getInstance().getWeekendBonusName().replaceAll(" ", "_") + " is now active!");
    }

    private int getEventId() {
        int eId = Skill.SKILL_RANDOM.nextInt(4) + 1;
        if (eId == WorldSettingsController.getInstance().getWorldSettings().getWeekendEventId()) {
            return getEventId();
        }
        return eId;
    }



//    public static long getDurationUntilMonday() {
//        ZonedDateTime nowMidnight = ZonedDateTime.now(ZoneId.of("UTC")).plusDays(0).withHour(0).withMinute(0).withSecond(0);
//        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC")).plusDays(0);
//        System.out.println("Day of week " + nowMidnight.getDayOfWeek());
//        int days = DayOfWeek.MONDAY.get(ChronoField.DAY_OF_WEEK) - nowMidnight.getDayOfWeek().getValue();
//        System.out.println("Days: " + days);
//        ZonedDateTime friday = nowMidnight.plusDays(days);
//        Duration durationFromResetToNow = TimeUtils.getDurationBetween(nowMidnight.toInstant().toEpochMilli(),now.toInstant().toEpochMilli());
//        Duration duration = TimeUtils.getDurationBetween(nowMidnight.toInstant().toEpochMilli(),friday.toInstant().toEpochMilli()).minus(durationFromResetToNow);
////        System.out.println(TimeUtils.getDurationString(durationFromResetToNow));
//        if (duration.isNegative()) {
//            System.out.println("Monday is in the past");
//            ZonedDateTime nextMonday = nowMidnight.plus(duration.toMillis(), ChronoUnit.MILLIS).plusWeeks(1).plus(durationFromResetToNow);
//            Duration durationUntilNextMonday = TimeUtils.getDurationBetween(nowMidnight.toInstant().toEpochMilli(),nextMonday.toInstant().toEpochMilli()).minus(durationFromResetToNow);
//            System.out.println(TimeUtils.getZDTString(nextMonday));
//            return durationUntilNextMonday.toMillis();
//        } else if (duration.isZero()) {
//            System.out.println("Today is Monday");
//            return nowMidnight.plusDays(7).toInstant().toEpochMilli();
//        } else {
//            System.out.println("Monday is in the future");
//            System.out.println(TimeUtils.getDurationString(duration));
//        }
//
//        return duration.toMillis();
//    }

    public WeekendBonusEndFixedScheduleEvent() {
        super(TimeUtils.getDurationUntilDayOfWeek(DayOfWeek.MONDAY), "weekend bonus end");
    }
}

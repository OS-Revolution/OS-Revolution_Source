package ethos.runehub.event.holiday;

import ethos.runehub.TimeUtils;
import ethos.runehub.event.FixedScheduleEvent;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class EndChristmasFixedScheduleEvent extends FixedScheduleEvent {

    public static final ZonedDateTime CHRISTMAS_DAY = ZonedDateTime.now(ZoneId.of("UTC")).withMonth(12).withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).plusDays(30);
    public static final Duration DURATION_BETWEEN_FIRST_UNTIL_CHRISTMAS = TimeUtils.getDurationBetween(ZonedDateTime.now(ZoneId.of("UTC")).withMonth(1).withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0), CHRISTMAS_DAY);
//    public static final Duration DURATION_BETWEEN_FIRST_AND_NOW = TimeUtils.getDurationBetween(ZonedDateTime.now(ZoneId.of("UTC")).withMonth(1).withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0), ZonedDateTime.now(ZoneId.of("UTC")));

    @Override
    public void execute() {

    }

    public EndChristmasFixedScheduleEvent() {
        super(DURATION_BETWEEN_FIRST_UNTIL_CHRISTMAS.toMillis(), "Christmas");
    }
}

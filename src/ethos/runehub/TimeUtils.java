package ethos.runehub;

import org.apache.commons.lang3.time.DurationFormatUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class TimeUtils {

    public static long getDaysAsMS(int days) {
        return ((60000 * 60) * 24) * days;
    }

    public static String getDurationString(Duration duration) {
        return getDurationString(duration.toMillis());
    }

    public static String getDurationAsDaysAndHoursString(Duration duration) {
        return DurationFormatUtils.formatDuration(duration.toMillis(), "dd' days, 'HH' hours'", true);
    }

    public static long getDurationUntilDayOfWeek(DayOfWeek dayOfWeek) {
        final int testingVar = 0;
        final ZonedDateTime nowMidnight = ZonedDateTime.now(ZoneId.of("UTC")).plusDays(testingVar).withHour(0).withMinute(0).withSecond(0);
        final ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC")).plusDays(testingVar);
        final int days = dayOfWeek.get(ChronoField.DAY_OF_WEEK) - nowMidnight.getDayOfWeek().getValue();
        final ZonedDateTime targetDoWAsZDT = nowMidnight.plusDays(days);
        final Duration durationFromResetToNow = TimeUtils.getDurationBetween(nowMidnight.toInstant().toEpochMilli(), now.toInstant().toEpochMilli());
        Duration duration = TimeUtils.getDurationBetween(nowMidnight.toInstant().toEpochMilli(), targetDoWAsZDT.toInstant().toEpochMilli()).minus(durationFromResetToNow);
        if (duration.isNegative()) {
            ZonedDateTime nextDoWOccurrence = nowMidnight.plus(duration.toMillis(), ChronoUnit.MILLIS).plusWeeks(1).plus(durationFromResetToNow);
            duration = TimeUtils.getDurationBetween(nowMidnight.toInstant().toEpochMilli(), nextDoWOccurrence.toInstant().toEpochMilli()).minus(durationFromResetToNow);
            System.out.println("Next DoW Occurrence: " + TimeUtils.getZDTString(nextDoWOccurrence));
        } else if (duration.isZero()) {
            System.out.println("Today is " + dayOfWeek);
            return nowMidnight.plusDays(7).toInstant().toEpochMilli();
        }
        final Duration result = TimeUtils.getDurationBetween(now.toInstant().toEpochMilli(),now.plus(duration.toMillis(),ChronoUnit.MILLIS).toInstant().toEpochMilli());
        System.out.println("Now Day of Week: " + nowMidnight.getDayOfWeek() + " Value: " + nowMidnight.getDayOfWeek().getValue());
        System.out.println("Target Day of Week: " + dayOfWeek + " Value: " + dayOfWeek.getValue());
        System.out.println("Days until target DoW: " + days);
        System.out.println("Target DoW as ZDT: " + TimeUtils.getZDTString(targetDoWAsZDT));
        System.out.println("Duration between now start and now actual: " + TimeUtils.getDurationString(durationFromResetToNow));
        System.out.println("Duration between now start and target ZDT: " + TimeUtils.getDurationString(duration));
        System.out.println("Result ZDT: " + TimeUtils.getZDTString(now.plus(duration.toMillis(),ChronoUnit.MILLIS)));
        System.out.println("Result Duration: " + TimeUtils.getDurationString(result));

        return result.toMillis();
    }

    public static String getDurationString(long ms) {
        return DurationFormatUtils.formatDuration(ms, "dd' days, 'HH' hours, 'mm' minutes, 'ss' seconds.'", true);
    }

    public static Duration getDurationFromMS(long ms) {
        return Duration.ofMillis(ms);
    }

    public static String getShortDurationString(long ms) {
        return DurationFormatUtils.formatDuration(ms, "dd' days' - HH:mm:ss", true);
    }

    public static String getHoursDurationString(long ms) {
        return DurationFormatUtils.formatDuration(ms, "HH:mm:ss", true);
    }

    public static String getShortDurationString(Duration duration) {
        return getShortDurationString(duration.toMillis());
    }

    public static String getZDTString(ZonedDateTime zdt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss Z");
        String formattedString = zdt.format(formatter);
        return formattedString;
    }

    public static String getZDTShortString(ZonedDateTime zdt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm");
        String formattedString = zdt.format(formatter);
        return formattedString;
    }

    public static long dateTimeDifference(Temporal d1, Temporal d2, ChronoUnit unit){
        return unit.between(d1, d2);
    }

    public static long dateTimeDifference(ZonedDateTime d1, ZonedDateTime d2){
        return (d1.toInstant().toEpochMilli() - d2.toInstant().toEpochMilli()) + d1.toInstant().toEpochMilli();
    }

    public static long getMSUntilDate(ZonedDateTime date) {
        return date.toInstant().toEpochMilli();
    }

    public static String getDurationStringDays(Duration duration) {
        return DurationFormatUtils.formatDuration(duration.toMillis(), "dd 'Days'", true);
    }

    public static Duration getDurationBetween(long start, long end) {
        return Duration.between(ZonedDateTime.ofInstant(Instant.ofEpochMilli(start), ZoneId.of("UTC")),ZonedDateTime.ofInstant(Instant.ofEpochMilli(end), ZoneId.of("UTC")));
    }

    public static Duration getDurationBetween(ZonedDateTime start, ZonedDateTime end) {
        return Duration.between(start,end);
//        return Duration.between(ZonedDateTime.ofInstant(Instant.ofEpochMilli(start), ZoneId.of("UTC")),ZonedDateTime.ofInstant(Instant.ofEpochMilli(end), ZoneId.of("UTC")));
    }

    public static int getMsAsTicks(long ms) {
        return Math.toIntExact(ms / 600);
    }

}

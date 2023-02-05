package ethos.runehub;

import org.apache.commons.lang3.time.DurationFormatUtils;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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

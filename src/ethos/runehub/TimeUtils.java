package ethos.runehub;

import org.apache.commons.lang3.time.DurationFormatUtils;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

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

    public static String getShortDurationString(long ms) {
        return DurationFormatUtils.formatDuration(ms, "dd' days' - HH:mm:ss", true);
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

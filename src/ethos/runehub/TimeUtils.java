package ethos.runehub;

import org.apache.commons.lang3.time.DurationFormatUtils;

import java.time.Duration;

public class TimeUtils {

    public static long getDaysAsMS(int days) {
        return ((60000 * 60) * 24) * days;
    }

    public static String getDurationString(Duration duration) {
       return DurationFormatUtils.formatDuration(duration.toMillis(), "dd:HH:mm:ss", true);
    }

    public static String getDurationString(long ms) {
        return DurationFormatUtils.formatDuration(ms, "'Days: ' dd 'Hours: 'HH 'Minutes: ' mm 'Seconds: ' ss", true);
    }

}

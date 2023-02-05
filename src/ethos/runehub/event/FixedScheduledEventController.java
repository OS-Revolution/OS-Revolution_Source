package ethos.runehub.event;

import ethos.runehub.RunehubUtils;
import ethos.runehub.TimeUtils;
import org.runehub.api.util.math.MathUtils;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FixedScheduledEventController {

    private static FixedScheduledEventController instance = null;

    public static FixedScheduledEventController getInstance() {
        if (instance == null)
            instance = new FixedScheduledEventController();
        return instance;
    }

    public void startEvent(FixedScheduleEvent event) {
        final ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));
        ZonedDateTime nextRun = this.getNextCycle(event.getRate());
        final Duration initialDelay = Duration.between(now, nextRun);
        executorService.scheduleAtFixedRate(
                event,
                initialDelay.toMillis(),
                Duration.ofMillis(event.getRate()).toMillis(),
                TimeUnit.MILLISECONDS);
    }

    public void forceEvent(FixedScheduleEvent event) {
        executorService.execute(event);
    }

    public ZonedDateTime getNextCycle(FixedScheduleEvent event) {
        return this.getNextCycle(event.getRate());
    }
    public ZonedDateTime getFirstRun(FixedScheduleEvent event) {
        final ZoneId ZID = ZoneId.of("UTC");
        final ZonedDateTime now = ZonedDateTime.now(ZID);
        final ZonedDateTime start = now.withMonth(1).withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        System.out.println(event.getName() + " rate: " + event.getRate());
        final ZonedDateTime firstRun = start.plus(event.getRate(), ChronoUnit.MILLIS);
        return firstRun;
    }

    private ZonedDateTime getNextCycle(long msRate) {
        final ZoneId ZID = ZoneId.of("UTC");
        final ZonedDateTime now = ZonedDateTime.now(ZID);
        final ZonedDateTime start = now.withMonth(1).withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        final ZonedDateTime firstRun = start.plus(msRate, ChronoUnit.MILLIS);
        final long firstRunMS = now.toInstant().toEpochMilli() - firstRun.toInstant().toEpochMilli();
        final long quotient = firstRunMS / msRate;
        final ZonedDateTime previousRun = firstRun.plus(quotient * msRate, ChronoUnit.MILLIS);
        return firstRun.isAfter(now) ? firstRun : previousRun.plus(msRate,ChronoUnit.MILLIS);
    }

    private FixedScheduledEventController() {
        this.executorService = Executors.newScheduledThreadPool(1);
    }

    private final ScheduledExecutorService executorService;
    private final List<Future<?>> futures = new ArrayList<>();
}

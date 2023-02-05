package ethos.runehub.event.dnd;

import ethos.runehub.event.FixedScheduleEvent;
import ethos.runehub.skill.gathering.fishing.FishingPlatformController;
import org.runehub.api.util.math.geometry.Point;
import org.runehub.api.util.math.geometry.impl.Rectangle;

import java.time.Duration;

public class FishingPlatformUpdateFixedScheduleEvent extends FixedScheduleEvent {

    @Override
    public void execute() {

        FishingPlatformController.getInstance().moveFishingSpots();
    }

    public FishingPlatformUpdateFixedScheduleEvent() {
        super(Duration.ofSeconds(45).toMillis(),"Fishing Platform ");
//        ZonedDateTime start = ZonedDateTime.now(ZoneId.of("UTC")).withMonth(1).withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
//        ZonedDateTime end = start.plus(TimeUtils.getDaysAsMS(90),ChronoUnit.MILLIS);
//        System.out.println(TimeUtils.getDurationString(TimeUtils.getDurationBetween(end,start)));
    }
}

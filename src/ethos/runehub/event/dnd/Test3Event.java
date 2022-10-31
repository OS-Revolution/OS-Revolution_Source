package ethos.runehub.event.dnd;

import ethos.runehub.event.FixedScheduleEvent;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class Test3Event extends FixedScheduleEvent {

    @Override
    public void execute() {
        System.out.println("Executing test event");
    }

    public Test3Event() {
        super(Duration.of(1, ChronoUnit.DAYS).toMillis(),"b");
    }
}

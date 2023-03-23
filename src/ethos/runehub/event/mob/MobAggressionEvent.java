package ethos.runehub.event.mob;

import ethos.runehub.event.FixedScheduleEvent;

import java.time.Duration;

public class MobAggressionEvent extends FixedScheduleEvent {

    @Override
    public void execute() {
        System.out.println("Doing mob aggression tick");
    }

    public MobAggressionEvent() {
        super(600, "mob-aggression");
    }
}

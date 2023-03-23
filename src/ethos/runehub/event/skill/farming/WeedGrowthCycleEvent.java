package ethos.runehub.event.skill.farming;

import ethos.runehub.event.FixedScheduleEvent;
import ethos.runehub.skill.gathering.farming.FarmController;
import ethos.runehub.skill.gathering.farming.patch.PatchType;

import java.time.Duration;
import java.util.logging.Logger;

public class WeedGrowthCycleEvent extends FixedScheduleEvent {

    @Override
    public void execute() {
        Logger.getGlobal().info("Weeds growth cycle event");
        FarmController.getInstance().growWeeds();
    }

    public WeedGrowthCycleEvent() {
        super(Duration.ofMinutes(5).toMillis(), "weeds-growth-cycle");
    }
}

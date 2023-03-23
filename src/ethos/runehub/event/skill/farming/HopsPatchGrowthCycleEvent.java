package ethos.runehub.event.skill.farming;

import ethos.runehub.event.FixedScheduleEvent;
import ethos.runehub.skill.gathering.farming.FarmController;
import ethos.runehub.skill.gathering.farming.patch.PatchType;

import java.time.Duration;
import java.util.logging.Logger;

public class HopsPatchGrowthCycleEvent extends FixedScheduleEvent {

    @Override
    public void execute() {
        Logger.getGlobal().info("hops patch growth cycle event");
        FarmController.getInstance().grow(PatchType.HOPS);
    }

    public HopsPatchGrowthCycleEvent() {
        super(Duration.ofMinutes(10).toMillis(), "hops-patch-growth-cycle");
    }
}

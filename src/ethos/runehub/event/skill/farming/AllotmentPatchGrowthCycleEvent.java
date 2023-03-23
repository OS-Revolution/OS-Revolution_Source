package ethos.runehub.event.skill.farming;

import ethos.runehub.event.FixedScheduleEvent;
import ethos.runehub.skill.gathering.farming.FarmController;
import ethos.runehub.skill.gathering.farming.patch.PatchType;

import java.time.Duration;
import java.util.logging.Logger;

public class AllotmentPatchGrowthCycleEvent extends FixedScheduleEvent {

    @Override
    public void execute() {
        Logger.getGlobal().info("Allotment growth cycle event");
        FarmController.getInstance().grow(PatchType.ALLOTMENT);
    }

    public AllotmentPatchGrowthCycleEvent() {
        super(Duration.ofMinutes(10).toMillis(), "allotment-patch-growth-cycle");
    }
}

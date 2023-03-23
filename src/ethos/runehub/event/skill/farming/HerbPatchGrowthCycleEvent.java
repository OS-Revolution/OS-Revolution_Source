package ethos.runehub.event.skill.farming;

import ethos.runehub.event.FixedScheduleEvent;
import ethos.runehub.skill.gathering.farming.FarmController;
import ethos.runehub.skill.gathering.farming.patch.PatchType;

import java.time.Duration;
import java.util.logging.Logger;

public class HerbPatchGrowthCycleEvent extends FixedScheduleEvent {

    @Override
    public void execute() {
        Logger.getGlobal().info("Herb growth cycle event");
        FarmController.getInstance().grow(PatchType.HERB);
    }

    public HerbPatchGrowthCycleEvent() {
        super(Duration.ofMinutes(20).toMillis(), "herb-patch-growth-cycle");
    }
}

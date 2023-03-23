package ethos.runehub.event.skill.farming;

import ethos.runehub.event.FixedScheduleEvent;
import ethos.runehub.skill.gathering.farming.FarmController;
import ethos.runehub.skill.gathering.farming.patch.PatchType;

import java.time.Duration;
import java.util.logging.Logger;

public class FlowerPatchGrowthCycleEvent extends FixedScheduleEvent {

    @Override
    public void execute() {
        Logger.getGlobal().info("Flower patch growth cycle event");
        FarmController.getInstance().grow(PatchType.FLOWER);
    }

    public FlowerPatchGrowthCycleEvent() {
        super(Duration.ofMinutes(5).toMillis(), "flower-patch-growth-cycle");
    }
}

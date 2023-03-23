package ethos.runehub.event.skill.farming;

import ethos.runehub.event.FixedScheduleEvent;
import ethos.runehub.skill.gathering.farming.FarmController;
import ethos.runehub.skill.gathering.farming.patch.PatchType;

import java.time.Duration;
import java.util.logging.Logger;

public class FruitTreePatchGrowthCycleEvent extends FixedScheduleEvent {

    @Override
    public void execute() {
        Logger.getGlobal().info("Fruit tree growth cycle event");
        FarmController.getInstance().grow(PatchType.ALLOTMENT);
    }

    public FruitTreePatchGrowthCycleEvent() {
        super(Duration.ofMinutes(160).toMillis(), "fruittree-growth-cycle");
    }
}

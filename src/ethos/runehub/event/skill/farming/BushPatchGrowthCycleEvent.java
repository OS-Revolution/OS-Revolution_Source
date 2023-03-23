package ethos.runehub.event.skill.farming;

import ethos.runehub.event.FixedScheduleEvent;
import ethos.runehub.skill.gathering.farming.FarmController;
import ethos.runehub.skill.gathering.farming.patch.PatchType;

import java.time.Duration;
import java.util.logging.Logger;

public class BushPatchGrowthCycleEvent extends FixedScheduleEvent {

    @Override
    public void execute() {
        Logger.getGlobal().info("Bush growth cycle event");
        FarmController.getInstance().grow(PatchType.BUSH);
    }

    public BushPatchGrowthCycleEvent() {
        super(Duration.ofMinutes(20).toMillis(), "bush-patch-growth-cycle");
    }
}

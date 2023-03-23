package ethos.runehub.event.skill.farming;

import ethos.runehub.event.FixedScheduleEvent;
import ethos.runehub.skill.gathering.farming.FarmController;
import ethos.runehub.skill.gathering.farming.patch.PatchType;

import java.time.Duration;
import java.util.logging.Logger;

public class TreePatchGrowthCycleEvent extends FixedScheduleEvent {

    @Override
    public void execute() {
        Logger.getGlobal().info("Tree growth cycle event");
        FarmController.getInstance().grow(PatchType.TREE);
    }

    public TreePatchGrowthCycleEvent() {
        super(Duration.ofMinutes(40).toMillis(), "tree-patch-growth-cycle");
    }
}

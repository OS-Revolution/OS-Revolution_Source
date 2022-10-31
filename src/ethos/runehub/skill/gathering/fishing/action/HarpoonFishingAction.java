package ethos.runehub.skill.gathering.fishing.action;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import ethos.runehub.skill.gathering.tool.GatheringTool;
import ethos.runehub.skill.node.context.impl.GatheringNodeContext;

public class HarpoonFishingAction extends FishingAction {

    @Override
    protected void onEvent() {

    }

    @Override
    protected void validateItemRequirements() {
        Preconditions.checkArgument(this.getGetBestAvailableTool() != null, "You need a harpoon to catch these fish.");
    }

    public HarpoonFishingAction(Player player, GatheringNodeContext<?> targetedNodeContext) {
        super(player, targetedNodeContext, 4);
    }
}

package ethos.runehub.skill.gathering.fishing.action;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import ethos.runehub.skill.gathering.tool.GatheringTool;
import ethos.runehub.skill.node.context.impl.GatheringNodeContext;

public class CageFishingAction extends FishingAction {


    @Override
    protected void onEvent() {

    }

    @Override
    protected void validateItemRequirements() {
        Preconditions.checkArgument(this.getGetBestAvailableTool() != null, "You need a cage to catch these fish.");
    }

    public CageFishingAction(Player player, GatheringNodeContext<?> targetedNodeContext) {
        super(player, targetedNodeContext, 4);
    }
}

package ethos.runehub.skill.gathering.fishing.action;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import ethos.runehub.skill.gathering.tool.GatheringTool;
import ethos.runehub.skill.node.context.impl.GatheringNodeContext;

public class LureFishingAction extends FishingAction {


    @Override
    protected void onEvent() {

    }

    @Override
    protected void validateItemRequirements() {
        Preconditions.checkArgument(this.getGetBestAvailableTool() != null, "You need a lure to catch these fish.");
        if (this.getTargetedNodeContext().getNodeId() == 2) {
            Preconditions.checkArgument(this.getActor().getItems().playerHasItem(313), "You need bait to catch these fish.");
        } else if (this.getTargetedNodeContext().getNodeId() == 3) {
            Preconditions.checkArgument(this.getActor().getItems().playerHasItem(314), "You need bait to catch these fish.");
        }else if (this.getTargetedNodeContext().getNodeId() == 4) {
            Preconditions.checkArgument(this.getActor().getItems().playerHasItem(313), "You need bait to catch these fish.");
        }
    }

    public LureFishingAction(Player player, GatheringNodeContext<?> targetedNodeContext) {
        super(player, targetedNodeContext, 4);
    }
}

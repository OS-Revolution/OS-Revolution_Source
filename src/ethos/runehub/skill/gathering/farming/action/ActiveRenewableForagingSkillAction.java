package ethos.runehub.skill.gathering.farming.action;

import ethos.model.players.Player;
import ethos.runehub.skill.gathering.GatheringSkillAction;
import ethos.runehub.skill.gathering.tool.GatheringTool;
import ethos.runehub.skill.node.context.impl.ForagingNodeContext;
import ethos.runehub.skill.node.io.ForagingSeedLoader;

public class ActiveRenewableForagingSkillAction extends GatheringSkillAction {

    @Override
    protected void onEvent() {
        final int nodeId = this.getTargetedNodeContext().getNodeId();
        final int seedId = ForagingSeedLoader.getInstance().read(nodeId).getSeedId();
        this.getActor().sendMessage("You found a seed whilst foraging!");
        this.getActor().getItems().addItem(seedId,1);
    }

    @Override
    protected GatheringTool getGetBestAvailableTool() throws NullPointerException {
        return this.getActor().getSkillController().getForaging().getBestAvailableTool();
    }

    @Override
    protected void updateAnimation() {
        if (super.getElapsedTicks() == 4 || super.getElapsedTicks() % 4 == 0) {
            this.getActor().startAnimation(this.getActor().getSkillController().getForaging().getBestAvailableTool().getAnimationId());
        }
    }

    public ActiveRenewableForagingSkillAction(Player player, int nodeId, int nodeX, int nodeY, int nodeZ) {
        super(player, 19, new ForagingNodeContext(nodeId,nodeX,nodeY,nodeZ), 4);
    }
}

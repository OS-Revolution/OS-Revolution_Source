package ethos.runehub.skill.gathering.farming.action;

import ethos.Server;
import ethos.model.players.Player;
import ethos.runehub.skill.gathering.GatheringSkillAction;
import ethos.runehub.skill.gathering.tool.GatheringTool;
import ethos.runehub.skill.node.context.impl.ForagingNodeContext;
import ethos.runehub.skill.node.io.ForagingSeedLoader;
import ethos.world.objects.GlobalObject;

public class ActiveForagingSkillAction extends GatheringSkillAction {

    @Override
    protected void onRespawn() {
        Server.getGlobalObjects().add(
                new GlobalObject(
                        this.getRenewableNode().getDepletedNodeId(),
                        targetedNodeContext.getX(),
                        targetedNodeContext.getY(),
                        targetedNodeContext.getZ())
        );
    }

    @Override
    protected GatheringTool getGetBestAvailableTool() throws NullPointerException {
        return this.getActor().getSkillController().getForaging().getBestAvailableTool();
    }

    @Override
    protected void onEvent() {
        final int nodeId = this.getTargetedNodeContext().getNodeId();
        final int seedId = ForagingSeedLoader.getInstance().read(nodeId).getSeedId();
        this.getActor().sendMessage("You found a seed whilst foraging!");
        this.getActor().getItems().addItem(seedId,1);
    }

    @Override
    protected void updateAnimation() {
        if (super.getElapsedTicks() == 4 || super.getElapsedTicks() % 4 == 0) {
            this.getActor().startAnimation(this.getActor().getSkillController().getForaging().getBestAvailableTool().getAnimationId());
        }
    }

    public ActiveForagingSkillAction(Player player, int nodeId, int nodeX, int nodeY, int nodeZ) {
        super(player, 19, new ForagingNodeContext(nodeId,nodeX,nodeY,nodeZ), 4);
    }
}

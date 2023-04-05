package ethos.runehub.skill.gathering.farming.action;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.content.journey.JourneyStepType;
import ethos.runehub.skill.gathering.GatheringSkillAction;
import ethos.runehub.skill.gathering.farming.FarmingConfig;
import ethos.runehub.skill.gathering.tool.GatheringTool;
import ethos.runehub.skill.node.context.impl.GatheringNodeContext;
import ethos.runehub.skill.node.impl.gatherable.GatheringNode;
import ethos.util.PreconditionUtils;
import org.runehub.api.util.SkillDictionary;

public class RakePatchAction extends GatheringSkillAction {

    @Override
    protected void updateAnimation() {
//        this.getActor().startAnimation(2273);
    }

    @Override
    protected void onEvent() {

    }

    @Override
    protected void validateItemRequirements() {
        Preconditions.checkArgument(this.getActor().getItems().playerHasItem(5341), "You do not have a valid tool.");
    }

    @Override
    protected void onGather() {
        this.addItems(6055, 1);
        this.getActor().getPA().addSkillXP(4, this.getSkillId(), true);
//        this.getActor().getAttributes().getJourneyController().checkJourney(5310519480822153480L,1);
        this.getActor().getAttributes().getJourneyController().checkJourney(6055,1, JourneyStepType.COLLECTION);
        cycle++;
        config.incrementCycle();
        this.getActor().getSkillController().getFarming().updateFarm(RunehubUtils.getRegionId(this.getTargetedNodeContext().getX(), this.getTargetedNodeContext().getY()));
    }

    @Override
    protected GatheringTool getGetBestAvailableTool() throws NullPointerException {
        return new GatheringTool(
                5341,
                1,
                SkillDictionary.Skill.FARMING.getId(),
                1.0,
                0,
                0f,
                2273
        );
    }

    @Override
    public void onTick() {
        if (config.getStage() < 2) {
            this.getActor().startAnimation(2273);
        }
        if (config.getStage() < 3) {
            this.onGather();
        } else {
            this.getActor().startAnimation(65535);
            this.stop();
        }
    }

    @Override
    protected void validateLevelRequirements() {
        Preconditions.checkArgument(PreconditionUtils.isTrue(this.getActor().getSkillController().getLevel(this.getSkillId()) >= 1),
                "You need a ?"
                        + SkillDictionary.getSkillNameFromId(this.getSkillId())
                        + " level of at least #"
                        + 1
                        + " to do this.");

    }

    @Override
    protected void validateWorldRequirements() {

//        Preconditions.checkArgument(PreconditionUtils.isFalse(Server.getGlobalObjects().exists(this.getRenewableNode().getDepletedNodeId(), targetedNodeContext.getX(), targetedNodeContext.getY())),
//                "This node is depleted.");
    }

    public RakePatchAction(Player player, FarmingConfig config, int nodeId, int nodeX, int nodeY) {
        super(player, SkillDictionary.Skill.FARMING.getId(), new GatheringNodeContext<>(nodeId, nodeX, nodeY, 0) {
            @Override
            public GatheringNode getNode() {
                return new GatheringNode(nodeId, 1, 4, -1L, 1000, SkillDictionary.Skill.FARMING.getId(), 1000);
            }
        }, 4);
        this.config = config;
    }

    private final FarmingConfig config;
    int cycle = 0;
}

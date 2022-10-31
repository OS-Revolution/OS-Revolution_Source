package ethos.runehub.skill.gathering.farming.action;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.entity.item.ItemInteractionContext;
import ethos.runehub.skill.gathering.GatheringSkillAction;
import ethos.runehub.skill.gathering.farming.FarmingConfig;
import ethos.runehub.skill.gathering.farming.crop.CropCache;
import ethos.runehub.skill.gathering.farming.crop.CropDAO;
import ethos.runehub.skill.gathering.tool.GatheringTool;
import ethos.runehub.skill.node.context.impl.GatheringNodeContext;
import ethos.runehub.skill.node.impl.gatherable.GatheringNode;
import ethos.util.PreconditionUtils;
import org.runehub.api.util.SkillDictionary;

public class ClearPatchAction extends GatheringSkillAction {


    @Override
    protected void updateAnimation() {
        this.getActor().startAnimation(830);
    }

    @Override
    protected void onEvent() {

    }

    @Override
    protected void validateItemRequirements() {
        Preconditions.checkArgument(this.getActor().getItems().playerHasItem(952), "You need a spade.");
    }

    @Override
    protected void onGather() {
        config.setCrop(0);
        config.setStage(3);
        config.setDiseased(false);
        config.setWatered(false);
        config.setCompost(0);
        this.getActor().getSkillController().getFarming().updateFarm(RunehubUtils.getRegionId(targetedNodeContext.getX(),targetedNodeContext.getY()));
    }

    @Override
    protected GatheringTool getGetBestAvailableTool() throws NullPointerException {
        return new GatheringTool(
                952,
                1,
                SkillDictionary.Skill.FARMING.getId(),
                1.0,
                0,
                0f,
                830
        );
    }

    @Override
    public void onTick() {
        if (cycle <= 4) {
            this.updateAnimation();
            cycle++;
        } else {
            this.onGather();
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
    }

    public ClearPatchAction(Player player, FarmingConfig config, int nodeId, int nodeX, int nodeY) {
        super(player, SkillDictionary.Skill.FARMING.getId(), new GatheringNodeContext<>(nodeId, nodeX, nodeY, 0) {
            @Override
            public GatheringNode getNode() {
                return new GatheringNode(nodeId, 1, 4, -1L, 1000, SkillDictionary.Skill.FARMING.getId(), 1000);
            }
        }, 2);
        this.config = config;
    }

    private int cycle;
    private final FarmingConfig config;
}

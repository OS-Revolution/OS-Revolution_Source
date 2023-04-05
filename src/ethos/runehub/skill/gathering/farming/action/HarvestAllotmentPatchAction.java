package ethos.runehub.skill.gathering.farming.action;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.skill.gathering.GatheringSkillAction;
import ethos.runehub.skill.gathering.farming.FarmingConfig;
import ethos.runehub.skill.gathering.farming.crop.CropCache;
import ethos.runehub.skill.gathering.farming.crop.CropDAO;
import ethos.runehub.skill.gathering.tool.GatheringTool;
import ethos.runehub.skill.node.context.impl.GatheringNodeContext;
import ethos.runehub.skill.node.impl.gatherable.GatheringNode;
import ethos.util.PreconditionUtils;
import org.runehub.api.util.SkillDictionary;

public class HarvestAllotmentPatchAction extends GatheringSkillAction {

    @Override
    protected void updateAnimation() {
        this.getActor().startAnimation(830);
    }

    @Override
    protected void onEvent() {

    }

    @Override
    protected void validateItemRequirements() {
        Preconditions.checkArgument(this.getActor().getItems().freeSlots() > 0, "You need at least $1 free inventory space.");
        Preconditions.checkArgument(this.getActor().getItems().playerHasItem(952), "You need a @952");
    }

    @Override
    protected void onGather() {
        this.getActor().getItems().addItem(CropDAO.getInstance().read(config.getCrop()).getCropId(), 1);
        this.getActor().sendMessage("You harvest a @" + CropDAO.getInstance().read(config.getCrop()).getCropId());
        this.getActor().getPA().addSkillXP(CropDAO.getInstance().read(config.getCrop()).getHarvestXp(), this.getSkillId(), true);
        this.updateAchievementDiaries();
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
        if (cycle < this.getMinHarvest()) {
            this.updateAnimation();
            this.onGather();
            this.getActor().getSkillController().getFarming().updateHarvestCounts(this.getTargetedNodeContext(),config);
            cycle++;
        } else if (cycle >= this.getMinHarvest()
                && cycle < this.getMaxHarvest()
                && isSuccessful(this.getMin(), this.getMax())) {
            this.updateAnimation();
            this.onGather();
            this.getActor().getSkillController().getFarming().updateHarvestCounts(this.getTargetedNodeContext(),config);
            cycle++;
        } else {
            config.setCrop(0);
            config.setStage(3);
            config.setDiseased(false);
            config.setWatered(false);
            config.setCompost(0);
            this.getActor().getSkillController().getFarming().updateFarm(RunehubUtils.getRegionId(targetedNodeContext.getX(), targetedNodeContext.getY()));
            this.getActor().startAnimation(65535);
            this.getActor().getSkillController().getFarming().resetHarvestCounts(this.getTargetedNodeContext(),config);
            this.stop();
        }
    }

    @Override
    protected void validateLevelRequirements() {
        Preconditions.checkArgument(PreconditionUtils.isTrue(this.getActor().getSkillController().getLevel(this.getSkillId()) >= CropDAO.getInstance().read(config.getCrop()).getLevelRequirement()),
                "You need a ?"
                        + SkillDictionary.getSkillNameFromId(this.getSkillId())
                        + " level of at least #"
                        + CropDAO.getInstance().read(config.getCrop()).getLevelRequirement()
                        + " to do this.");

    }

    @Override
    protected void validateWorldRequirements() {
        Preconditions.checkArgument(config.getCrop() > 0, "The patch is empty.");
    }

    private void updateAchievementDiaries() {
        if (config.getCrop() == 5318) {
            this.getActor().getAttributes().getAchievementController().completeAchievement(1483892139077748908L);
        }
    }

    private int getMinHarvest() {
        return (int) (CropCache.getInstance().read(config.getCrop()).getBaseHarvestAmount() * this.getActor().getSkillController().getFarming().getHarvestMinMaxBonus(config));
    }

    private int getMaxHarvest() {
        return (int) (30 * this.getActor().getSkillController().getFarming().getHarvestMinMaxBonus(config));
    }

    private int getMin() {
        return (int) (60 * this.getActor().getSkillController().getFarming().getHarvestChanceBonus());
    }

    private int getMax() {
        return (int) (200 * this.getActor().getSkillController().getFarming().getHarvestChanceBonus());
    }

    public HarvestAllotmentPatchAction(Player player, FarmingConfig config, int nodeId, int nodeX, int nodeY) {
        super(player, SkillDictionary.Skill.FARMING.getId(), new GatheringNodeContext<>(nodeId, nodeX, nodeY, 0) {
            @Override
            public GatheringNode getNode() {
                return new GatheringNode(nodeId, 1, 4, -1L, 1000, SkillDictionary.Skill.FARMING.getId(), 1000);
            }
        }, 3);
        this.config = config;
        this.cycle = this.getActor().getSkillController().getFarming().getHarvestedAmount(this.getTargetedNodeContext(),config);
    }

    private int cycle;
    private final FarmingConfig config;
}

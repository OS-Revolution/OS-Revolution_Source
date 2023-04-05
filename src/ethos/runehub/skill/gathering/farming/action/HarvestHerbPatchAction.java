package ethos.runehub.skill.gathering.farming.action;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.skill.gathering.GatheringSkillAction;
import ethos.runehub.skill.gathering.farming.FarmingConfig;
import ethos.runehub.skill.gathering.farming.crop.CropCache;
import ethos.runehub.skill.gathering.farming.crop.CropDAO;
import ethos.runehub.skill.gathering.tool.GatheringTool;
import ethos.runehub.skill.gathering.tool.GatheringToolLoader;
import ethos.runehub.skill.node.context.impl.GatheringNodeContext;
import ethos.runehub.skill.node.impl.gatherable.GatheringNode;
import ethos.util.PreconditionUtils;
import org.runehub.api.model.math.impl.AdjustableInteger;
import org.runehub.api.util.SkillDictionary;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class HarvestHerbPatchAction extends GatheringSkillAction {

    @Override
    protected void updateAnimation() {
//        this.getActor().startAnimation(2282);
    }

    @Override
    protected void onEvent() {

    }

    @Override
    protected void validateItemRequirements() {
        Preconditions.checkArgument(this.getActor().getItems().freeSlots() > 0, "You need at least $1 free inventory space.");
        Preconditions.checkArgument(this.hasSecateurs(), "You need secateurs to harvest this");
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
        final List<GatheringTool> availableTools = GatheringToolLoader.getInstance().readAll().stream().filter(tool -> tool.getSkillId() == this.getSkillId()).filter(tool -> this.getActor().getSkillController().getLevel(this.getSkillId()) >= tool.getLevelRequired()).collect(Collectors.toList());
        final int bestToolLevel = availableTools.stream().mapToInt(GatheringTool::getLevelRequired).max().orElse(1);

        return availableTools.stream().filter(tool -> tool.getLevelRequired() == bestToolLevel).findFirst().orElse(null);
    }

    @Override
    public void onTick() {
        if (cycle < this.getMinHarvest()) {
            this.getActor().getSkillController().getFarming().updateHarvestCounts(this.getTargetedNodeContext(),config);
            this.getActor().startAnimation(this.getGetBestAvailableTool().getAnimationId());
            this.onGather();
            cycle++;
        } else if (cycle >= this.getMinHarvest()
                && cycle < this.getMaxHarvest()
                && isSuccessful(this.getMin(), this.getMax())) {
            this.getActor().startAnimation(this.getGetBestAvailableTool().getAnimationId());
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
        if (config.getCrop() == 5292) {
            this.getActor().getAttributes().getAchievementController().completeAchievement(-4856231565739537844L);
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

    private boolean hasSecateurs() {
        return (this.getActor().getItems().playerHasItem(5329))
                || (this.getActor().getItems().playerHasItem(7409) || this.getActor().getItems().isWearingItem(7409))
                || (this.getActor().getItems().playerHasItem(7411) || this.getActor().getItems().isWearingItem(7411));
    }

    public HarvestHerbPatchAction(Player player, FarmingConfig config, int nodeId, int nodeX, int nodeY) {
        super(player, SkillDictionary.Skill.FARMING.getId(), new GatheringNodeContext<>(nodeId, nodeX, nodeY, 0) {
            @Override
            public GatheringNode getNode() {
                return new GatheringNode(nodeId, 1, 4, -1L, 1000, SkillDictionary.Skill.FARMING.getId(), 1000);
            }
        }, 3);
        this.config = config;
        this.cycle = player.getSkillController().getFarming().getHarvestedAmount(getTargetedNodeContext(),config);
    }

    private int cycle;
    private final FarmingConfig config;
}

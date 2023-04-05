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

public class PlantSeedAction extends GatheringSkillAction {


    @Override
    protected void updateAnimation() {
//        this.getActor().startAnimation(2291);
    }

    @Override
    protected void onEvent() {

    }

    @Override
    protected void validateItemRequirements() {
        Preconditions.checkArgument(CropDAO.getInstance().getAllEntries().stream().anyMatch(crop -> crop.getId() == interactionContext.getUsedId()),"You can not plant this.");
        Preconditions.checkArgument(config.getType() == CropCache.getInstance().read(interactionContext.getUsedId()).getPatchTypeId(), "You can not plant this here." );
        Preconditions.checkArgument(this.getActor().getItems().playerHasItem(interactionContext.getUsedId(),CropCache.getInstance().read(interactionContext.getUsedId()).getSeedAmount()),"You need @"  + interactionContext.getUsedId() +  " x #" + CropCache.getInstance().read(interactionContext.getUsedId()).getSeedAmount() + ".");
        Preconditions.checkArgument(this.getActor().getItems().playerHasItem(5343), "You do not have a valid tool.");
    }

    @Override
    protected void onGather() {
        this.getActor().startAnimation(2291);
        this.getActor().getItems().deleteItem2(interactionContext.getUsedId(), CropCache.getInstance().read(interactionContext.getUsedId()).getSeedAmount());
        config.setCrop(interactionContext.getUsedId());
        config.setStage(0);
        this.getActor().getSkillController().getFarming().updateFarm(RunehubUtils.getRegionId(interactionContext.getX(),interactionContext.getY()));
        this.getActor().getPA().addSkillXP(CropCache.getInstance().read(interactionContext.getUsedId()).getPlantXp(),SkillDictionary.Skill.FARMING.getId(), true);
    }

    @Override
    protected GatheringTool getGetBestAvailableTool() throws NullPointerException {
        return new GatheringTool(
                5343,
                1,
                SkillDictionary.Skill.FARMING.getId(),
                1.0,
                0,
                0f,
                2291
        );
    }

    @Override
    public void onTick() {
        if (cycle <= CropCache.getInstance().read(interactionContext.getUsedId()).getSeedAmount()) {
            this.getActor().startAnimation(2291);
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
                        + CropCache.getInstance().read(interactionContext.getUsedId()).getLevelRequirement()
                        + " to do this.");

    }

    @Override
    protected void validateWorldRequirements() {
            Preconditions.checkArgument((config.getCrop() == 0 && config.getStage() == 3),"You must clear this patch before you can plant these.");
    }

    public PlantSeedAction(Player player, ItemInteractionContext interactionContext) {
        super(player, SkillDictionary.Skill.FARMING.getId(), new GatheringNodeContext<>(interactionContext.getUsedWithId(), interactionContext.getX(), interactionContext.getY(), 0) {
            @Override
            public GatheringNode getNode() {
                return new GatheringNode(interactionContext.getUsedWithId(), 1, 4, -1L, 1000, SkillDictionary.Skill.FARMING.getId(), 1000);
            }
        }, 2);
        this.interactionContext = interactionContext;
        this.config = player.getSkillController().getFarming().getConfig(interactionContext.getUsedWithId(), RunehubUtils.getRegionId(interactionContext.getX(), interactionContext.getY())).orElse(null);
    }

    private int cycle;
    private final ItemInteractionContext interactionContext;
    private final FarmingConfig config;
}

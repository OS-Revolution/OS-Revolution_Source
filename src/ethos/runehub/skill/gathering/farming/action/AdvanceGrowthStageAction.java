package ethos.runehub.skill.gathering.farming.action;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import ethos.runehub.RunehubConstants;
import ethos.runehub.RunehubUtils;
import ethos.runehub.dialog.DialogSequence;
import ethos.runehub.entity.item.ItemInteractionContext;
import ethos.runehub.skill.SkillAction;
import ethos.runehub.skill.gathering.farming.FarmController;
import ethos.runehub.skill.gathering.farming.FarmTick;
import ethos.runehub.skill.gathering.farming.Farming;
import ethos.runehub.skill.gathering.farming.FarmingConfig;
import ethos.runehub.skill.gathering.farming.crop.CropDAO;
import ethos.runehub.skill.gathering.farming.patch.PatchType;
import ethos.util.PreconditionUtils;
import org.runehub.api.util.SkillDictionary;

public class AdvanceGrowthStageAction extends SkillAction {



    @Override
    protected void updateAnimation() {}

    @Override
    protected void addItems(int id, int amount) {}


    @Override
    protected void validateItemRequirements() {
        Preconditions.checkArgument((config.getStage() < CropDAO.getInstance().read(config.getCrop()).getGrowthCycles()),"This patch is already at the maximum stage.");
    }

//    @Override
//    protected void onGather() {
//        this.getActor().getItems().deleteItem2(interactionContext.getUsedId(), CropCache.getInstance().read(interactionContext.getUsedId()).getSeedAmount());
//        config.setCrop(interactionContext.getUsedId());
//        config.setStage(0);
//        this.getActor().getSkillController().getFarming().updateFarm(RunehubUtils.getRegionId(interactionContext.getX(),interactionContext.getY()));
//        this.getActor().getPA().addSkillXP(CropCache.getInstance().read(interactionContext.getUsedId()).getPlantXp(),SkillDictionary.Skill.FARMING.getId(), true);
//    }


    @Override
    protected void onActionStart() {
        this.getActor().startAnimation(2291);
    }

    @Override
    protected void onActionStop() {}

    @Override
    public void onTick() {

        this.getActor().getItems().deleteItem(interactionContext.getUsedId(), RunehubConstants.ADVANCE_PATCH_GROWTH_COST);
        FarmController.getInstance().doGrowthCycleForOnlinePlayer(this.getActor(), PatchType.values()[config.getType()]);
        this.getActor().getAttributes().getFarmTickExecutorService().execute(new FarmTick(this.getActor()));
        this.stop();
    }

    @Override
    protected void onUpdate() {}

    @Override
    protected void validateInventory() {
        Preconditions.checkArgument(this.getActor().getItems().playerHasItem(interactionContext.getUsedId(),RunehubConstants.ADVANCE_PATCH_GROWTH_COST),
                "You need at least " + RunehubConstants.ADVANCE_PATCH_GROWTH_COST + " Jewels to advance a growth stage.");
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
//            Preconditions.checkArgument((config.getCrop() == 0 && config.getStage() == 3),"You must clear this patch before you can treat it with compost.");
    }

    public AdvanceGrowthStageAction(Player player, ItemInteractionContext interactionContext) {
        super(player,SkillDictionary.Skill.FARMING.getId(),6);
        this.interactionContext = interactionContext;
        this.config = player.getSkillController().getFarming().getConfig(interactionContext.getUsedWithId(), RunehubUtils.getRegionId(interactionContext.getX(), interactionContext.getY())).orElse(null);
    }

    private final ItemInteractionContext interactionContext;
    private final FarmingConfig config;
}

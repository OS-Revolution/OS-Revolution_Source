package ethos.runehub.skill.gathering.farming.action;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.entity.item.ItemInteractionContext;
import ethos.runehub.skill.SkillAction;
import ethos.runehub.skill.gathering.GatheringSkillAction;
import ethos.runehub.skill.gathering.farming.Farming;
import ethos.runehub.skill.gathering.farming.FarmingConfig;
import ethos.runehub.skill.gathering.farming.crop.CropCache;
import ethos.runehub.skill.gathering.tool.GatheringTool;
import ethos.runehub.skill.node.context.impl.GatheringNodeContext;
import ethos.runehub.skill.node.impl.gatherable.GatheringNode;
import ethos.util.PreconditionUtils;
import org.runehub.api.util.SkillDictionary;

public class ApplyCompostAction extends SkillAction {


    @Override
    protected void updateAnimation() {}

    @Override
    protected void addItems(int id, int amount) {}


    @Override
    protected void validateItemRequirements() {
        Preconditions.checkArgument((config.getCrop() == 0 && config.getStage() == 3),"You must clear this patch before you can treat it with compost.");
        Preconditions.checkArgument(config.getCompost() == 0,"This patch has already been treated with compost.");
        if (interactionContext.getUsedId() == Farming.BOTTOMLESS_COMPOST) {
            Preconditions.checkArgument(this.getActor().getContext().getPlayerSaveData().getBottomlessCompostBucketCharges() > 0, "You've run out of compost.");
        }
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
        this.getActor().startAnimation(2283);
    }

    @Override
    protected void onActionStop() {}

    @Override
    public void onTick() {
        if (interactionContext.getUsedId() == Farming.COMPOST) {
            this.getActor().getPA().addSkillXP(18,SkillDictionary.Skill.FARMING.getId(), true);
            this.getActor().getItems().deleteItem(interactionContext.getUsedId(),1);
            this.getActor().getItems().addOrDropItem(1925,1);
            config.setCompost(interactionContext.getUsedId());
        } else if(interactionContext.getUsedId() == Farming.SUPERCOMPOST) {
            this.getActor().getPA().addSkillXP(26,SkillDictionary.Skill.FARMING.getId(), true);
            this.getActor().getItems().deleteItem(interactionContext.getUsedId(),1);
            this.getActor().getItems().addOrDropItem(1925,1);
            config.setCompost(interactionContext.getUsedId());
        } else if(interactionContext.getUsedId() == Farming.ULTRACOMPOST) {
            this.getActor().getPA().addSkillXP(36,SkillDictionary.Skill.FARMING.getId(), true);
            this.getActor().getItems().deleteItem(interactionContext.getUsedId(),1);
            this.getActor().getItems().addOrDropItem(1925,1);
            config.setCompost(interactionContext.getUsedId());
        } else if(interactionContext.getUsedId() == Farming.BOTTOMLESS_COMPOST) {
            this.getActor().getPA().addSkillXP(18,SkillDictionary.Skill.FARMING.getId(), true);
            this.getActor().getContext().getPlayerSaveData().setBottomlessCompostBucketCharges(this.getActor().getContext().getPlayerSaveData().getBottomlessCompostBucketCharges() - 1);
            config.setCompost(this.getActor().getContext().getPlayerSaveData().getBottomlessCompostBucketType());
        }
        this.stop();
    }

    @Override
    protected void onUpdate() {}

    @Override
    protected void validateInventory() {
        Preconditions.checkArgument(this.getActor().getItems().playerHasItem(interactionContext.getUsedId()));
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

    public ApplyCompostAction(Player player, ItemInteractionContext interactionContext) {
        super(player,SkillDictionary.Skill.FARMING.getId(),6);
        this.interactionContext = interactionContext;
        this.config = player.getSkillController().getFarming().getConfig(interactionContext.getUsedWithId(), RunehubUtils.getRegionId(interactionContext.getX(), interactionContext.getY())).orElse(null);
    }

    private final ItemInteractionContext interactionContext;
    private final FarmingConfig config;
}

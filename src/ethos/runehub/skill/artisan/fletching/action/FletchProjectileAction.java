package ethos.runehub.skill.artisan.fletching.action;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.skill.SkillAction;
import ethos.runehub.skill.artisan.fletching.Fletchable;
import ethos.runehub.skill.support.slayer.SlayerKnowledgeRewardCache;
import org.runehub.api.util.SkillDictionary;

import java.util.Arrays;

public class FletchProjectileAction extends SkillAction {

    @Override
    protected void onActionStart() {
//        this.getActor().startAnimation(fletchable.getAnimationId());
    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {
        if (amount > 0) {
//            this.getActor().startAnimation(fletchable.getAnimationId());
            this.deleteMaterials();
            this.getActor().getSkillController().addXP(this.getSkillId(),fletchable.getBaseXp() * fletchable.getAmountProduced());
            this.getActor().getItems().addItem(fletchable.getProductId(),fletchable.getAmountProduced());
            amount--;
        } else {
            this.stop();
        }
    }

    @Override
    protected void onUpdate() {

    }

    @Override
    protected void validateInventory() {

    }

    @Override
    protected void validateLevelRequirements() {
        Preconditions.checkArgument(this.getActor().getSkillController().getLevel(this.getSkillId()) >= fletchable.getLevelRequirement(), "You need a $"
                + RunehubUtils.getSkillName(this.getSkillId()) + " level of $"
                + fletchable.getLevelRequirement() + " to do this.");
    }

    @Override
    protected void validateItemRequirements() {
        Preconditions.checkArgument(Arrays.stream(fletchable.getMaterials()).allMatch(gameItem -> this.getActor().getItems().playerHasItem(gameItem.getId(), gameItem.getAmount())), "You do not have all the required materials.");
        Preconditions.checkArgument(fletchable.getProductId() == 11875 && this.getActor().getSlayerSave().getUnlockedKnowledge()[0], "You must unlock the Slayer knowledge " + SlayerKnowledgeRewardCache.getInstance().read(0).getName());
        Preconditions.checkArgument(fletchable.getProductId() == 4160 && this.getActor().getSlayerSave().getUnlockedKnowledge()[0], "You must unlock the Slayer knowledge " + SlayerKnowledgeRewardCache.getInstance().read(0).getName());
    }

    @Override
    protected void validateWorldRequirements() {

    }

    @Override
    protected void updateAnimation() {

    }

    @Override
    protected void addItems(int id, int amount) {

    }

    private void deleteMaterials() {
        Arrays.stream(fletchable.getMaterials()).forEach(gameItem -> {
            this.getActor().getItems().deleteItem2(gameItem.getId(), gameItem.getAmount());
        });
    }

    public FletchProjectileAction(Player actor, Fletchable fletchable, int amount) {
        super(actor, SkillDictionary.Skill.FLETCHING.getId(), 2);
        this.fletchable = fletchable;
        this.amount = amount;
    }

    private int amount;
    private final Fletchable fletchable;
}

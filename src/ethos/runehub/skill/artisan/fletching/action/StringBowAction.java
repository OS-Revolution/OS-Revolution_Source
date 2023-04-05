package ethos.runehub.skill.artisan.fletching.action;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.skill.SkillAction;
import ethos.runehub.skill.artisan.fletching.Fletchable;
import org.runehub.api.util.SkillDictionary;

import java.util.Arrays;

public class StringBowAction extends SkillAction {

    @Override
    protected void onActionStart() {
        this.getActor().startAnimation(fletchable.getAnimationId());
    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {
        if (amount > 0) {
            this.getActor().startAnimation(fletchable.getAnimationId());
            this.deleteMaterials();
            this.getActor().getSkillController().addXP(this.getSkillId(),fletchable.getBaseXp());
            this.getActor().getItems().addItem(fletchable.getProductId(),fletchable.getAmountProduced());
            this.updateAchievements();
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

    private void updateAchievements() {
        if (fletchable.getProductId() == 843) {
            this.getActor().getAttributes().getAchievementController().completeAchievement(6109194337526825863L);
        }
    }

    private void deleteMaterials() {
        Arrays.stream(fletchable.getMaterials()).forEach(gameItem -> {
            this.getActor().getItems().deleteItem2(gameItem.getId(), gameItem.getAmount());
        });
    }

    public StringBowAction(Player actor, Fletchable fletchable, int amount) {
        super(actor, SkillDictionary.Skill.FLETCHING.getId(), 2);
        this.fletchable = fletchable;
        this.amount = amount;
    }

    private int amount;
    private final Fletchable fletchable;
}

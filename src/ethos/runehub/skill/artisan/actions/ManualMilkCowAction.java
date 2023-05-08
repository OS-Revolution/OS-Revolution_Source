package ethos.runehub.skill.artisan.actions;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import ethos.runehub.skill.SkillAction;
import org.runehub.api.util.SkillDictionary;

public class ManualMilkCowAction extends SkillAction {

    @Override
    protected void validateWorldRequirements() {

    }

    @Override
    protected void updateAnimation() {

    }

    @Override
    protected void addItems(int id, int amount) {

    }

    @Override
    protected void onActionStart() {
        this.getActor().startAnimation(2305);
    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {
        this.getActor().getItems().deleteItem2(1925,1);
        this.getActor().getItems().addItem(1927,1);
        this.getActor().getSkillController().getFarming().train(new AutoMilkCowAction(this.getActor()));
        this.stop();
    }

    @Override
    protected void onUpdate() {

    }

    @Override
    protected void validateInventory() {

    }

    @Override
    protected void validateLevelRequirements() {

    }

    @Override
    protected void validateItemRequirements() {
        Preconditions.checkArgument(this.getActor().getItems().playerHasItem(1925, 1), "You need a bucket.");
    }


    public ManualMilkCowAction(Player actor) {
        super(actor, SkillDictionary.Skill.FARMING.getId(), 3);
    }
}

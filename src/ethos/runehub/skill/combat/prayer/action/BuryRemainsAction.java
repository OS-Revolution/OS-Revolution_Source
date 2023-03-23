package ethos.runehub.skill.combat.prayer.action;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import ethos.runehub.skill.SkillAction;
import ethos.runehub.skill.combat.prayer.remains.Remains;
import ethos.runehub.skill.combat.prayer.remains.RemainsCache;
import org.runehub.api.util.SkillDictionary;

import java.util.logging.Logger;

public class BuryRemainsAction extends SkillAction {

    @Override
    protected void onActionStart() {

    }

    @Override
    protected void onActionStop() {

    }

    @Override
    public void stop() {
        Logger.getGlobal().fine("Stopping Event");
        this.running = false;
        this.onActionStop();
    }

    @Override
    protected void onTick() {
        this.getActor().startAnimation(827);
        this.getActor().getItems().deleteItem(remains.getItemId(),1);
        this.getActor().getSkillController().addXP(this.getSkillId(),remains.getBaseXp());
        this.getActor().sendMessage("You bury the @" + remains.getItemId());
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
        Preconditions.checkArgument(this.getActor().getItems().playerHasItem(remains.getItemId(),1));
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

    public BuryRemainsAction(Player actor, int itemId) {
        super(actor, SkillDictionary.Skill.PRAYER.getId(), 1);
        this.remains = RemainsCache.getInstance().read(itemId);
    }

    private final Remains remains;
}

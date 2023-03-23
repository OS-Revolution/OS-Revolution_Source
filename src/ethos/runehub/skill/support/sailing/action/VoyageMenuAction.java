package ethos.runehub.skill.support.sailing.action;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import ethos.runehub.skill.support.SupportSkillAction;
import ethos.util.PreconditionUtils;

public abstract class VoyageMenuAction extends SupportSkillAction {

    protected abstract void validate();
    protected abstract void onAction();

    protected void checkPreconditions() {
        try {
            Preconditions.checkArgument(PreconditionUtils.notNull(this.getActor()), "Actor is Null");
            Preconditions.checkArgument(PreconditionUtils.notNull(this.getActor().getSession()), "Session is Null");
            Preconditions.checkArgument(PreconditionUtils.isFalse(this.getActor().disconnected), "Actor is Disconnected");
            Preconditions.checkArgument(PreconditionUtils.isFalse(this.getActor().hitUpdateRequired), "You can't do this while in combat");
            this.validate();
        } catch (Exception e) {
            this.stop();
            this.getActor().sendMessage(e.getMessage());
            return;
        }
        this.onAction();
        this.stop();
    }

    @Override
    protected void onActionStart() {

    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {

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
    }

    @Override
    protected void validateWorldRequirements() {
    }

    @Override
    protected void updateAnimation() {
    }

    public VoyageMenuAction(Player actor, int shipSlot, int voyageSlot) {
        super(actor, actor.getSkillController().getSailing().getId(), 1,null);
        this.shipSlot = shipSlot;
        this.voyageSlot = voyageSlot;
    }

    protected final int shipSlot, voyageSlot;
}

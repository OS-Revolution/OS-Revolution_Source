package ethos.runehub.skill.artisan.cooking.action;

import com.google.common.base.Preconditions;
import ethos.Server;
import ethos.model.players.Player;
import ethos.runehub.entity.item.ItemInteractionContext;
import ethos.runehub.skill.artisan.ArtisanSkillAction;
import ethos.runehub.skill.artisan.cooking.CookingItemReaction;
import ethos.util.PreconditionUtils;

public class CookOnNodeAction extends ArtisanSkillAction {

    @Override
    protected void validateItemRequirements() {
        Preconditions.checkArgument(this.getActor().getItems().playerHasItem(this.getContext().getUsedId(), 1), "You do not have the items needed to do this.");
    }

    @Override
    protected void validateWorldRequirements() {
        if (this.getContext().getUsedWithId() == 114) {
            Preconditions.checkArgument(PreconditionUtils.isFalse(Server.getGlobalObjects().exists(this.getContext().getUsedWithId(), this.getContext().getX(), this.getContext().getY())), "You need something to cook these on.");
        } else {
            Preconditions.checkArgument(PreconditionUtils.isTrue(Server.getGlobalObjects().exists(this.getContext().getUsedWithId(), this.getContext().getX(), this.getContext().getY())), "You need something to cook these on.");
        }
    }

    @Override
    protected void validateInventory() {
        Preconditions.checkArgument(this.getActor().getItems().freeSlots() >= 0, "You must have at least " + 0 + " free inventory slot to do this.");
    }

    @Override
    protected float getRollModifier() {
        return this.getContext().getUsedWithId() == 114 ? 1.05f : 1.0f;
    }

    public CookOnNodeAction(Player player, CookingItemReaction reaction, ItemInteractionContext context) {
        super(player, 7, 4, reaction, context);
        this.setAnimationId(context.getUsedWithId() == 114 ? 896 : 897);
    }


}

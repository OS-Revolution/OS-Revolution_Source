package ethos.runehub.skill;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.entity.item.ItemInteractionContext;
import ethos.runehub.skill.artisan.ArtisanSkillItemReaction;
import ethos.util.PreconditionUtils;
import org.runehub.api.util.SkillDictionary;

import java.util.logging.Logger;

public class SkillItemInteractionAction extends SkillAction{

    @Override
    protected void onActionStart() {

    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {
        if (actions == 0 || actionsDone < actions) {
            if (isSuccessful(reaction.getLow(), reaction.getHigh())) {
                this.onSuccess();
            } else {
                this.onFailure();
            }
            actionsDone++;
        } else {
            this.stop();
        }
    }

    @Override
    protected void onUpdate() {

    }

    @Override
    protected void validateInventory() {
        Preconditions.checkArgument(this.getActor().getItems().freeSlots() >= reaction.getProductItemBaseAmount(), "You must have at least " + reaction.getProductItemBaseAmount() + " free inventory slot to do this.");
    }

    @Override
    protected void validateLevelRequirements() {
        Preconditions.checkArgument(PreconditionUtils.isTrue(this.getActor().getSkillController().getLevel(this.getSkillId()) >= reaction.getLevelRequired()),
                "You need a "
                        + RunehubUtils.getSkillName(this.getSkillId())
                        + " level of at least "
                        + reaction.getLevelRequired()
                        + " to do this.");
    }

    @Override
    protected void validateItemRequirements() {
        if (context.getUsedId() != -1) {
            Preconditions.checkArgument(this.getActor().getItems().playerHasItem(context.getUsedId(), context.getUsedAmount()), "You do not have the items needed to do this.");
        }

        if (context.getUsedWithId() != -1) {
            Preconditions.checkArgument(this.getActor().getItems().playerHasItem(context.getUsedWithId(),context.getUsedWithAmount()), "You do not have the items needed to do this.");

        }
    }

    @Override
    protected void validateWorldRequirements() {

    }

    @Override
    protected void updateAnimation() {

    }

    @Override
    protected void addItems(int id, int amount) {
        this.getActor().getItems().addItem(id,amount);
    }

    protected void onFailure() {
        System.out.println("Failed");
        if (reaction.isUsedConsumed()) {
            this.getActor().getItems().deleteItem(context.getUsedId(), 1);
        }
        if (reaction.isUsedWithConsumed()) {
            this.getActor().getItems().deleteItem(context.getUsedWithId(), 1);
        }
        this.addItems(reaction.getFailedItemId(), 1);
    }

    protected void onSuccess() {
        System.out.println("Succeeded");
        if (reaction.isUsedConsumed()) {
            this.getActor().getItems().deleteItem(context.getUsedId(), 1);
        }
        if (reaction.isUsedWithConsumed()) {
            this.getActor().getItems().deleteItem(context.getUsedWithId(), 1);
        }
        this.addItems(reaction.getProductItemId(), reaction.getProductItemBaseAmount());
        this.addXp(reaction.getReactionXp());
    }

    public SkillItemInteractionAction(Player actor, int skillId, int ticks, ItemInteractionContext context, ArtisanSkillItemReaction reaction, int actions) {
        super(actor, skillId, ticks);
        this.context = context;
        this.reaction = reaction;
        this.actions = actions;
    }

    private int actionsDone;
    private final int actions;
    private final ItemInteractionContext context;
    private final ArtisanSkillItemReaction reaction;
}

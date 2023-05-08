package ethos.runehub.skill.artisan;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import ethos.runehub.entity.item.ItemInteractionContext;
import ethos.runehub.skill.SkillAction;
import ethos.util.PreconditionUtils;
import org.runehub.api.util.SkillDictionary;

import java.util.logging.Logger;

public abstract class ArtisanSkillAction extends SkillAction {

    @Override
    protected void updateAnimation() {

    }

    @Override
    protected void onActionStop() {
        this.getActor().getAttributes().setIntegerInput(0);
        this.getActor().getAttributes().setSkillStationId(-1);
    }

    @Override
    protected void onActionStart() {
        this.getActor().startAnimation(animationId);
    }

    @Override
    protected void onTick() {
        Logger.getGlobal().fine("Starting Artisan Sequence");
        if (this.getIterations() == 0 || iterations< this.getIterations()) {
            this.getActor().startAnimation(animationId);
            if (this.isSuccessful(reaction.getLow(), reaction.getHigh())) {
                this.onSuccess();
            } else {
                this.onFailure();
            }
            iterations++;
        } else {
            this.stop();
        }
//        if (this.getIterations() != 0) {
//            if (iterations <= this.getIterations()) {
//                this.getActor().startAnimation(animationId);
//            }
//            if (iterations <= this.getIterations()) {
//                iterations++;
////            if (super.getElapsedTicks() >= refreshTick * 2 && super.getElapsedTicks() % (refreshTick) == 0) {
//                if (this.isSuccessful(reaction.getLow(), reaction.getHigh())) {
//                    this.onSuccess();
//                } else {
//                    this.onFailure();
//                }
////            }
//            } else if (iterations >= this.getIterations() && this.getIterations() > 0) {
//                this.stop();
//            }
//        }
    }

    @Override
    protected void onUpdate() {
        if (this.getIterations() > 0) {
            if (!initialized) {
                this.getActor().getPA().closeAllWindows();
                initialized = true;
            }
        }
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
    protected void validateLevelRequirements() {
        Preconditions.checkArgument(PreconditionUtils.isTrue(this.getActor().getSkillController().getLevel(this.getSkillId()) >= reaction.getLevelRequired()),
                "You need a "
                        + SkillDictionary.getSkillNameFromId(this.getSkillId())
                        + " level of at least "
                        + reaction.getLevelRequired()
                        + " to do this.");

    }

    @Override
    protected void validateInventory() {
        Preconditions.checkArgument(this.getActor().getItems().freeSlots() >= reaction.getProductItemBaseAmount(), "You must have at least " + reaction.getProductItemBaseAmount() + " free inventory slot to do this.");
    }

    @Override
    protected void addItems(int id, int amount) {
        this.getActor().getItems().addItem(id, amount);
    }

//    protected boolean isSuccessful() {
//        Logger.getGlobal().fine("Efficiency: " + this.getActor().getSkillController().getSkill(this.getSkillId()).getEfficiency());
//        final int baseSuccessRoll = Skill.SKILL_RANDOM.nextInt(100);
//        final int modifiedSuccessRoll = this.getActor().getSkillController().getSkill(this.getSkillId()).getEfficiency();
//        final int roll = baseSuccessRoll + modifiedSuccessRoll;
//        return roll >= reaction.getMinSuccessRoll();
//    }

    protected void onFailure() {
        Logger.getGlobal().fine("Failed");
        if (reaction.isUsedConsumed()) {
            this.getActor().getItems().deleteItem(context.getUsedId(), 1);
        }
        if (reaction.isUsedWithConsumed()) {
            this.getActor().getItems().deleteItem(context.getUsedWithId(), 1);
        }
        this.addItems(reaction.getFailedItemId(), 1);
    }

    protected void onSuccess() {
        Logger.getGlobal().fine("Succeeded");
        if (reaction.isUsedConsumed()) {
            this.getActor().getItems().deleteItem(context.getUsedId(), 1);
        }
        if (reaction.isUsedWithConsumed()) {
            this.getActor().getItems().deleteItem(context.getUsedWithId(), 1);
        }
        this.addItems(reaction.getProductItemId(), reaction.getProductItemBaseAmount());
        this.addXp(reaction.getReactionXp());
    }

    protected ArtisanSkillItemReaction getReaction() {
        return reaction;
    }

    protected int getIterations() {
        return this.getActor().getAttributes().getIntegerInput();
    }

    protected ItemInteractionContext getContext() {
        return context;
    }

    protected int getAnimationId() {
        return animationId;
    }

    protected void setAnimationId(int animationId) {
        this.animationId = animationId;
    }

    public ArtisanSkillAction(Player actor, int skillId, int ticks, ArtisanSkillItemReaction reaction, ItemInteractionContext context) {
        super(actor, skillId, ticks);
        this.reaction = reaction;
        this.refreshTick = ticks;
        this.context = context;
    }

    private boolean initialized;
    private int iterations = 0;
    private final ArtisanSkillItemReaction reaction;
    private int refreshTick;
    private final ItemInteractionContext context;
    private int animationId;
}

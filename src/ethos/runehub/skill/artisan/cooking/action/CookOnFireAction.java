package ethos.runehub.skill.artisan.cooking.action;

import com.google.common.base.Preconditions;
import ethos.Server;
import ethos.model.players.Player;
import ethos.runehub.entity.item.ItemInteractionContext;
import ethos.runehub.skill.artisan.ArtisanSkillAction;
import ethos.runehub.skill.artisan.ArtisanSkillItemReaction;
import ethos.runehub.skill.artisan.cooking.CookingItemReaction;
import ethos.util.Misc;
import ethos.util.PreconditionUtils;

public class CookOnFireAction extends ArtisanSkillAction {


    @Override
    protected void validateItemRequirements() {
        Preconditions.checkArgument(this.getActor().getItems().playerHasItem(this.getContext().getUsedId(), 1), "You do not have the items needed to do this.");
    }

    @Override
    protected void validateWorldRequirements() {
        Preconditions.checkArgument(PreconditionUtils.isTrue(Server.getGlobalObjects().exists(this.getContext().getUsedWithId(), this.getContext().getX(), this.getContext().getY())), "You need something to cook these on.");
    }

    @Override
    protected void validateInventory() {
        Preconditions.checkArgument(this.getActor().getItems().freeSlots() >= 0, "You must have at least " + 0 + " free inventory slot to do this.");
    }

    @Override
    protected float getRollModifier() {
        return 1.0f;
    }

//    @Override
//    protected void onSuccess() {
//        super.onSuccess();
//        if (Misc.random(100) <= 5 && this.getActor().getAttributes().getSkillStationId() == 13542) {
//            this.getActor().getItems().addItemToBank(this.getReaction().getProductItemId(),1);
//            this.getActor().sendMessage("@or@You make two portions of food thanks to the range! The extra was sent to your bank.");
//        }
//    }

    @Override
    protected double getBaseSuccessChance(int min, int max) {
        int modifiedMin = (int) (min * this.getActor().getSkillController().getCooking().getSuccessValuesBonus());
        int modifiedMax = (int) (max * this.getActor().getSkillController().getCooking().getSuccessValuesBonus());
        return this.getActor().getSkillController().getSkill(this.getSkillId()).getActionSuccessChance(modifiedMin, modifiedMax);
    }

    public CookOnFireAction(Player player, ArtisanSkillItemReaction reaction, ItemInteractionContext context, int actions) {
        super(player, 7, 4, reaction, context);
        player.getAttributes().setIntegerInput(actions);
        this.setAnimationId(897);
    }

//    private int actions, actionsDone;
}

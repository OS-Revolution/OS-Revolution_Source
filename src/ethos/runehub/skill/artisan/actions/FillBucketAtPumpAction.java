package ethos.runehub.skill.artisan.actions;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import ethos.runehub.entity.item.ItemInteractionContext;
import ethos.runehub.skill.artisan.ArtisanSkillAction;
import ethos.runehub.skill.artisan.ArtisanSkillItemReaction;

import java.util.logging.Logger;

public class FillBucketAtPumpAction extends ArtisanSkillAction {

    @Override
    protected void validateWorldRequirements() {

    }

    @Override
    protected void validateItemRequirements() {
        if (this.getContext().getUsedId() != -1) {
            Preconditions.checkArgument(this.getActor().getItems().playerHasItem(this.getContext().getUsedId(), this.getContext().getUsedAmount()), "You do not have the items needed to do this.");
        }
    }

    @Override
    protected void onSuccess() {
        int bucketAmount = this.getActor().getItems().getItemAmount(1925);
        this.getActor().getItems().deleteItem2(this.getContext().getUsedId(), bucketAmount);
        this.addItems(this.getReaction().getProductItemId(), bucketAmount);
        this.stop();
    }

    public FillBucketAtPumpAction(Player actor, ArtisanSkillItemReaction reaction, ItemInteractionContext context) {
        super(actor, 7, 4, reaction, context);
        this.setAnimationId(894);
    }
}

package ethos.runehub.skill.artisan.herblore.action;

import ethos.model.items.ItemAssistant;
import ethos.model.players.Player;
import ethos.runehub.entity.item.ItemInteractionContext;
import ethos.runehub.skill.artisan.ArtisanSkillAction;
import ethos.runehub.skill.artisan.ArtisanSkillItemReaction;

public class MixUnfinishedPotionAction extends ArtisanSkillAction {

    @Override
    protected void onActionStart() {
        this.getActor().getPA().sendFrame164(1743);
        this.getActor().getPA().sendFrame246(13716, 190, this.getReaction().getProductItemId());
        this.getActor().getPA().sendFrame126("\\n\\n\\n\\n\\n" + ItemAssistant.getItemName(this.getReaction().getProductItemId()) + "", 13717);
        this.getActor().getPA().sendFrame126("How many would you like to make?", 13721);
//        for (int i = 13716; i < 13800; i++) {
//            this.getActor().getPA().sendFrame126("Frame: " + i, i);
//        }
    }

    @Override
    protected void validateWorldRequirements() {

    }

    public MixUnfinishedPotionAction(Player actor, ArtisanSkillItemReaction reaction, ItemInteractionContext context) {
        super(actor, 15, 3, reaction, context);
        this.setAnimationId(363);
    }

}

package ethos.runehub.entity.item;

import ethos.model.items.ItemAssistant;
import ethos.model.players.Player;
import ethos.runehub.skill.artisan.ArtisanSkillItemReaction;
import ethos.runehub.skill.artisan.cooking.CookingItemReaction;
import ethos.runehub.skill.artisan.cooking.action.CookOnNodeAction;
import ethos.runehub.skill.artisan.herblore.HerbloreItemReaction;
import ethos.runehub.skill.artisan.herblore.action.CrushItemAction;
import ethos.runehub.skill.artisan.herblore.action.MixUnfinishedPotionAction;
import ethos.runehub.skill.artisan.smithing.SmeltingItemReaction;
import ethos.runehub.skill.artisan.smithing.action.SmeltAction;

public class ItemReactionProcessor {

    public void process(ItemInteractionContext context, ItemInteraction interaction, ItemReaction reaction, Player player) {
//        if (reaction.getUuid() == -4605300025780253777L) {
        if (reaction instanceof ArtisanSkillItemReaction) {
            final ArtisanSkillItemReaction artisanSkillItemReaction = (ArtisanSkillItemReaction) reaction;
            if (artisanSkillItemReaction.getActionId() == 1) {
                player.getPA().sendFrame164(1743);
                player.getPA().sendFrame246(13716, 190, ((ArtisanSkillItemReaction) reaction).getProductItemId());
                player.getPA().sendFrame126("\\n\\n\\n\\n\\n" + ItemAssistant.getItemName(((ArtisanSkillItemReaction) reaction).getProductItemId()) + "", 13717);

                player.getSkillController().getCooking().train(new CookOnNodeAction(
                        player,
                        (CookingItemReaction) reaction,
                        context
                ));
            } else if (artisanSkillItemReaction.getActionId() == 2) {
                player.getSkillController().getHerblore().train(new MixUnfinishedPotionAction(
                        player,
                        (HerbloreItemReaction) reaction,
                        context
                ));
            } else if (artisanSkillItemReaction.getActionId() == 3) {
                player.getSkillController().getHerblore().train(new CrushItemAction(
                        player,
                        (HerbloreItemReaction) reaction,
                        context
                ));
            } else if (artisanSkillItemReaction.getActionId() == 4) { //smelting
//                player.getSkillController().getSmithing().train(new SmeltAction(
//                        player,
//                        1,
//                        (SmeltingItemReaction) reaction,
//                        context
//                ));
            }
        } else {
            System.out.println(reaction.getClass());
        }

    }
}

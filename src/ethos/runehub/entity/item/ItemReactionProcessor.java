package ethos.runehub.entity.item;

import ethos.model.items.ItemAssistant;
import ethos.model.players.Player;
import ethos.runehub.skill.artisan.ArtisanSkillItemReaction;
import ethos.runehub.skill.artisan.cooking.CookingItemReaction;
import ethos.runehub.skill.artisan.cooking.action.CookOnNodeAction;
import ethos.runehub.skill.artisan.crafting.action.CraftLeatherAction;
import ethos.runehub.skill.artisan.crafting.action.CutGemAction;
import ethos.runehub.skill.artisan.crafting.CraftingItemReaction;
import ethos.runehub.skill.artisan.herblore.HerbloreItemReaction;
import ethos.runehub.skill.artisan.herblore.action.CrushItemAction;
import ethos.runehub.skill.artisan.herblore.action.MixUnfinishedPotionAction;
import ethos.runehub.ui.impl.crafting.LeatherCraftingUI;

public class ItemReactionProcessor {

    public void process(ItemInteractionContext context, ItemInteraction interaction, ItemReaction reaction, Player player) {
        if (reaction instanceof ArtisanSkillItemReaction) {
            final ArtisanSkillItemReaction artisanSkillItemReaction = (ArtisanSkillItemReaction) reaction;
            if (artisanSkillItemReaction.getActionId() == 1) {
                player.getPA().sendFrame164(1743);
                player.getPA().sendFrame246(13716, 190, ((ArtisanSkillItemReaction) reaction).getProductItemId());
                player.getPA().sendFrame126("\\n\\n\\n\\n\\n" + ItemAssistant.getItemName(((ArtisanSkillItemReaction) reaction).getProductItemId()) + "", 13717);
            if (context.getUsedWithId() == 13542) {
                player.getAttributes().setSkillStationId(context.getUsedWithId());
            }
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

            } else if (artisanSkillItemReaction.getActionId() == 5) { //gem cutting
                player.getSkillController().getCrafting().train(new CutGemAction(
                        player,
                        (CraftingItemReaction) reaction,
                        context
                ));
            } else if (artisanSkillItemReaction.getActionId() == 6) { //leatherwork
                player.sendUI(new LeatherCraftingUI(player, 1741));
            } else if (artisanSkillItemReaction.getActionId() == 7) { //hardleather
                player.sendUI(new LeatherCraftingUI(player, 1743));
            } else if (artisanSkillItemReaction.getActionId() == 8) { //green dragon leather
                player.sendUI(new LeatherCraftingUI(player, 1745));
            } else if (artisanSkillItemReaction.getActionId() == 9) { //blue dragon leather
                player.sendUI(new LeatherCraftingUI(player, 2505));
            } else if (artisanSkillItemReaction.getActionId() == 10) { //red dragon leather
                player.sendUI(new LeatherCraftingUI(player, 2507));
            } else if (artisanSkillItemReaction.getActionId() == 11) { //black dragon leather
                player.sendUI(new LeatherCraftingUI(player, 2509));
            } else if (artisanSkillItemReaction.getActionId() == 12) { //snakeskin
                player.sendUI(new LeatherCraftingUI(player, 6289));
            } else if (artisanSkillItemReaction.getActionId() == 13) { //yak-hide
                player.sendUI(new LeatherCraftingUI(player, 10820));
            }
        } else {
            System.out.println(reaction.getClass());
        }
    }
}

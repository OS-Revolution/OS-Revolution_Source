package ethos.runehub.entity.item;

import ethos.model.items.ItemAssistant;
import ethos.model.players.Player;
import ethos.runehub.skill.SkillItemInteractionAction;
import ethos.runehub.skill.artisan.ArtisanSkillItemReaction;
import ethos.runehub.skill.artisan.actions.FillBucketAtPumpAction;
import ethos.runehub.skill.artisan.cooking.CookingItemReaction;
import ethos.runehub.skill.artisan.cooking.action.CookOnFireAction;
import ethos.runehub.skill.artisan.cooking.action.CookOnNodeAction;
import ethos.runehub.skill.artisan.crafting.action.CutGemAction;
import ethos.runehub.skill.artisan.crafting.CraftingItemReaction;
import ethos.runehub.skill.artisan.fletching.ui.FletchLogsSkillMenuUI;
import ethos.runehub.skill.artisan.fletching.ui.FletchProjectileSkillMenuUI;
import ethos.runehub.skill.artisan.fletching.ui.MountCrossbowSkillMenuUI;
import ethos.runehub.skill.artisan.fletching.ui.StringBowSkillMenuUI;
import ethos.runehub.skill.artisan.herblore.HerbloreItemReaction;
import ethos.runehub.skill.artisan.herblore.action.CrushItemAction;
import ethos.runehub.skill.artisan.herblore.action.MixUnfinishedPotionAction;
import ethos.runehub.skill.combat.prayer.action.SacrificeRemainsOnAltarAction;
import ethos.runehub.skill.support.firemaking.action.KindlingOnTinderboxAction;
import ethos.runehub.skill.support.firemaking.action.TinderboxOnKindlingAction;
import ethos.runehub.ui.impl.cooking.CookingUI;
import ethos.runehub.ui.impl.cooking.MixingUI;
import ethos.runehub.ui.impl.crafting.LeatherCraftingUI;

public class ItemReactionProcessor {

    public void process(ItemInteractionContext context, ItemInteraction interaction, ItemReaction reaction, Player player) {
        if (reaction instanceof ArtisanSkillItemReaction) {
            final ArtisanSkillItemReaction artisanSkillItemReaction = (ArtisanSkillItemReaction) reaction;
            if (artisanSkillItemReaction.getActionId() == 1) {
                player.sendUI(new CookingUI(player, context));
//                player.getPA().sendFrame164(1743);
//                player.getPA().sendFrame246(13716, 190, ((ArtisanSkillItemReaction) reaction).getProductItemId());
//                player.getPA().sendFrame126("\\n\\n\\n\\n\\n" + ItemAssistant.getItemName(((ArtisanSkillItemReaction) reaction).getProductItemId()) + "", 13717);
//            if (context.getUsedWithId() == 13542) {
//                player.getAttributes().setSkillStationId(context.getUsedWithId());
//            }
//                player.getSkillController().getCooking().train(new CookOnNodeAction(
//                        player,
//                        (CookingItemReaction) reaction,
//                        context
//                ));
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
            } else if (artisanSkillItemReaction.getActionId() == 14) { //bones on altar
                player.getSkillController().getPrayer().train(new SacrificeRemainsOnAltarAction(player, interaction.getUsedId(), context));
            } else if (artisanSkillItemReaction.getActionId() == 15) { //lighting fire
                player.getSkillController().getFiremaking().train(new TinderboxOnKindlingAction(player, context, artisanSkillItemReaction));
            } else if (artisanSkillItemReaction.getActionId() == 16) { //lighting fire
                player.getSkillController().getFiremaking().train(new KindlingOnTinderboxAction(player, context, artisanSkillItemReaction));
            } else if (artisanSkillItemReaction.getActionId() == 17 && context.getUsedId() == 946) { //fletching logs
                player.sendUI(new FletchLogsSkillMenuUI(player, context.getUsedWithId()));
            } else if (artisanSkillItemReaction.getActionId() == 17 && context.getUsedWithId() == 946) { //fletching logs
                player.sendUI(new FletchLogsSkillMenuUI(player, context.getUsedId()));
            } else if (artisanSkillItemReaction.getActionId() == 18) { //stringing bows
                player.sendUI(new StringBowSkillMenuUI(player, context.getUsedWithId()));
            } else if (artisanSkillItemReaction.getActionId() == 19) { //stringing bows
                player.sendUI(new StringBowSkillMenuUI(player, context.getUsedId()));
            } else if (artisanSkillItemReaction.getActionId() == 20) { //stringing bows
                player.sendUI(new MountCrossbowSkillMenuUI(player, context.getUsedWithId()));
            } else if (artisanSkillItemReaction.getActionId() == 21) { //stringing bows
                player.sendUI(new FletchProjectileSkillMenuUI(player, context.getUsedId()));
            } else if (artisanSkillItemReaction.getActionId() == 22) { //making bolt tips
                player.sendUI(new FletchProjectileSkillMenuUI(player, context.getUsedWithId()));
            } else if (artisanSkillItemReaction.getActionId() == 23) { //making bolt tips
                player.sendUI(new FletchProjectileSkillMenuUI(player, context.getUsedId()));
            } else if (artisanSkillItemReaction.getActionId() == 24) { //cooking skill combinations
                player.sendUI(new MixingUI(player, context));
            } else if (artisanSkillItemReaction.getActionId() == 25) { //cake making
                if (context.getUsedId() == 1887) {
                    if (
                            context.getUsedWithId() == 1944
                                    || context.getUsedWithId() == 1933
                                    || context.getUsedWithId() == 1927
                    ) {

                    }
                } else if (context.getUsedWithId() == 1887) {

                }
            } else if (artisanSkillItemReaction.getActionId() == 100) { //Filling bucket at pump
                player.getSkillController().getCooking().train(new FillBucketAtPumpAction(player,artisanSkillItemReaction,context));
            }
        } else {
            System.out.println(reaction.getClass());
        }
    }
}

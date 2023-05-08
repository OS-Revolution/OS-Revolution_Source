package ethos.runehub.entity.item;

import ethos.runehub.skill.artisan.cooking.CookingItemReactionLoader;
import ethos.runehub.skill.artisan.crafting.CraftingItemReactionLoader;
import ethos.runehub.skill.artisan.fletching.FletchingItemReactionDAO;
import ethos.runehub.skill.artisan.herblore.HerbloreItemReactionLoader;
import ethos.runehub.skill.artisan.smithing.SmeltingItemReactionLoader;
import ethos.runehub.skill.combat.prayer.remains.RemainsItemReaction;
import ethos.runehub.skill.combat.prayer.remains.RemainsItemReactionLoader;
import ethos.runehub.skill.support.firemaking.kindling.KindlingItemReactionLoader;

public class ItemReactionFactory {

    public static ItemReaction getItemReaction(int reactionKey, long reactionUuid) {
        switch (reactionKey) {
            case 7:
                return ArtisanSkillItemReactionDAO.getInstance().read(reactionUuid);
            case 12:
                return CraftingItemReactionLoader.getInstance().read(reactionUuid);
            case 13:
                return SmeltingItemReactionLoader.getInstance().read(reactionUuid);
            case 15:
                return HerbloreItemReactionLoader.getInstance().read(reactionUuid);
            case 5:
                return RemainsItemReactionLoader.getInstance().read(reactionUuid);
            case 11:
                return KindlingItemReactionLoader.getInstance().read(reactionUuid);
            case 9:
                return FletchingItemReactionDAO.getInstance().read(reactionUuid);
            default:
                throw new NullPointerException("No Reactions Available: " + reactionKey);
        }
    }
}

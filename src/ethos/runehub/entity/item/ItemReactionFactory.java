package ethos.runehub.entity.item;

import ethos.runehub.skill.artisan.cooking.CookingItemReactionLoader;
import ethos.runehub.skill.artisan.herblore.HerbloreItemReactionLoader;
import ethos.runehub.skill.artisan.smithing.SmeltingItemReactionDAO;
import ethos.runehub.skill.artisan.smithing.SmeltingItemReactionLoader;
import org.runehub.api.util.SkillDictionary;

public class ItemReactionFactory {

    public static ItemReaction getItemReaction(int reactionKey, long reactionUuid) {
        switch (reactionKey) {
            case 7:
                return CookingItemReactionLoader.getInstance().read(reactionUuid);
            case 13:
                return SmeltingItemReactionLoader.getInstance().read(reactionUuid);
            case 15:
                return HerbloreItemReactionLoader.getInstance().read(reactionUuid);
            default:
                throw new NullPointerException("No Reactions Available: " + reactionKey);
        }
    }
}

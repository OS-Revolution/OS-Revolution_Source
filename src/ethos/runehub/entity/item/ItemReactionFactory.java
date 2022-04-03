package ethos.runehub.entity.item;

import ethos.runehub.skill.artisan.cooking.CookingItemReactionLoader;
import ethos.runehub.skill.artisan.herblore.HerbloreItemReactionLoader;

public class ItemReactionFactory {

    public static ItemReaction getItemReaction(int reactionKey, long reactionUuid) {
        switch (reactionKey) {
            case 7:
                return CookingItemReactionLoader.getInstance().read(reactionUuid);
            case 15:
                return HerbloreItemReactionLoader.getInstance().read(reactionUuid);
            default:
                throw new NullPointerException("No Reactions Available: " + reactionKey);
        }
    }
}

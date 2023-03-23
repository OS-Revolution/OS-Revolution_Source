package ethos.runehub.skill.artisan.fletching;

import ethos.runehub.skill.artisan.ArtisanSkillItemReaction;
import org.runehub.api.io.data.StoredObject;

@StoredObject(tableName = "fletching_item_reaction")
public class FletchingItemReaction extends ArtisanSkillItemReaction {

    public FletchingItemReaction(long uuid, int actionId, int levelRequired, int low, int productItemId, int productItemBaseAmount, int failedItemId, int reactionXp, boolean usedConsumed, boolean usedWithConsumed, int high) {
        super(uuid, actionId, levelRequired, low, productItemId, productItemBaseAmount, failedItemId, reactionXp, usedConsumed, usedWithConsumed, high);
    }
}

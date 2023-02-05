package ethos.runehub.skill.artisan.crafting;

import ethos.runehub.skill.artisan.ArtisanSkillItemReaction;
import org.runehub.api.io.data.StoredObject;

@StoredObject(tableName = "crafting_item_reactions")
public class CraftingItemReaction extends ArtisanSkillItemReaction {


    public CraftingItemReaction(long uuid, int actionId, int levelRequired, int low, int productItemId, int productItemBaseAmount, int failedItemId, int reactionXp, boolean usedConsumed, boolean usedWithConsumed, int high) {
        super(uuid, actionId, levelRequired, low, productItemId, productItemBaseAmount, failedItemId, reactionXp, usedConsumed, usedWithConsumed, high);
    }
}

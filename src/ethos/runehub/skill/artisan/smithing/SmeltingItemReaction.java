package ethos.runehub.skill.artisan.smithing;

import ethos.runehub.skill.artisan.ArtisanSkillItemReaction;
import org.runehub.api.io.data.StoredObject;

@StoredObject(tableName = "smelting_item_reactions")
public class SmeltingItemReaction extends ArtisanSkillItemReaction {

    public SmeltingItemReaction(long uuid, int actionId, int levelRequired, int minBurnRoll, int productItemId, int productItemBaseAmount, int failedItemId,
                                int reactionXp, boolean usedConsumed, boolean usedWithConsumed, int high) {
        super(uuid,actionId,levelRequired,minBurnRoll,productItemId,productItemBaseAmount,failedItemId,reactionXp,usedConsumed, usedWithConsumed,high);
    }


}

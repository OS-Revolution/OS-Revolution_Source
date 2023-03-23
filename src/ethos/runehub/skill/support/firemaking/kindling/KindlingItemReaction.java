package ethos.runehub.skill.support.firemaking.kindling;

import ethos.runehub.skill.artisan.ArtisanSkillItemReaction;
import org.runehub.api.io.data.StoredObject;

@StoredObject(tableName = "kindling_reactions")
public class KindlingItemReaction extends ArtisanSkillItemReaction {

    public KindlingItemReaction(long uuid, int actionId, int levelRequired, int low, int productItemId, int productItemBaseAmount, int failedItemId, int reactionXp, boolean usedConsumed, boolean usedWithConsumed, int high) {
        super(uuid, actionId, levelRequired, low, productItemId, productItemBaseAmount, failedItemId, reactionXp, usedConsumed, usedWithConsumed, high);
    }
}

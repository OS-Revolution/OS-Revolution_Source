package ethos.runehub.skill.combat.prayer.remains;

import ethos.runehub.skill.artisan.ArtisanSkillItemReaction;
import org.runehub.api.io.data.StoredObject;

@StoredObject(tableName = "remains_altar_reactions")
public class RemainsItemReaction extends ArtisanSkillItemReaction {


    public RemainsItemReaction(long uuid, int actionId, int levelRequired, int low, int productItemId, int productItemBaseAmount, int failedItemId, int reactionXp, boolean usedConsumed, boolean usedWithConsumed, int high) {
        super(uuid, 14, 1, 256, -1, 0, -1, reactionXp, usedConsumed, false, 256);
    }
}

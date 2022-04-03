package ethos.runehub.skill.artisan;

import ethos.runehub.entity.item.ItemReaction;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;

@StoredObject(tableName = "artisan_item_reactions")
public class ArtisanSkillItemReaction extends ItemReaction {

    public int getLevelRequired() {
        return levelRequired;
    }

    public int getActionId() {
        return actionId;
    }

    public int getProductItemBaseAmount() {
        return productItemBaseAmount;
    }

    public int getProductItemId() {
        return productItemId;
    }

    public int getFailedItemId() {
        return failedItemId;
    }

//    public int getReactionAnimationId() {
//        return reactionAnimationId;
//    }

    public int getReactionXp() {
        return reactionXp;
    }

//    public int getItemUsedId() {
//        return itemUsedId;
//    }
//
//    public int getItemUsedWithId() {
//        return itemUsedWithId;
//    }
//
//    public int getItemUsedAmount() {
//        return itemUsedAmount;
//    }
//
//    public int getItemUsedWithAmount() {
//        return itemUsedWithAmount;
//    }

    public boolean isUsedConsumed() {
        return usedConsumed;
    }

    public boolean isUsedWithConsumed() {
        return usedWithConsumed;
    }

    public int getHigh() {
        return high;
    }

    public int getLow() {
        return low;
    }

    public ArtisanSkillItemReaction(long uuid, int actionId, int levelRequired, int low, int productItemId, int productItemBaseAmount, int failedItemId,
                                    int reactionXp, boolean usedConsumed, boolean usedWithConsumed, int high) {
        super(uuid);
        this.actionId = actionId;
        this.levelRequired = levelRequired;
        this.low = low;
        this.productItemId = productItemId;
        this.productItemBaseAmount = productItemBaseAmount;
        this.failedItemId = failedItemId;
//        this.reactionAnimationId = reactionAnimationId;
        this.reactionXp = reactionXp;
//        this.itemUsedId = itemUsedId;
//        this.itemUsedWithId = itemUsedWithId;
//        this.itemUsedAmount = itemUsedAmount;
//        this.itemUsedWithAmount = itemUsedWithAmount;
        this.usedConsumed = usedConsumed;
        this.usedWithConsumed = usedWithConsumed;
        this.high = high;
    }

    @StoredValue(type = SqlDataType.INTEGER)
    private final int actionId;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int levelRequired;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int low;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int productItemId;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int productItemBaseAmount;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int failedItemId;
//    @StoredValue(type = SqlDataType.INTEGER)
//    private final int reactionAnimationId;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int reactionXp;
//    @StoredValue(type = SqlDataType.INTEGER)
//    private final int itemUsedId;
//    @StoredValue(type = SqlDataType.INTEGER)
//    private final int itemUsedWithId;
//    @StoredValue(type = SqlDataType.INTEGER)
//    private final int itemUsedAmount;
//    @StoredValue(type = SqlDataType.INTEGER)
//    private final int itemUsedWithAmount;
    @StoredValue(type = SqlDataType.BOOLEAN)
    private final boolean usedConsumed;
    @StoredValue(type = SqlDataType.BOOLEAN)
    private final boolean usedWithConsumed;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int high;
}

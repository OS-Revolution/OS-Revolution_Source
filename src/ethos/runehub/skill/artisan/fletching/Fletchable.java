package ethos.runehub.skill.artisan.fletching;

import ethos.runehub.entity.item.GameItem;
import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;
@StoredObject(tableName = "fletchable")
public class Fletchable {

    public int getProductId() {
        return productId;
    }

    public GameItem[] getMaterials() {
        return materials;
    }

    public boolean isMembers() {
        return members;
    }

    public int getBaseXp() {
        return baseXp;
    }

    public int getLevelRequirement() {
        return levelRequirement;
    }

    public int getAmountProduced() {
        return amountProduced;
    }

    public int getAnimationId() {
        return animationId;
    }

    public Fletchable(int productId, GameItem[] materials, boolean members, int baseXp, int levelRequirement, int amountProduced, int animationId) {
        this.productId = productId;
        this.materials = materials;
        this.members = members;
        this.baseXp = baseXp;
        this.levelRequirement = levelRequirement;
        this.amountProduced = amountProduced;
        this.animationId = animationId;
    }

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int productId;
    @StoredValue(type = SqlDataType.JSON)
    private final GameItem[] materials;
    @StoredValue(type = SqlDataType.BOOLEAN)
    private final boolean members;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int baseXp;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int levelRequirement;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int amountProduced;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int animationId;
}

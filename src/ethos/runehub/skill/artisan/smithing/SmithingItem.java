package ethos.runehub.skill.artisan.smithing;

import ethos.runehub.entity.item.GameItem;
import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;
@StoredObject(tableName = "smithing_items")
public class SmithingItem {

    public int getItemId() {
        return itemId;
    }

    public int getAmountProduced() {
        return amountProduced;
    }

    public boolean isMembers() {
        return members;
    }

    public int getXp() {
        return xp;
    }

    public int getLevelRequired() {
        return levelRequired;
    }

    public GameItem[] getMaterials() {
        return materials;
    }

    public SmithingItem(int itemId, int amountProduced, boolean members, int xp, int levelRequired, GameItem[] materials) {
        this.itemId = itemId;
        this.amountProduced = amountProduced;
        this.members = members;
        this.xp = xp;
        this.levelRequired = levelRequired;
        this.materials = materials;
    }

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int itemId;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int amountProduced;
    @StoredValue(type = SqlDataType.BOOLEAN)
    private final boolean members;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int xp;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int levelRequired;
    @StoredValue(type = SqlDataType.JSON)
    private final GameItem[] materials;
}

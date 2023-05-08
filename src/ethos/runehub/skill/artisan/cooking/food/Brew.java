package ethos.runehub.skill.artisan.cooking.food;

import ethos.runehub.entity.item.GameItem;
import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;
@StoredObject(tableName = "brews")
public class Brew {

    public int getBaseBrewedItemId() {
        return baseBrewedItemId;
    }

    public int getPremiumBrewedItemId() {
        return premiumBrewedItemId;
    }

    public GameItem[] getMaterials() {
        return materials;
    }

    public boolean isMembers() {
        return members;
    }

    public int getLevel() {
        return level;
    }

    public int getXp() {
        return xp;
    }

    public Brew(int baseBrewedItemId, int premiumBrewedItemId, GameItem[] materials, int level, int xp, boolean members) {
        this.baseBrewedItemId = baseBrewedItemId;
        this.premiumBrewedItemId = premiumBrewedItemId;
        this.materials = materials;
        this.level = level;
        this.xp = xp;
        this.members = members;
    }

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int baseBrewedItemId;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int premiumBrewedItemId;
    @StoredValue(type = SqlDataType.JSON)
    private final GameItem[] materials;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int level;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int xp;
    @StoredValue(type = SqlDataType.BOOLEAN)
    private final boolean members;
}

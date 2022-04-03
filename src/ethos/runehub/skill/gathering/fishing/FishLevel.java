package ethos.runehub.skill.gathering.fishing;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;

@StoredObject(tableName = "fish_levels")
public class FishLevel {

    public int getLevelRequired() {
        return levelRequired;
    }

    public int getItemId() {
        return itemId;
    }

    public int getXp() {
        return xp;
    }

    public int getBaitId() {
        return baitId;
    }

    public FishLevel(int itemId, int level, int xp, int baitId) {
        this.itemId = itemId;
        this.levelRequired = level;
        this.xp = xp;
        this.baitId = baitId;
    }

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int itemId;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int levelRequired;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int xp;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int baitId;



}

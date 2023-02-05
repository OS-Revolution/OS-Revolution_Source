package ethos.runehub.skill.combat.magic;

import ethos.runehub.entity.item.GameItem;
import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;

@StoredObject(tableName = "magic_tablets")
public class MagicTablet {

    public int getItemId() {
        return itemId;
    }

    public int getLevel() {
        return level;
    }

    public int getXp() {
        return xp;
    }

    public GameItem[] getMaterials() {
        return materials;
    }

    public boolean[] getLectern() {
        return lectern;
    }

    public boolean isMembers() {
        return members;
    }

    public MagicTablet(int itemId, int level, int xp, GameItem[] materials, boolean[] lectern, boolean members) {
        this.itemId = itemId;
        this.level = level;
        this.xp = xp;
        this.materials = materials;
        this.lectern = lectern;
        this.members = members;
    }

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int itemId;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int level;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int xp;
    @StoredValue(type = SqlDataType.JSON)
    private final GameItem[] materials;
    @StoredValue(type = SqlDataType.JSON)
    private final boolean[] lectern;
    @StoredValue(type = SqlDataType.BOOLEAN)
    private final boolean members;
}

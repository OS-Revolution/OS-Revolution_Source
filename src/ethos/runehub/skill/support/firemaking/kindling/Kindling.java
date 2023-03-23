package ethos.runehub.skill.support.firemaking.kindling;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;

@StoredObject(tableName = "kindling")
public class Kindling {

    public int getItemId() {
        return itemId;
    }

    public int getFirePropId() {
        return firePropId;
    }

    public int getBaseXp() {
        return baseXp;
    }

    public boolean isMembers() {
        return members;
    }

    public int getLevelRequirement() {
        return levelRequirement;
    }

    public Kindling(int itemId, int firePropId, int baseXp, boolean members, int levelRequirement) {
        this.itemId = itemId;
        this.firePropId = firePropId;
        this.baseXp = baseXp;
        this.members = members;
        this.levelRequirement = levelRequirement;
    }

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int itemId;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int firePropId;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int baseXp;
    @StoredValue(type = SqlDataType.BOOLEAN)
    private final boolean members;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int levelRequirement;

}

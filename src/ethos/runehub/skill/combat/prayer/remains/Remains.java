package ethos.runehub.skill.combat.prayer.remains;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;
@StoredObject(tableName = "remains")
public class Remains {

    public int getItemId() {
        return itemId;
    }

    public int getBaseXp() {
        return baseXp;
    }

    public Remains(int id, int baseXp) {
        this.itemId = id;
        this.baseXp = baseXp;
    }

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int itemId;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int baseXp;
}

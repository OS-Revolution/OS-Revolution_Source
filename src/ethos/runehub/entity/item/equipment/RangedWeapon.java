package ethos.runehub.entity.item.equipment;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;
@StoredObject(tableName = "ranged_weapons")
public class RangedWeapon {

    public int getItemId() {
        return itemId;
    }

    public int[] getAcceptableProjectiles() {
        return acceptableProjectiles;
    }

    public RangedWeapon(int itemId, int[] acceptableProjectiles) {
        this.itemId = itemId;
        this.acceptableProjectiles = acceptableProjectiles;
    }

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int itemId;
    @StoredValue(type = SqlDataType.JSON)
    private final int[] acceptableProjectiles;
}

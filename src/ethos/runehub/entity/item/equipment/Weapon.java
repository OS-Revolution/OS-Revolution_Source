package ethos.runehub.entity.item.equipment;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;

@StoredObject(tableName = "weapon")
public class Weapon {

    public int getItemId() {
        return itemId;
    }

    public int getWeaponType() {
        return weaponType;
    }

    public int getCombatType() {
        return combatType;
    }

    public Weapon(int itemId, int style, int combatType) {
        this.itemId = itemId;
        this.weaponType = style;
        this.combatType = combatType;
    }

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int itemId;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int weaponType;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int combatType;
}

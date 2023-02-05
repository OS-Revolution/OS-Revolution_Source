package ethos.runehub.skill.combat.magic;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;

@StoredObject(tableName = "enchants")
public class Enchantable {

    public int getBaseItemId() {
        return baseItemId;
    }

    public int getEchantedItemId() {
        return echantedItemId;
    }

    public int getSpellId() {
        return spellId;
    }

    public Enchantable(int baseItemId, int echantedItemId, int spellId) {
        this.baseItemId = baseItemId;
        this.echantedItemId = echantedItemId;
        this.spellId = spellId;
    }

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int baseItemId;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int echantedItemId;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int spellId;
}

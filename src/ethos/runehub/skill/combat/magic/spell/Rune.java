package ethos.runehub.skill.combat.magic.spell;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;
@StoredObject(tableName = "runes")
public class Rune {

    public int getId() {
        return id;
    }

    public int[] getValidEquipment() {
        return validEquipment;
    }

    public int[] getValidItems() {
        return validItems;
    }

    public RuneIdentifier getRuneId() {
        return RuneIdentifier.values()[id];
    }

    public Rune(int id, int[] validItems, int[] validEquipment) {
        this.id = id;
        this.validItems = validItems;
        this.validEquipment = validEquipment;
    }

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int id;
    @StoredValue(type = SqlDataType.JSON)
    private final int[] validItems;
    @StoredValue(type = SqlDataType.JSON)
    private final int[] validEquipment;
}

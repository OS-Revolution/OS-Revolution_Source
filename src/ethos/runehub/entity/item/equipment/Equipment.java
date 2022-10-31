package ethos.runehub.entity.item.equipment;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;

import java.util.Arrays;

@StoredObject(tableName = "equipment")
public class Equipment {

    public static final int STAB_BONUS = 0;
    public static final int SLASH_BONUS = 1;
    public static final int CRUSH_BONUS = 2;
    public static final int MAGIC_BONUS = 3;
    public static final int RANGE_BONUS = 4;

    public int getItemId() {
        return itemId;
    }

    public int[] getAttackBonus() {
        return attackBonus;
    }

    public int[] getDefenceBonus() {
        return defenceBonus;
    }

    public int getMeleeStrengthBonus() {
        return meleeStrengthBonus;
    }

    public int getRangedStrengthBonus() {
        return rangedStrengthBonus;
    }

    public double getMagicDamageBonus() {
        return magicDamageBonus;
    }

    public int getPrayerBonus() {
        return prayerBonus;
    }

    public int getSlot() {
        return slot;
    }

    public int getSpeed() {
        return speed;
    }

    public int getRange() {
        return range;
    }

    @Override
    public String toString() {
        return "Equipment[\n"
                + "ItemID: " + itemId + "\n"
                + "Attack Bonuses: " + Arrays.toString(attackBonus) + "\n"
                + "Defence Bonuses: " + Arrays.toString(defenceBonus) + "\n"
                + "Melee Strength: " + meleeStrengthBonus + "\n"
                + "Ranged Strength: " + rangedStrengthBonus + "\n"
                + "Magic Damage: " + magicDamageBonus + "\n"
                + "Prayer: " + prayerBonus + "\n"
                + "Slot: " + EquipmentSlot.values()[slot] + "[" + slot + "]" + "\n"
                + "Speed: " + speed + "\n"
                + "Range: " + range + "\n"
                + "]";
    }

    public Equipment(int itemId, int[] attackBonus, int[] defenceBonus, int meleeStrengthBonus, int rangedStrengthBonus,
                     double magicDamageBonus, int prayerBonus, int slot, int speed, int range) {
        this.itemId = itemId;
        this.attackBonus = attackBonus;
        this.defenceBonus = defenceBonus;
        this.meleeStrengthBonus = meleeStrengthBonus;
        this.rangedStrengthBonus = rangedStrengthBonus;
        this.magicDamageBonus = magicDamageBonus;
        this.prayerBonus = prayerBonus;
        this.slot = slot;
        this.speed = speed;
        this.range = range;
    }

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int itemId;
    @StoredValue(type = SqlDataType.JSON)
    private final int[] attackBonus;
    @StoredValue(type = SqlDataType.JSON)
    private final int[] defenceBonus;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int meleeStrengthBonus;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int rangedStrengthBonus;
    @StoredValue(type = SqlDataType.DOUBLE)
    private final double magicDamageBonus;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int prayerBonus;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int slot;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int speed;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int range;
}

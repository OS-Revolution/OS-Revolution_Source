package ethos.runehub.skill.combat.magic.spell;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;
@StoredObject(tableName = "spells")
public class Spell {

    public int getSpellId() {
        return spellId;
    }

    public int getButtonId() {
        return buttonId;
    }

    public boolean isMembers() {
        return members;
    }

    public int getTicks() {
        return ticks;
    }

    public int getAnimationId() {
        return animationId;
    }

    public int getGfxId() {
        return gfxId;
    }

    public int getCastXP() {
        return castXP;
    }

    public int getLevel() {
        return level;
    }

    public int[][] getRunes() {
        return runes;
    }

    public Spell(int spellId, int buttonId, boolean members, int ticks, int animationId, int gfxId, int castXP, int level,int[][] runes) {
        this.spellId = spellId;
        this.buttonId = buttonId;
        this.members = members;
        this.ticks = ticks;
        this.animationId = animationId;
        this.gfxId = gfxId;
        this.castXP = castXP;
        this.level = level;
        this.runes = runes;
    }

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int spellId;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int buttonId;
    @StoredValue(type = SqlDataType.BOOLEAN)
    private final boolean members;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int ticks;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int animationId;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int gfxId;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int castXP;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int level;
    @StoredValue(type = SqlDataType.JSON)
    private final int[][] runes;
}

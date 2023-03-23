package ethos.runehub.skill.artisan.fletching.bows;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;

@StoredObject(tableName = "strung_bows")
public class StrungBow {

    public int getBowId() {
        return bowId;
    }

    public int getUnstrungBowId() {
        return unstrungBowId;
    }

    public boolean isMembers() {
        return members;
    }

    public int getBaseXp() {
        return baseXp;
    }

    public int getLevelRequirement() {
        return levelRequirement;
    }

    public StrungBow(int bowId, int unstrungBowId, boolean members, int baseXp, int levelRequirement) {
        this.bowId = bowId;
        this.unstrungBowId = unstrungBowId;
        this.members = members;
        this.baseXp = baseXp;
        this.levelRequirement = levelRequirement;
    }

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int bowId;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int unstrungBowId;
    @StoredValue(type = SqlDataType.BOOLEAN)
    private final boolean members;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int baseXp;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int levelRequirement;
}

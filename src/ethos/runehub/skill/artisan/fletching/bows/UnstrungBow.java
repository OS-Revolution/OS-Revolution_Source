package ethos.runehub.skill.artisan.fletching.bows;

import ethos.runehub.entity.item.GameItem;
import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;
@StoredObject(tableName = "unstrung_bows")
public class UnstrungBow {

    public int getBowId() {
        return bowId;
    }

    public GameItem[] getMaterials() {
        return materials;
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

    public UnstrungBow(int bowId, GameItem[] materials, boolean members, int baseXp, int levelRequirement) {
        this.bowId = bowId;
        this.materials = materials;
        this.members = members;
        this.baseXp = baseXp;
        this.levelRequirement = levelRequirement;
    }

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int bowId;
    @StoredValue(type = SqlDataType.JSON)
    private final GameItem[] materials;
    @StoredValue(type = SqlDataType.BOOLEAN)
    private final boolean members;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int baseXp;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int levelRequirement;
}

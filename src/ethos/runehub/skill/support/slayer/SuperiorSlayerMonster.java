package ethos.runehub.skill.support.slayer;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;
@StoredObject(tableName = "superior_slayer_monster")
public class SuperiorSlayerMonster {

    public int getMobId() {
        return mobId;
    }

    public String getSlayerCategory() {
        return slayerCategory;
    }

    public int[] getExclusiveMobIds() {
        return exclusiveMobIds;
    }

    public SuperiorSlayerMonster(int mobId, String slayerCategory, int[] exclusiveMobIds) {
        this.mobId = mobId;
        this.slayerCategory = slayerCategory;
        this.exclusiveMobIds = exclusiveMobIds;
    }

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int mobId;
    @StoredValue(type = SqlDataType.TEXT)
    private final String slayerCategory;
    @StoredValue(type = SqlDataType.JSON)
    private final int[] exclusiveMobIds;
}

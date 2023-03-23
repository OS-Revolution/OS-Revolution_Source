package ethos.runehub.skill.support.slayer;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;

@StoredObject(tableName = "slayer_assignments")
public class SlayerAssignment {

    public int getSlayerLevel() {
        return slayerLevel;
    }

//    public int[] getLocation() {
//        return location;
//    }

    public String getCategory() {
        return category;
    }

    public long getId() {
        return id;
    }

    public boolean isMembers() {
        return members;
    }

    public int[] getRequiredMobIds() {
        return requiredMobIds;
    }

    public SlayerAssignment(long id, String category, int slayerLevel, boolean members, int[] requiredMobIds) {
        this.id = id;
        this.category = category;
        this.slayerLevel = slayerLevel;
        this.members = members;
        this.requiredMobIds = requiredMobIds;
//        this.location = location;
    }

    @StoredValue(type = SqlDataType.BIGINT, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final long id;
    @StoredValue(type = SqlDataType.TEXT)
    private final String category;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int slayerLevel;
    @StoredValue(type = SqlDataType.BOOLEAN)
    private final boolean members;
    @StoredValue(type = SqlDataType.JSON)
    private final int[] requiredMobIds;
//    @StoredValue(type = SqlDataType.JSON)
//    private final int[] location;

}

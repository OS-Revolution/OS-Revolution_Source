package ethos.runehub.entity.mob;

import ethos.runehub.RunehubUtils;
import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;
@StoredObject(tableName = "mob_spawns")
public class MobSpawn {

    public int getMobId() {
        return mobId;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public int getFace() {
        return face;
    }

    public boolean isRoam() {
        return roam;
    }

    public long getSpawnId() {
        return spawnId;
    }

    public int getRegionId() {
        return RunehubUtils.getRegionId(x,y);
    }

    public MobSpawn(long spawnId, int mobId, int x, int y, int z, int face, boolean roam) {
        this.spawnId = spawnId;
        this.mobId = mobId;
        this.x = x;
        this.y = y;
        this.z = z;
        this.face = face;
        this.roam = roam;
    }

    @StoredValue(type = SqlDataType.BIGINT, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final long spawnId;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int mobId;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int x;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int y;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int z;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int face;
    @StoredValue(type = SqlDataType.BOOLEAN)
    private final boolean roam;
}

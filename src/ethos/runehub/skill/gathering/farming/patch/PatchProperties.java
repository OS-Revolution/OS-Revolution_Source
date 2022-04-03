package ethos.runehub.skill.gathering.farming.patch;


import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;
import org.runehub.api.util.math.geometry.impl.IrregularPolygon;
import org.runehub.api.util.math.geometry.impl.Polygon;

@StoredObject(tableName = "patch_properties")
public class PatchProperties
{

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int id;
    @StoredValue(type = SqlDataType.BLOB)
    private final Polygon boundary;
    @StoredValue(type = SqlDataType.BIGINT)
    private final long growthCycleMS;
    @StoredValue(type = SqlDataType.BOOLEAN)
    private final boolean waterable;

    public PatchProperties(Integer id, Polygon boundary, Long growthCycle, Boolean waterable) {
        this.id = id;
        this.boundary = boundary;
        this.growthCycleMS = growthCycle;
        this.waterable = waterable;
    }

    public PatchProperties(Integer id, IrregularPolygon boundary, Long growthCycle, Boolean waterable) {
        this.id = id;
        this.boundary = boundary;
        this.growthCycleMS = growthCycle;
        this.waterable = waterable;
    }

    public boolean isWaterable() {
        return waterable;
    }

    public int getId() {
        return id;
    }

    public long getGrowthCycleMS() {
        return growthCycleMS;
    }

    public Polygon getBoundary() {
        return boundary;
    }

}

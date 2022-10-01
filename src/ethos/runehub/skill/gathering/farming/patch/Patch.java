package ethos.runehub.skill.gathering.farming.patch;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;
import org.runehub.api.util.math.geometry.impl.IrregularPolygon;

@StoredObject(tableName = "patch")
public class Patch {

    public int getId() {
        return id;
    }

    public IrregularPolygon getBoundary() {
        return boundary;
    }

    public int getPatchTypeId() {
        return patchTypeId;
    }

    public PatchType getPatchType() {
        return PatchType.values()[patchTypeId];
    }

    public Patch(int id, IrregularPolygon boundary, int patchTypeId) {
        this.id = id;
        this.boundary = boundary;
        this.patchTypeId = patchTypeId;
    }

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int id;
    @StoredValue(type = SqlDataType.JSON)
    private final IrregularPolygon boundary;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int patchTypeId;
}

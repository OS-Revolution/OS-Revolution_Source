package ethos.runehub.entity.node.door;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;

@StoredObject(tableName = "door_definitions")
public class DoorDefinition {

    public int getObjectId() {
        return objectId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setType(DoorType type) {
        this.type = type.ordinal();
    }

    public int getHingeType() {
        return hingeType;
    }

    public void setHingeType(int hingeType) {
        this.hingeType = hingeType;
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

    public DoorDefinition(int objectId) {
        this(objectId, DoorType.PROP_ROTATE,DoorHingeType.LEFT);
    }

    public DoorDefinition(int objectId, DoorType type, DoorHingeType hingeType) {
        this(objectId, type.ordinal(),hingeType.ordinal(),0,0,0,0);
    }

    public DoorDefinition(int objectId, int type, int hingeType, int x,int y, int z,int face) {
        this.objectId = objectId;
        this.type = type;
        this.hingeType = hingeType;
        this.x = x;
        this.y = y;
        this.z = z;
        this.face = face;
    }

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.NOT_NULL)
    private int type;
    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.NOT_NULL)
    private int hingeType;
    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int objectId;
    private final int x,y,z,face;
}

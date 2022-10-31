package ethos.runehub.content.rift;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;
import org.runehub.api.util.math.geometry.impl.Rectangle;

@StoredObject(tableName = "floor")
public class RiftFloor {

    public int getFloorId() {
        return floorId;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public int[] getEliteMobPool() {
        return eliteMobPool;
    }

    public int[] getTrashMobPool() {
        return trashMobPool;
    }

    public RiftFloor(int floorId, int startX, int startY, Rectangle boundingBox, int[] eliteMobPool, int[] trashMobPool) {
        this.floorId = floorId;
        this.startX = startX;
        this.startY = startY;
        this.boundingBox = boundingBox;
        this.eliteMobPool = eliteMobPool;
        this.trashMobPool = trashMobPool;
    }

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int floorId;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int startX;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int startY;
    @StoredValue(type = SqlDataType.JSON)
    private final Rectangle boundingBox;
    @StoredValue(type = SqlDataType.JSON)
    private final int[] eliteMobPool;
    @StoredValue(type = SqlDataType.JSON)
    private final int[] trashMobPool;
}

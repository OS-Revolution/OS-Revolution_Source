package ethos.runehub.entity.item.impl;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;
import org.runehub.api.util.math.geometry.impl.Rectangle;
@StoredObject(tableName = "teleport_tablets")
public class TeleportTablet {

    public int getItemId() {
        return itemId;
    }

    public boolean isMembers() {
        return members;
    }

    public Rectangle getTeleportArea() {
        return teleportArea;
    }

    public TeleportTablet(int itemId, boolean members, Rectangle teleportArea) {
        this.itemId = itemId;
        this.members = members;
        this.teleportArea = teleportArea;
    }

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int itemId;
    @StoredValue(type = SqlDataType.BOOLEAN)
    private final boolean members;
    @StoredValue(type = SqlDataType.JSON)
    private final Rectangle teleportArea;
}

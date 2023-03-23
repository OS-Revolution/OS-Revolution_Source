package ethos.runehub.entity.item.equipment;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;
@StoredObject(tableName = "projectile")
public class Projectile {

    public int getItemId() {
        return itemId;
    }

    public int getStartGfx() {
        return startGfx;
    }

    public int getProjectileGfx() {
        return projectileGfx;
    }

    public int getEndGfx() {
        return endGfx;
    }

    public Projectile(int itemId, int startGfx, int projectileGfx, int endGfx) {
        this.itemId = itemId;
        this.startGfx = startGfx;
        this.projectileGfx = projectileGfx;
        this.endGfx = endGfx;
    }

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int itemId;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int startGfx;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int projectileGfx;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int endGfx;
}

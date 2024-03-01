package ethos.runehub.skill.artisan.crafting.jewellery;

import ethos.runehub.entity.item.GameItem;
import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;

@StoredObject(tableName = "jewellery")
public class Jewellery {

    public int getProductId() {
        return productId;
    }

    public int getMouldId() {
        return mouldId;
    }

    public int getLevel() {
        return level;
    }

    public int getXp() {
        return xp;
    }

    public boolean isMembers() {
        return members;
    }

    public GameItem[] getMaterials() {
        return materials;
    }

    public Jewellery(int productId, int mouldId, int level, int xp, boolean members, GameItem[] materials) {
        this.productId = productId;
        this.mouldId = mouldId;
        this.level = level;
        this.xp = xp;
        this.members = members;
        this.materials = materials;
    }

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int productId;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int mouldId;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int level;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int xp;
    @StoredValue(type = SqlDataType.BOOLEAN)
    private final boolean members;
    @StoredValue(type = SqlDataType.JSON)
    private final GameItem[] materials;
}
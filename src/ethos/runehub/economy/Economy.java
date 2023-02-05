package ethos.runehub.economy;

import ethos.runehub.entity.item.GameItem;
import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;
@StoredObject(tableName = "economy")
public class Economy {

    public int getId() {
        return id;
    }


    public GameItem[] getItems() {
        return items;
    }

    public Economy(int id, GameItem[] items) {
        this.id = id;
        this.items = items;
    }

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int id;
    @StoredValue(type = SqlDataType.JSON)
    private final GameItem[] items;
}

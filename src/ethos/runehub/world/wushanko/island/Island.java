package ethos.runehub.world.wushanko.island;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;

@StoredObject(tableName = "islands")
public class Island {

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Island(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int id;
    @StoredValue(type = SqlDataType.TEXT)
    private final String name;
}
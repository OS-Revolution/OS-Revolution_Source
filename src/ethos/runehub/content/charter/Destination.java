package ethos.runehub.content.charter;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;
import org.runehub.api.util.math.geometry.impl.Rectangle;
@StoredObject(tableName = "destination")
public class Destination {

    public long getId() {
        return id;
    }

    public Rectangle getArrivalArea() {
        return arrivalArea;
    }

    public String getName() {
        return name;
    }

    public Destination(long id, Rectangle arrivalArea, String name) {
        this.id = id;
        this.arrivalArea = arrivalArea;
        this.name = name;
    }

    @StoredValue(type = SqlDataType.BIGINT, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final long id;
    @StoredValue(type = SqlDataType.JSON)
    private final Rectangle arrivalArea;
    @StoredValue(type = SqlDataType.TEXT)
    private final String name;
}

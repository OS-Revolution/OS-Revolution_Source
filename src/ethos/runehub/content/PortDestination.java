package ethos.runehub.content;

import ethos.model.items.GameItem;
import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;
@StoredObject(tableName = "port_destinations")
public class PortDestination {

    public int getId() {
        return id;
    }

    public int getDropOffX() {
        return dropOffX;
    }

    public int getDropOffY() {
        return dropOffY;
    }

    public GameItem[] getCost() {
        return cost;
    }

    public boolean isMembers() {
        return members;
    }

    public String getDescription() {
        return description;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public PortDestination(int id, int dropOffX, int dropOffY, GameItem[] cost, boolean members, String name, String description) {
        this.id = id;
        this.dropOffX = dropOffX;
        this.dropOffY = dropOffY;
        this.cost = cost;
        this.members = members;
        this.destinationName = name;
        this.description = description;
    }

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int id;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int dropOffX;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int dropOffY;
    @StoredValue(type = SqlDataType.JSON)
    private final GameItem[] cost;
    @StoredValue(type = SqlDataType.BOOLEAN)
    private final boolean members;
    @StoredValue(type = SqlDataType.TEXT)
    private final String destinationName;
    @StoredValue(type = SqlDataType.TEXT)
    private final String description;
}

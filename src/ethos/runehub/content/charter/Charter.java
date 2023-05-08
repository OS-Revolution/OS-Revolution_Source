package ethos.runehub.content.charter;

import ethos.model.items.GameItem;
import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;

@StoredObject(tableName = "charter")
public class Charter {

    public long getId() {
        return id;
    }

    public long getDestinationId() {
        return destinationId;
    }

    public GameItem[] getFee() {
        return fee;
    }

    public boolean isMembers() {
        return members;
    }

    public Charter(long id, long destinationId, GameItem[] fee, boolean members) {
        this.id = id;
        this.destinationId = destinationId;
        this.fee = fee;
        this.members = members;
    }

    @StoredValue(type = SqlDataType.BIGINT, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final long id;
    @StoredValue(type = SqlDataType.BIGINT)
    private final long destinationId;
    @StoredValue(type = SqlDataType.JSON)
    private final GameItem[] fee;
    @StoredValue(type = SqlDataType.BOOLEAN)
    private final boolean members;

}

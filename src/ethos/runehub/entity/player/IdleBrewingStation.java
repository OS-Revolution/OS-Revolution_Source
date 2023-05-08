package ethos.runehub.entity.player;

import ethos.runehub.entity.item.GameItem;
import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;
import org.runehub.api.model.math.impl.AdjustableInteger;

import java.util.List;
import java.util.Map;

@StoredObject(tableName = "idle_station_brewing")
public class IdleBrewingStation  {

    public long getPlayerId() {
        return playerId;
    }

    public List<Long> getItems() {
        return items;
    }

    public IdleBrewingStation(long playerId, List<Long> items) {
       this.playerId = playerId;
       this.items = items;
    }

    @StoredValue(type = SqlDataType.BIGINT, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final long playerId;
    @StoredValue(type = SqlDataType.BLOB)
    private final List<Long> items;
}

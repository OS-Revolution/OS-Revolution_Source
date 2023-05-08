package ethos.runehub.entity.player;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredValue;
import org.runehub.api.model.math.impl.AdjustableInteger;

import java.util.Map;

public class IdleStation {

    public long getPlayerId() {
        return playerId;
    }

    public Map<Integer, AdjustableInteger> getItems() {
        return items;
    }


    public IdleStation(long playerId, Map<Integer,AdjustableInteger> items ) {
        this.playerId = playerId;
        this.items = items;
    }

    @StoredValue(type = SqlDataType.BIGINT, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final long playerId;
    @StoredValue(type = SqlDataType.JSON)
    private final Map<Integer,AdjustableInteger> items;
}

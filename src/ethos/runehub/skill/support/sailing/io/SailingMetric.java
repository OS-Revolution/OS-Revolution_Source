package ethos.runehub.skill.support.sailing.io;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;
@StoredObject(tableName = "sailing_metrics")
public class SailingMetric {

    public long getId() {
        return id;
    }

    public long getVoyageId() {
        return voyageId;
    }

    public long getShipId() {
        return shipId;
    }

    public long getPlayerId() {
        return playerId;
    }

    public long[] getBoughtTradeGoods() {
        return boughtTradeGoods;
    }

    public long[] getSoldTradeGoods() {
        return soldTradeGoods;
    }

    public SailingMetric(long id, long voyageId, long shipId, long playerId, long[] boughtTradeGoods, long[] soldTradeGoods) {
        this.id = id;
        this.voyageId = voyageId;
        this.shipId = shipId;
        this.playerId = playerId;
        this.boughtTradeGoods = boughtTradeGoods;
        this.soldTradeGoods = soldTradeGoods;
    }

    @StoredValue(type = SqlDataType.BIGINT, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final long id;
    @StoredValue(type = SqlDataType.BIGINT)
    private final long voyageId;
    @StoredValue(type = SqlDataType.BIGINT)
    private final long shipId;
    @StoredValue(type = SqlDataType.BIGINT)
    private final long playerId;
    @StoredValue(type = SqlDataType.JSON)
    private final long[] boughtTradeGoods;
    @StoredValue(type = SqlDataType.JSON)
    private final long[] soldTradeGoods;
}

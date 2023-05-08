package ethos.runehub.skill.support.sailing;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;
@StoredObject(tableName = "sailing_save_data")
public class SailingSaveData {

    public long getPlayerId() {
        return playerId;
    }

    public long[] getShipSlot() {
        return shipSlot;
    }

    public int[] getAvailabeVoyages() {
        return availabeVoyages;
    }

    public long[] getShipSlotTimestamp() {
        return shipSlotTimestamp;
    }

    public SailingSaveData(long playerId, long[] shipSlot, int[] availabeVoyages, long[] shipSlotTimestamp) {
        this.playerId = playerId;
        this.shipSlot = shipSlot;
        this.availabeVoyages = availabeVoyages;
        this.shipSlotTimestamp = shipSlotTimestamp;
    }

    @StoredValue(type = SqlDataType.BIGINT, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final long playerId;
    @StoredValue(type = SqlDataType.JSON)
    private final long[] shipSlot;
    @StoredValue(type = SqlDataType.JSON)
    private final int[] availabeVoyages;
    @StoredValue(type = SqlDataType.JSON)
    private final long[] shipSlotTimestamp;
}

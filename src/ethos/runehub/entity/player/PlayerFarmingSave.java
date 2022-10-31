package ethos.runehub.entity.player;

import ethos.runehub.skill.gathering.farming.FarmingConfig;
import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;

import java.util.Arrays;
import java.util.List;

@StoredObject(tableName = "farming")
public class PlayerFarmingSave {

    public long getPlayerId() {
        return playerId;
    }

    public int[][] getConfigMap() {
        return configMap;
    }


    public PlayerFarmingSave(long playerId, int[][] configMap) {
        this.playerId = playerId;
        this.configMap = configMap;
    }

    @StoredValue(type = SqlDataType.BIGINT, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final long playerId;
    @StoredValue(type = SqlDataType.JSON)
    private final int[][] configMap;
}

package ethos.runehub.skill.gathering.farming.foraging;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;

@StoredObject(tableName = "seeds")
public class ForagingSeed {

    public int getSeedId() {
        return seedId;
    }

    public int getHerbNodeId() {
        return herbNodeId;
    }

    public ForagingSeed( int herbNodeId,int seedId) {
        this.seedId = seedId;
        this.herbNodeId = herbNodeId;
    }

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int herbNodeId;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int seedId;
}

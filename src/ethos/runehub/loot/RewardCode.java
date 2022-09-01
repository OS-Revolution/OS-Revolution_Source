package ethos.runehub.loot;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;

@StoredObject(tableName = "generated_codes")
public class RewardCode {

    public long getUuid() {
        return uuid;
    }

    public long getDurationMs() {
        return durationMs;
    }

    public String getUserCode() {
        return userCode;
    }

    public long getCreationTimestamp() {
        return creationTimestamp;
    }

    public int getRewardItemId() {
        return rewardItemId;
    }

    public int getRewardItemAmount() {
        return rewardItemAmount;
    }

    public RewardCode(long uuid, String userCode, long durationMs, long creationTimestamp, int rewardItemId, int rewardItemAmount) {
        this.uuid = uuid;
        this.userCode = userCode;
        this.durationMs = durationMs;
        this.creationTimestamp = creationTimestamp;
        this.rewardItemId = rewardItemId;
        this.rewardItemAmount = rewardItemAmount;
    }

    public RewardCode(long uuid, String userCode, long durationMs, long creationTimestamp) {
        this.uuid = uuid;
        this.userCode = userCode;
        this.durationMs = durationMs;
        this.creationTimestamp = creationTimestamp;
        this.rewardItemId = 85;
        this.rewardItemAmount = 1;
    }

    @StoredValue(type = SqlDataType.BIGINT, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final long uuid;
    @StoredValue(type = SqlDataType.TEXT)
    private final String userCode;
    @StoredValue(type = SqlDataType.BIGINT)
    private final long durationMs;
    @StoredValue(type = SqlDataType.BIGINT)
    private final long creationTimestamp;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int rewardItemId;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int rewardItemAmount;
}

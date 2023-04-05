package ethos.runehub.content.journey;

import ethos.runehub.entity.item.GameItem;
import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;

@StoredObject(tableName = "journey_steps")
public class JourneyStep {

    public long getId() {
        return id;
    }

    public int[] getValidChunks() {
        return validChunks;
    }

    public long getRegionId() {
        return regionId;
    }

    public String getDescription() {
        return description;
    }

    public GameItem[] getCompletionRewards() {
        return completionRewards;
    }

    public int getRequiredProgress() {
        return requiredProgress;
    }

    public int getProgressIncrement() {
        return progressIncrement;
    }

    public int getStepType() {
        return stepType;
    }

    public int[] getTargetId() {
        return targetId;
    }

    public JourneyStep(long id, int[] validChunks, long regionId, String description, GameItem[] completionRewards, int requiredProgress, int progressIncrement,
                       int[] targetId, int stepType) {
        this.id = id;
        this.validChunks = validChunks;
        this.regionId = regionId;
        this.description = description;
        this.completionRewards = completionRewards;
        this.requiredProgress = requiredProgress;
        this.progressIncrement = progressIncrement;
        this.targetId = targetId;
        this.stepType = stepType;
    }

    @StoredValue(type = SqlDataType.BIGINT, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final long id;
    @StoredValue(type = SqlDataType.JSON)
    private final int[] validChunks;
    @StoredValue(type = SqlDataType.BIGINT)
    private final long regionId;
    @StoredValue(type = SqlDataType.TEXT)
    private final String description;
    @StoredValue(type = SqlDataType.JSON)
    private final GameItem[] completionRewards;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int requiredProgress;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int progressIncrement;
    @StoredValue(type = SqlDataType.JSON)
    private final int[] targetId;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int stepType;
}

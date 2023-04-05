package ethos.runehub.content.achievement;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;
@StoredObject(tableName = "achievement")
public class Achievement {

    public long getId() {
        return id;
    }

    public int getDifficulty() {
        return difficulty;
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

    public Achievement(long id, int difficulty, int[] validChunks, long regionId, String description) {
        this.id = id;
        this.difficulty = difficulty;
        this.validChunks = validChunks;
        this.regionId = regionId;
        this.description = description;
    }

    @StoredValue(type = SqlDataType.BIGINT, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final long id;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int difficulty;
    @StoredValue(type = SqlDataType.JSON)
    private final int[] validChunks;
    @StoredValue(type = SqlDataType.BIGINT)
    private final long regionId;
    @StoredValue(type = SqlDataType.TEXT)
    private final String description;
}

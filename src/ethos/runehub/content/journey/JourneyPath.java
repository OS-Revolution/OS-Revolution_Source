package ethos.runehub.content.journey;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;

@StoredObject(tableName = "path")
public class JourneyPath {

    public int getId() {
        return id;
    }

    public int[] getJourney() {
        return journey;
    }

    public int[] getRequirement() {
        return requirement;
    }

    public String getName() {
        return name;
    }

    public JourneyPath(int id, int[] journey, int[] requirement, String name) {
        this.id = id;
        this.journey = journey;
        this.requirement = requirement;
        this.name = name;
    }

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int id;
    @StoredValue(type = SqlDataType.JSON)
    private final int[] journey;
    @StoredValue(type = SqlDataType.JSON)
    private final int[] requirement;
    @StoredValue(type = SqlDataType.TEXT)
    private final String name;
}

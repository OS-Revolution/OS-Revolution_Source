package ethos.runehub.content.journey;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;

@StoredObject(tableName = "journey")
public class Journey {

    public int getId() {
        return id;
    }

    public Long[] getSteps() {
        return steps;
    }

    public Integer[] getRequiredJourney() {
        return requiredJourney;
    }

    public String getName() {
        return name;
    }

    public Journey(int id, Long[] steps, Integer[] requiredJourney, String name) {
        this.id = id;
        this.steps = steps;
        this.requiredJourney = requiredJourney;
        this.name = name;
    }

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int id;
    @StoredValue(type = SqlDataType.JSON)
    private final Long[] steps;
    @StoredValue(type = SqlDataType.JSON)
    private final Integer[] requiredJourney;
    @StoredValue(type = SqlDataType.TEXT)
    private final String name;
}

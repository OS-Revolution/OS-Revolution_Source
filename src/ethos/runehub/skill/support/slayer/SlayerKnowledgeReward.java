package ethos.runehub.skill.support.slayer;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;
@StoredObject(tableName = "slayer_knowledge_rewards")
public class SlayerKnowledgeReward {

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public boolean isMembers() {
        return members;
    }

    public String getDescription() {
        return description;
    }

    public SlayerKnowledgeReward(int id, String name, int cost, String description, boolean members) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.members = members;
    }

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int id;
    @StoredValue(type = SqlDataType.TEXT)
    private final String name;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int cost;
    @StoredValue(type = SqlDataType.TEXT)
    private final String description;
    @StoredValue(type = SqlDataType.BOOLEAN)
    private final boolean members;
}

package ethos.runehub.skill.node.impl.gatherable;

import ethos.runehub.skill.node.impl.SkillNode;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;

@StoredObject(tableName = "gathering_nodes")
public class GatheringNode extends SkillNode {

    public long getGatherableItemTableId() {
        return gatherableItemTableId;
    }

    public int getMinRoll() {
        return minRoll;
    }

    public int getMaxRoll() {
        return maxRoll;
    }

    public GatheringNode(int id, int levelRequirement, int interactionExperience, long gatherableItemTableId, int gatherMinRoll, int skillId, int maxRoll) {
        super(id, levelRequirement, interactionExperience,skillId);
        this.gatherableItemTableId = gatherableItemTableId;
        this.minRoll = gatherMinRoll;
        this.maxRoll = maxRoll;
    }

    @StoredValue(type = SqlDataType.BIGINT)
    private final long gatherableItemTableId;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int minRoll;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int maxRoll;
}

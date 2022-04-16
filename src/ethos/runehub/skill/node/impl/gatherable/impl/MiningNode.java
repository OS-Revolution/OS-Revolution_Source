package ethos.runehub.skill.node.impl.gatherable.impl;

import ethos.runehub.skill.node.impl.gatherable.GatheringNode;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;

@StoredObject(tableName = "mining_nodes")
public class MiningNode extends GatheringNode {

    public int getMiningCycleTicks() {
        return miningCycleTicks;
    }

    public MiningNode(int id, int levelRequirement, int interactionExperience, int skillId, long tableId, int harvestChance,int maxRoll,int miningCycleTicks) {
        super(id, levelRequirement, interactionExperience, tableId, harvestChance, skillId,maxRoll);
        this.miningCycleTicks = miningCycleTicks;
    }

    @StoredValue(type = SqlDataType.INTEGER)
    private final int miningCycleTicks;
}

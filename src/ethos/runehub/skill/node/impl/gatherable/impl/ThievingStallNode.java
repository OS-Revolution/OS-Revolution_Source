package ethos.runehub.skill.node.impl.gatherable.impl;

import ethos.runehub.skill.node.impl.gatherable.GatheringNode;
import org.runehub.api.io.data.StoredObject;

@StoredObject(tableName = "thieving_stall_nodes")
public class ThievingStallNode extends GatheringNode {

    public ThievingStallNode(int id, int levelRequirement, int interactionExperience, int skillId, long tableId, int minRoll, int maxRoll) {
        super(id, levelRequirement, interactionExperience, tableId, minRoll, skillId,maxRoll);
    }
}

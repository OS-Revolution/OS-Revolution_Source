package ethos.runehub.skill.node.impl.gatherable.impl;

import ethos.runehub.skill.node.impl.gatherable.GatheringNode;
import org.runehub.api.io.data.StoredObject;

@StoredObject(tableName = "foraging_nodes")
public class ForagingNode extends GatheringNode {

    public ForagingNode(int id, int levelRequirement, int interactionExperience, int skillId, long tableId, int harvestChance, int maxRoll) {
        super(id, levelRequirement, interactionExperience, tableId, harvestChance, skillId,maxRoll);
    }
}

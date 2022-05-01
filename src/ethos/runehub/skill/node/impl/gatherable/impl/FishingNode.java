package ethos.runehub.skill.node.impl.gatherable.impl;

import ethos.runehub.skill.node.impl.gatherable.GatheringNode;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;
import org.runehub.api.util.SkillDictionary;

@StoredObject(tableName = "fishing_nodes")
public class FishingNode extends GatheringNode {

    public int getBaitId() {
        return baitId;
    }

    public int getSpotId() {
        return spotId;
    }

    public int getToolId() {
        return toolId;
    }

    public FishingNode(int id, int levelRequirement, int interactionExperience, int skillId, long tableId, int harvestChance,int maxRoll, int baitId, int spotId, int toolId) {
        super(id, levelRequirement, interactionExperience, tableId, harvestChance, skillId,maxRoll);
        this.baitId = baitId;
        this.spotId = spotId;
        this.toolId = toolId;
    }

    @StoredValue(type = SqlDataType.INTEGER)
    private final int baitId;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int spotId;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int toolId;


}

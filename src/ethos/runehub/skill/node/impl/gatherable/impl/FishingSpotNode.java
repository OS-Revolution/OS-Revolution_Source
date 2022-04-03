package ethos.runehub.skill.node.impl.gatherable.impl;

import ethos.runehub.skill.node.impl.Node;
import ethos.runehub.skill.node.impl.SkillNode;
import ethos.runehub.skill.node.impl.gatherable.GatheringNode;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;

@StoredObject(tableName = "fishing_spot_nodes")
public class FishingSpotNode extends Node {


    public int getNpcId() {
        return npcId;
    }

    public int getToolTypeId() {
        return toolTypeId;
    }

    public FishingSpotNode(int id, int npcId, int toolTypeId) {
        super(id);
        this.npcId = npcId;
        this.toolTypeId = toolTypeId;
    }

    @StoredValue(type = SqlDataType.INTEGER)
    private final int npcId;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int toolTypeId;


}

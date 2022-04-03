package ethos.runehub.skill.node.impl;

import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;

@StoredObject(tableName = "renewable_nodes")
public class RenewableNode extends Node {

    public int getDepletedNodeId() {
        return depletedNodeId;
    }

    public int getRespawnTime() {
        return respawnTime;
    }

    public int getDepletionMinRoll() {
        return depletionMinRoll;
    }

    public RenewableNode(int id, int depletedNodeId, int respawnTime, int depletionMinRoll) {
        super(id);
        this.depletedNodeId = depletedNodeId;
        this.respawnTime = respawnTime;
        this.depletionMinRoll = depletionMinRoll;
    }

    @StoredValue(type = SqlDataType.INTEGER)
    private final int depletedNodeId;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int respawnTime;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int depletionMinRoll;
}

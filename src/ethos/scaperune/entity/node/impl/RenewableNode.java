package ethos.scaperune.entity.node.impl;

import ethos.scaperune.entity.node.Node;
import ethos.scaperune.skill.gathering.GatheringNode;

public class RenewableNode extends Node {

    public int getDepletedId() {
        return depletedId;
    }

    public int getDepletionTime() {
        return depletionTime;
    }

    public int getRespawnTime() {
        return respawnTime;
    }

    protected RenewableNode(int id, int depletedId, int respawnTime, int depletionTime) {
        super(id);
        this.depletedId = depletedId;
        this.respawnTime = respawnTime;
        this.depletionTime = depletionTime;
    }

    protected final int depletedId;
    protected final int respawnTime;
    protected final int depletionTime;
}

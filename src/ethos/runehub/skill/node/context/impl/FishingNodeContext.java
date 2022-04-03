package ethos.runehub.skill.node.context.impl;

import ethos.runehub.skill.node.impl.gatherable.impl.FishingNode;
import ethos.runehub.skill.node.impl.gatherable.impl.MiningNode;
import ethos.runehub.skill.node.io.FishingNodeLoader;
import ethos.runehub.skill.node.io.MiningNodeLoader;

public class FishingNodeContext extends GatheringNodeContext<FishingNode> {

    public FishingNodeContext(int nodeId, int x, int y, int z) {
        super(nodeId, x, y, z);
    }

    @Override
    public FishingNode getNode() {
        return FishingNodeLoader.getInstance().read(this.getNodeId());
    }
}

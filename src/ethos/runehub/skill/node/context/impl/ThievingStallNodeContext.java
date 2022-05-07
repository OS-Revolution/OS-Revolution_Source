package ethos.runehub.skill.node.context.impl;

import ethos.runehub.skill.node.impl.gatherable.impl.ThievingStallNode;
import ethos.runehub.skill.node.impl.gatherable.impl.WoodcuttingNode;
import ethos.runehub.skill.node.io.ThievingStallNodeLoader;
import ethos.runehub.skill.node.io.WoodcuttingNodeLoader;

public class ThievingStallNodeContext extends GatheringNodeContext<ThievingStallNode> {

    public ThievingStallNodeContext(int nodeId, int x, int y, int z) {
        super(nodeId, x, y, z);
    }

    @Override
    public ThievingStallNode getNode() {
        return ThievingStallNodeLoader.getInstance().read(this.getNodeId());
    }
}

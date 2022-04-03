package ethos.runehub.skill.node.context.impl;

import ethos.runehub.skill.node.impl.gatherable.impl.WoodcuttingNode;
import ethos.runehub.skill.node.io.WoodcuttingNodeLoader;

public class WoodcuttingNodeContext extends GatheringNodeContext<WoodcuttingNode> {

    public WoodcuttingNodeContext(int nodeId, int x, int y, int z) {
        super(nodeId, x, y, z);
    }

    @Override
    public WoodcuttingNode getNode() {
        return WoodcuttingNodeLoader.getInstance().read(this.getNodeId());
    }
}

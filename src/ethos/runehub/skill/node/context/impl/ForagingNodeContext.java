package ethos.runehub.skill.node.context.impl;

import ethos.runehub.skill.node.impl.gatherable.impl.ForagingNode;
import ethos.runehub.skill.node.io.ForagingNodeLoader;

public class ForagingNodeContext extends GatheringNodeContext<ForagingNode> {

    public ForagingNodeContext(int nodeId, int x, int y, int z) {
        super(nodeId, x, y, z);
    }

    @Override
    public ForagingNode getNode() {
        return ForagingNodeLoader.getInstance().read(this.getNodeId());
    }
}

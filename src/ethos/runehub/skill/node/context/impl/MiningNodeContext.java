package ethos.runehub.skill.node.context.impl;

import ethos.runehub.skill.node.impl.gatherable.impl.MiningNode;
import ethos.runehub.skill.node.io.MiningNodeLoader;

public class MiningNodeContext extends GatheringNodeContext<MiningNode> {

    public MiningNodeContext(int nodeId, int x, int y, int z) {
        super(nodeId, x, y, z);
    }

    @Override
    public MiningNode getNode() {
        return MiningNodeLoader.getInstance().read(this.getNodeId());
    }
}

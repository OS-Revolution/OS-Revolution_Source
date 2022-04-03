package ethos.runehub.skill.node.context.impl;

import ethos.runehub.skill.node.context.ActiveSkillNodeContext;
import ethos.runehub.skill.node.impl.gatherable.GatheringNode;

public abstract class GatheringNodeContext<T extends GatheringNode> extends ActiveSkillNodeContext<T> {


    public GatheringNodeContext(int nodeId, int x, int y, int z) {
        super(nodeId, x, y, z);
    }
}

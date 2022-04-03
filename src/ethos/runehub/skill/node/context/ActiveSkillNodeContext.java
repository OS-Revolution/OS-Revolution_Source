package ethos.runehub.skill.node.context;

import ethos.runehub.skill.node.impl.Node;
import ethos.runehub.skill.node.impl.SkillNode;

public abstract class ActiveSkillNodeContext<N extends SkillNode> {

    public abstract N getNode();

    public int getX() {
        return x;
    }

    public int getNodeId() {
        return nodeId;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public ActiveSkillNodeContext(int nodeId, int x, int y, int z) {
        this.nodeId = nodeId;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    private final int x,y,z;
    private final int nodeId;
}

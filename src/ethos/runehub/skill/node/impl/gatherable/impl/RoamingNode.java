package ethos.runehub.skill.node.impl.gatherable.impl;

import ethos.runehub.skill.node.impl.Node;

public class RoamingNode extends Node {

    public RoamingNode(int id, int nodeX, int nodeY, int nodeZ, boolean roaming) {
        super(id);
        this.nodeX = nodeX;
        this.nodeY = nodeY;
        this.nodeZ = nodeZ;
        this.roaming = roaming;
    }

    private final int nodeX;
    private final int nodeY;
    private final int nodeZ;
    private final boolean roaming;
}

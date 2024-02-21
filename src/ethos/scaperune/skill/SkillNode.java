package ethos.scaperune.skill;

import ethos.scaperune.entity.node.Node;
import ethos.scaperune.entity.node.impl.RenewableNode;

public class SkillNode extends RenewableNode {

    public int getSkillId() {
        return skillId;
    }

    public int getInteractionLevelRequirement() {
        return interactionLevelRequirement;
    }

    public int getInteractionXP() {
        return interactionXP;
    }

    protected SkillNode(int id, int depletedId, int respawnTime, int depletionTime,
                        int skillId, int interactionXP, int interactionLevelRequirement) {
        super(id, depletedId, respawnTime, depletionTime);
        this.skillId = skillId;
        this.interactionXP = interactionXP;
        this.interactionLevelRequirement = interactionLevelRequirement;
    }

    private final int skillId;
    private final int interactionXP;
    private final int interactionLevelRequirement;
}

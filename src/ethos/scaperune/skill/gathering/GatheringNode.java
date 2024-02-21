package ethos.scaperune.skill.gathering;

import ethos.scaperune.skill.SkillNode;

public class GatheringNode extends SkillNode {

    public int getLow() {
        return low;
    }

    public int getHigh() {
        return high;
    }

    public long getTableId() {
        return tableId;
    }


    public GatheringNode(int id, int depletedId, int respawnTime, int depletionTime,
                            int skillId, int interactionXP, int interactionLevelRequirement,
                            int low, int high, long tableId) {
        super(id, depletedId, respawnTime, depletionTime, skillId, interactionXP, interactionLevelRequirement);
        this.low = low;
        this.high = high;
        this.tableId = tableId;
    }

    private final int low;
    private final int high;
    private final long tableId;
}

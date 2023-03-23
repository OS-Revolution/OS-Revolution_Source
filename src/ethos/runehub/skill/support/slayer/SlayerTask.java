package ethos.runehub.skill.support.slayer;

import org.runehub.api.model.math.Range;
import org.runehub.api.model.math.impl.IntegerRange;

public class SlayerTask {

    public long getAssignmentId() {
        return assignmentId;
    }

    public int getCombatLevelRequirement() {
        return combatLevelRequirement;
    }

    public int getWeight() {
        return weight;
    }

    public long[] getLocation() {
        return location;
    }

    public int getMinAssigned() {
        return minAssigned;
    }

    public int getMaxAssigned() {
        return maxAssigned;
    }

    public Range<Integer> getAssignedAmount() {
        return new IntegerRange(minAssigned,maxAssigned);
    }

    public int getUnlockId() {
        return unlockId;
    }

    public SlayerTask(long assignmentId, int combatLevelRequirement, int weight, long[] location, Range<Integer> assignedAmount, int unlockId) {
        this.assignmentId = assignmentId;
        this.combatLevelRequirement = combatLevelRequirement;
        this.weight = weight;
        this.location = location;
        this.minAssigned = assignedAmount.getMin();
        this.maxAssigned = assignedAmount.getMax();
        this.unlockId = unlockId;
    }

    private final long assignmentId;
    private final int combatLevelRequirement;
    private final int weight;
    private final long[] location;
    private final int minAssigned,maxAssigned;
    private final int unlockId;
}

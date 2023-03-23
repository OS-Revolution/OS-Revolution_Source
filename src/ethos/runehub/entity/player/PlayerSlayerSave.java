package ethos.runehub.entity.player;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;

@StoredObject(tableName = "slayer_save")
public class PlayerSlayerSave {

    public long getPlayerId() {
        return playerId;
    }

    public long getAssignmentId() {
        return assignmentId;
    }

    public int getAssignedAmount() {
        return assignedAmount;
    }

    public void setAssignedAmount(int assignedAmount) {
        this.assignedAmount = assignedAmount;
    }

    public void setAssignmentId(long assignmentId) {
        this.assignmentId = assignmentId;
    }

    public long[] getBlockedAssignments() {
        return blockedAssignments;
    }

    public long[] getPreferredAssignments() {
        return preferredAssignments;
    }

    public int getTaskMasterId() {
        return taskMasterId;
    }

    public int getTaskStreak() {
        return taskStreak;
    }

    public int getSlayerPoints() {
        return slayerPoints;
    }

    public void setSlayerPoints(int slayerPoints) {
        this.slayerPoints = slayerPoints;
    }

    public void setTaskMasterId(int taskMasterId) {
        this.taskMasterId = taskMasterId;
    }

    public void setTaskStreak(int taskStreak) {
        this.taskStreak = taskStreak;
    }

    public int getTasksCompleted() {
        return tasksCompleted;
    }

    public void setTasksCompleted(int tasksCompleted) {
        this.tasksCompleted = tasksCompleted;
    }

    public int getTotalAssignedAmount() {
        return totalAssignedAmount;
    }

    public void setTotalAssignedAmount(int totalAssignedAmount) {
        this.totalAssignedAmount = totalAssignedAmount;
    }

    public boolean[] getUnlockedKnowledge() {
        return unlockedKnowledge;
    }

    public void subtractSlayerPoints(int amount) {
        if (slayerPoints - amount >= 0) {
            slayerPoints -= amount;
        } else {
            throw new NullPointerException("Can not have negative value");
        }
    }

    public PlayerSlayerSave(long playerId, long assignmentId, int assignedAmount, long[] preferredAssignments, long[] blockedAssignments,
                            int taskStreak, int taskMasterId, int slayerPoints, int tasksCompleted, int totalAssignedAmount, boolean[] unlockedKnowledge) {
        this.playerId = playerId;
        this.assignmentId = assignmentId;
        this.assignedAmount = assignedAmount;
        this.preferredAssignments = preferredAssignments;
        this.blockedAssignments = blockedAssignments;
        this.taskStreak = taskStreak;
        this.taskMasterId = taskMasterId;
        this.slayerPoints = slayerPoints;
        this.tasksCompleted = tasksCompleted;
        this.totalAssignedAmount = totalAssignedAmount;
        this.unlockedKnowledge = unlockedKnowledge;
    }

    @StoredValue(type = SqlDataType.BIGINT, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final long playerId;
    @StoredValue(type = SqlDataType.BIGINT)
    private long assignmentId;
    @StoredValue(type = SqlDataType.INTEGER)
    private int assignedAmount;
    @StoredValue(type = SqlDataType.JSON)
    private final long[] preferredAssignments;
    @StoredValue(type = SqlDataType.JSON)
    private final long[] blockedAssignments;
    @StoredValue(type = SqlDataType.INTEGER)
    private int taskStreak;
    @StoredValue(type = SqlDataType.INTEGER)
    private int taskMasterId;
    @StoredValue(type = SqlDataType.INTEGER)
    private int slayerPoints;
    @StoredValue(type = SqlDataType.INTEGER)
    private int tasksCompleted;
    @StoredValue(type = SqlDataType.INTEGER)
    private int totalAssignedAmount;
    @StoredValue(type = SqlDataType.JSON)
    private final boolean[] unlockedKnowledge;
}

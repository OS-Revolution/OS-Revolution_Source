package ethos.runehub.skill.support.slayer.master;

import ethos.runehub.skill.support.slayer.SlayerAssignmentDAO;
import ethos.runehub.skill.support.slayer.SlayerTask;
import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;
import org.runehub.api.model.math.impl.IntegerRange;

@StoredObject(tableName = "slayer_master")
public class SlayerMaster {

    public int getNpcId() {
        return npcId;
    }

    public SlayerTask[] getTasks() {
        return tasks;
    }

    public int getPointsPerTask() {
        return pointsPerTask;
    }

    public int getPointsPer10thTask() {
        return pointsPer10thTask;
    }

    public int getPointsPer50thTask() {
        return pointsPer50thTask;
    }

    public int getPointsPer100thTask() {
        return pointsPer100thTask;
    }

    public int getPointsPer250thTask() {
        return pointsPer250thTask;
    }

    public int getPointsPer1000thTask() {
        return pointsPer1000thTask;
    }

    public boolean isMembers() {
        return members;
    }



    public SlayerMaster(int npcId, SlayerTask[] tasks, int pointsPerTask, int pointsPer10thTask, int pointsPer50thTask, int pointsPer100thTask, int pointsPer250thTask, int pointsPer1000thTask, boolean members) {
        this.npcId = npcId;
        this.tasks = tasks;
        this.pointsPerTask = pointsPerTask;
        this.pointsPer10thTask = pointsPer10thTask;
        this.pointsPer50thTask = pointsPer50thTask;
        this.pointsPer100thTask = pointsPer100thTask;
        this.pointsPer250thTask = pointsPer250thTask;
        this.pointsPer1000thTask = pointsPer1000thTask;
        this.members = members;
    }

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int npcId;
    @StoredValue(type = SqlDataType.JSON)
    private final SlayerTask[] tasks;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int pointsPerTask;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int pointsPer10thTask;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int pointsPer50thTask;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int pointsPer100thTask;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int pointsPer250thTask;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int pointsPer1000thTask;
    @StoredValue(type = SqlDataType.BOOLEAN)
    private final boolean members;
}

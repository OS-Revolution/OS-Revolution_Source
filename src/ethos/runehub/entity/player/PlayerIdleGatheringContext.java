package ethos.runehub.entity.player;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;

@StoredObject(tableName = "idle_gathering")
public class PlayerIdleGatheringContext {

    public int getToolId() {
        return toolId;
    }

    public void setToolId(int toolId) {
        this.toolId = toolId;
    }

    public long getLoginTimeMS() {
        return loginTimeMS;
    }

    public void setLoginTimeMS(long loginTimeMS) {
        this.loginTimeMS = loginTimeMS;
    }

    public long getLogoutTimeMS() {
        return logoutTimeMS;
    }

    public void setLogoutTimeMS(long logoutTimeMS) {
        this.logoutTimeMS = logoutTimeMS;
    }

    public int getNodeZ() {
        return nodeZ;
    }

    public void setNodeZ(int nodeZ) {
        this.nodeZ = nodeZ;
    }

    public int getNodeY() {
        return nodeY;
    }

    public void setNodeY(int nodeY) {
        this.nodeY = nodeY;
    }

    public int getNodeX() {
        return nodeX;
    }

    public void setNodeX(int nodeX) {
        this.nodeX = nodeX;
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public long getPlayerId() {
        return playerId;
    }

    public double getGainsRate() {
        return gainsRate;
    }

    public int getEfficiency() {
        return efficiency;
    }

    public int getPower() {
        return power;
    }

    public void setEfficiency(int efficiency) {
        this.efficiency = efficiency;
    }

    public void setGainsRate(double gainsRate) {
        this.gainsRate = gainsRate;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public PlayerIdleGatheringContext(long playerId) {
        this.playerId = playerId;
    }
    public PlayerIdleGatheringContext(long playerId, int toolId, long loginTimeMS, long logoutTimeMS, int nodeZ, int nodeY, int nodeX, int nodeId, int skillId) {
        this.playerId = playerId;
        this.toolId = toolId;
        this.loginTimeMS = loginTimeMS;
        this.logoutTimeMS = logoutTimeMS;
        this.nodeZ = nodeZ;
        this.nodeY = nodeY;
        this.nodeX = nodeX;
        this.nodeId = nodeId;
        this.skillId = skillId;
    }

    public PlayerIdleGatheringContext(int toolId, long loginTimeMS, long logoutTimeMS, int nodeZ, int nodeY, int nodeX, int nodeId,
                                      int skillId, int power, int efficiency, double gainsRate,long playerId) {
        this.playerId = playerId;
        this.toolId = toolId;
        this.loginTimeMS = loginTimeMS;
        this.logoutTimeMS = logoutTimeMS;
        this.nodeZ = nodeZ;
        this.nodeY = nodeY;
        this.nodeX = nodeX;
        this.nodeId = nodeId;
        this.skillId = skillId;
    }

    @StoredValue(type = SqlDataType.INTEGER)
    private int toolId;
    @StoredValue(type = SqlDataType.BIGINT)
    private long loginTimeMS;
    @StoredValue(type = SqlDataType.BIGINT)
    private long logoutTimeMS;
    @StoredValue(type = SqlDataType.INTEGER)
    private int nodeZ;
    @StoredValue(type = SqlDataType.INTEGER)
    private int nodeY;
    @StoredValue(type = SqlDataType.INTEGER)
    private int nodeX;
    @StoredValue(type = SqlDataType.INTEGER)
    private int nodeId;
    @StoredValue(type = SqlDataType.INTEGER)
    private int skillId;
    @StoredValue(type = SqlDataType.INTEGER)
    private int power;
    @StoredValue(type = SqlDataType.INTEGER)
    private int efficiency;
    @StoredValue(type = SqlDataType.DOUBLE)
    private double gainsRate;
    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final long playerId;

}

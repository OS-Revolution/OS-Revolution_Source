package ethos.runehub.skill.support.thieving;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;
@StoredObject(tableName = "pickpocket_mobs")
public class Pickpocket {

    public int getNpcId() {
        return npcId;
    }

    public int getLevel() {
        return level;
    }

    public int getXp() {
        return xp;
    }

    public int getStunDamageMin() {
        return stunDamageMin;
    }

    public int getStunDamageMax() {
        return stunDamageMax;
    }

    public int getStunDuration() {
        return stunDuration;
    }

    public int getSuccessMin() {
        return successMin;
    }

    public int getSuccessMax() {
        return successMax;
    }

    public boolean isMembers() {
        return members;
    }

    public long getLootTableId() {
        return lootTableId;
    }

    public Pickpocket(int npcId, int level, int xp, int stunDamageMin, int stunDamageMax, int stunDuration, int successMin, int successMax, boolean members, long lootTableId) {
        this.npcId = npcId;
        this.level = level;
        this.xp = xp;
        this.stunDamageMin = stunDamageMin;
        this.stunDamageMax = stunDamageMax;
        this.stunDuration = stunDuration;
        this.successMin = successMin;
        this.successMax = successMax;
        this.members = members;
        this.lootTableId = lootTableId;
    }

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int npcId;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int level;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int xp;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int stunDamageMin;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int stunDamageMax;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int stunDuration;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int successMin;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int successMax;
    @StoredValue(type = SqlDataType.BOOLEAN)
    private final boolean members;
    @StoredValue(type = SqlDataType.BIGINT)
    private final long lootTableId;
}

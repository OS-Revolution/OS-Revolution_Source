package ethos.runehub.skill.gathering.tool;

import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;

@StoredObject(tableName = "gathering_tools")
public class GatheringTool {

    public float getXpGainMultiplier() {
        return xpGainMultiplier;
    }

    public int getBaseEfficiency() {
        return baseEfficiency;
    }

    public double getBasePower() {
        return basePower;
    }

    public int getItemId() {
        return itemId;
    }

    public int getLevelRequired() {
        return levelRequired;
    }

    public int getSkillId() {
        return skillId;
    }

    public int getAnimationId() {
        return animationId;
    }

    public GatheringTool(int itemId, int levelRequired, int skillId, double basePower, int baseEfficiency, float xpGainMultiplier, int animationId) {
        this.itemId = itemId;
        this.levelRequired = levelRequired;
        this.skillId = skillId;
        this.basePower = basePower;
        this.baseEfficiency = baseEfficiency;
        this.xpGainMultiplier = xpGainMultiplier;
        this.animationId = animationId;
    }

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int itemId;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int levelRequired;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int skillId;
    @StoredValue(type = SqlDataType.DOUBLE)
    private final double basePower;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int baseEfficiency;
    @StoredValue(type = SqlDataType.FLOAT)
    private final float xpGainMultiplier;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int animationId;
}

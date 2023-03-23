package ethos.runehub.skill;

import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredValue;

public class SkillItem {

    public boolean isMembers() {
        return members;
    }

    public int getBaseXp() {
        return baseXp;
    }

    public int getLevelRequirement() {
        return levelRequirement;
    }

    public SkillItem(boolean members, int baseXp, int levelRequirement) {
        this.members = members;
        this.baseXp = baseXp;
        this.levelRequirement = levelRequirement;
    }

    @StoredValue(type = SqlDataType.BOOLEAN)
    private final boolean members;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int baseXp;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int levelRequirement;
}

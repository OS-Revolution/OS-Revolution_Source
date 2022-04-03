package ethos.runehub.skill;

import java.util.List;

public class SkillActionContext {

    public int getSkillId() {
        return skillId;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public int getToolId() {
        return toolId;
    }

    public void setToolId(int toolId) {
        this.toolId = toolId;
    }

    public SkillActionContext(
            int skillId,
            int skillLevel
    ) {
        this.skillId = skillId;
        this.skillLevel = skillLevel;
    }

    private final int skillLevel;
    private final int skillId;
    private int toolId;
}

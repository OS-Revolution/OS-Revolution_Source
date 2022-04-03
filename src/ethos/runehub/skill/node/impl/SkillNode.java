package ethos.runehub.skill.node.impl;

import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;

@StoredObject(tableName = "skill_nodes")
public class SkillNode extends Node {

    public int getInteractionExperience() {
        return interactionExperience;
    }

    public int getLevelRequirement() {
        return levelRequirement;
    }

    public int getSkillId() {
        return skillId;
    }

    public SkillNode(int id, int levelRequirement, int interactionExperience, int skillId) {
        super(id);
        this.levelRequirement = levelRequirement;
        this.interactionExperience = interactionExperience;
        this.skillId = skillId;
    }


    @StoredValue(type = SqlDataType.INTEGER)
    private final int levelRequirement;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int interactionExperience;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int skillId;

}

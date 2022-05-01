package ethos.runehub.skill.node.impl.artisan;

import ethos.runehub.skill.node.impl.SkillNode;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;
import org.runehub.api.util.SkillDictionary;

@StoredObject(tableName = "runecraft_altar_nodes")
public class RunecraftingAltarNode extends SkillNode {

    public int getEssence() {
        return essence;
    }

    public int getRuneId() {
        return runeId;
    }

    public RunecraftingAltarNode(int id, int levelRequirement, int interactionExperience, int skillId, int essence, int runeId) {
        super(id, levelRequirement, interactionExperience, skillId);
        this.essence = essence;
        this.runeId = runeId;
    }

    @StoredValue(type = SqlDataType.INTEGER)
    private final int essence;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int runeId;
}

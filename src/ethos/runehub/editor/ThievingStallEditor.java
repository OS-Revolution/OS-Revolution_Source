package ethos.runehub.editor;

import ethos.runehub.ConsoleEditor;
import ethos.runehub.skill.node.impl.RenewableNode;
import ethos.runehub.skill.node.impl.gatherable.impl.ThievingStallNode;
import ethos.runehub.skill.node.impl.gatherable.impl.WoodcuttingNode;
import ethos.runehub.skill.node.io.RenewableNodeDAO;
import ethos.runehub.skill.node.io.ThievingStallDAO;
import ethos.runehub.skill.node.io.WoodcuttingNodeDAO;
import org.runehub.api.util.SkillDictionary;

public class ThievingStallEditor extends Editor{
    @Override
    public void run(ConsoleEditor console) {
        this.loadDefaults();
    }

    private void loadDefaults() {
        createNode(11730,5,16,-23581974517233973L,634,4,256,0,256);
    }

    private void createNode(int id, int levelRequirement, int interactionExperience, long tableId, int depletedId, int respawnTime, int harvestChance, int depletionMinRoll, int max) {
        ThievingStallDAO.getInstance().create(new ThievingStallNode(id, levelRequirement, interactionExperience, SkillDictionary.Skill.THIEVING.getId(), tableId, harvestChance,max));
        RenewableNodeDAO.getInstance().create(new RenewableNode(id,depletedId,respawnTime,depletionMinRoll));
    }
}

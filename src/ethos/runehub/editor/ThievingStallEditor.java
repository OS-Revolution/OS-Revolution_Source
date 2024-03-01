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
        createNode(11730,5,16,-23581974517233973L,634,4,256,0,256); // Bakers Stall 1
        createNode(6574,5,16,-3523346064029758874L,634,4,256,0,256); // Tea Stall 2
        createNode(635,5,16,-3523346064029758874L,634,4,256,0,256); // Tea Stall 3
        createNode(20350,5,16,-3523346064029758874L,634,4,256,0,256); // Tea Stall 4
        createNode(11729,20,24,6676568764568192338L,634,7,256,0,256); // Silk Stall 5
       // createNode(11730,22,27,-23581974517233973L,634,7,256,0,256); // Wine Stall
        createNode(7053,27,10,-7442308887119248421L,634,6,256,0,256); // Seed Stall 6
        createNode(11732,35,36,-8835543925372728067L,634,8,256,0,256); // Fur Stall 7
        createNode(4277,42,42,-4463804438927621791L,634,8,256,0,256); // Fish Stall 8
        createNode(11734,50,54,7997473146465570071L,634,10,256,0,256); // Silver Stall 9
        createNode(11733,65,81,2558512402690624009L,634,10,256,0,256); // Spice Stall 10
        createNode(11731,75,160,-564486835568818799L,634,15,256,0,256); // Gem Stall 11
    }

    private void createNode(int id, int levelRequirement, int interactionExperience, long tableId, int depletedId, int respawnTime, int harvestChance, int depletionMinRoll, int max) {
        ThievingStallDAO.getInstance().create(new ThievingStallNode(id, levelRequirement, interactionExperience, SkillDictionary.Skill.THIEVING.getId(), tableId, harvestChance,max));
        RenewableNodeDAO.getInstance().create(new RenewableNode(id,depletedId,respawnTime,depletionMinRoll));
    }
}

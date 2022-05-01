package ethos.runehub.skill.node;

import ethos.model.items.ItemAssistant;
import ethos.runehub.skill.gathering.fishing.FishLevel;
import ethos.runehub.skill.node.impl.RenewableNode;
import ethos.runehub.skill.node.impl.gatherable.GatheringNode;
import ethos.runehub.skill.node.io.*;
import ethos.runehub.skill.node.impl.gatherable.impl.*;
import org.runehub.api.util.SkillDictionary;

import java.util.Arrays;

public class NodeTest {

    public static void main(String[] args) {

        createTreeNodes(10041, 1276, 1278, 1279);
        createOakNodes(1751, 11756);
        createWillowNodes(1756, 1760, 1750, 1758, 11759, 11761, 11763, 11755);
        createMapleNodes(1759, 11762);
        createYewNodes(1753, 1754, 11758, 27255);
        createMagicNodes(1761, 11764);
        createWoodcuttingNode(2887,99,150,6223316775799507538L,1342,800,1,0,6); //cinnamon tree
//        createWoodcuttingNode(2887,1,150,-1,1342,800,1000,0,1000); //abyss tentacles



        createClayNodes(7487, 7454);
        createCopperNodes(7484, 11961, 11960, 11962, 13709);
        createTinNodes(7485, 9714, 9716, 11957, 11958, 11959, 13712);
        createIronNodes(7488, 11954, 11955, 11956, 13710, 13444, 13445, 13446, 7455);
        createCoalNodes(7489, 9717, 9718, 9719, 2096, 13714, 13706);
        createGoldNodes(7491, 7458, 9722, 9720, 13707, 14962, 14963, 14964, 13441, 13442, 13443, 8728, 8975);
        createMithrilNodes(7492, 13718, 7459);
        createAdamantiteNodes(7493, 13720, 7460);
        createRuniteNodes(7494, 7461, 14175);
        createMiningNode(7471,1,5,4220464022211939160L,-1,-1,1000,1000,8,1000);

//        createShrimpFishingNode();
//        createAnchovyFishingNode();
//        createSardineFishingNode();
//        createHerringFishingNode();

        createSmallNet1();
        createBait1();
        createAdvancedFishingSpot(3,20,5927298435842065754L,314,20928,307,16,96); //lure
        createAdvancedFishingSpot(4,25,8017948149741733797L,313,20928,309,16,96); //bait
        createAdvancedFishingSpot(5,40,8019266119932658008L,-1,20929,301,6,95);//cage
        createAdvancedFishingSpot(6,32,2250548059131170668L,-1,20929,311,4,48);//harpoon
        createAdvancedFishingSpot(7,32,3181061014343730924L,-1,20926,305,5,65);//big net
        createAdvancedFishingSpot(8,76,-2966189725657772939L,-1,20926,311,3,40);//harpoon

        createFishLevel(317,1,10,-1); //shrimps
        createFishLevel(321,15,40,-1); //anchovies
        createFishLevel(327,5,20,313); //sardine
        createFishLevel(345,10,30,313); //herring
        createFishLevel(345,20,50,314); //trout
        createFishLevel(345,30,70,314); //salmon
        createFishLevel(345,25,60,313); //pike
        createFishLevel(377,40,90,-1); //lobster
        createFishLevel(359,35,80,-1); //tuna
        createFishLevel(371,50,100,-1); //swordfish
        createFishLevel(383,76,110,-1); //shark

        createForagingNode(8132,1,12,-7065169498672184451L,-1,80,16,50,96);
        createForagingNode(8133,14,15,-3941900583690615282L,-1,80,16,50,96);
        createForagingNode(8134,19,18,-4583966657062287043L,-1,80,16,50,96);
        createForagingNode(8135,26,24,-4288257009528650164L,-1,80,16,50,96);
        createForagingNode(8136,32,30,7533136831862032637L,-1,80,16,50,96);
        createForagingNode(8137,38,38,4895250652240432251L,-1,80,16,50,96);
        createForagingNode(8138,44,48,-6382097510913456227L,-1,80,16,50,96);
        createForagingNode(8139,50,61,601707381973468815L,-1,80,16,50,96);
        createForagingNode(8140,56,78,-7220802764691059657L,-1,80,16,50,96);
        createForagingNode(8141,62,98,4045070789225498865L,-1,80,16,50,96);
        createForagingNode(8142,67,120,-2835110223605184254L,-1,80,16,50,96);
        createForagingNode(8144,73,151,-6904893650944375485L,-1,80,16,50,96);
        createForagingNode(8145,79,192,-2571227490922839450L,-1,80,16,50,96);
        createForagingNode(8146,85,224,-2302024383490407217L,-1,80,16,50,96);
        createForagingNode(8146,85,224,-2302024383490407217L,-1,800,1,40,6); //bloodweed

        createForagingNode(7855,26,120,7473856952692066914L,-1,80,16,40,96); //limpwurt
        createForagingNode(7725,59,29,6812173401978392303L,-1,80,16,50,96); //whiteberry bush
        createForagingNode(8621,61,82,-3742746979874681384L,-1,80,16,50,96); //snapegrass needs pick option, name change, and recolor
        createForagingNode(7134,35,70,884346873633983237L,-1,80,16,50,96); //mort myre fungus needs client side pick option and name change
        createForagingNode(7757,55,25,45713744309145440L,-1,80,16,50,96); //cactus spine cactus
        createForagingNode(1406,64,68,-2038964458387557841L,-1,80,16,50,96); //potato cactus needs name change and pick option


    }

    private static void createForagingNode(int id, int levelRequirement, int interactionExperience, long tableId,
                                           int depletedId, int respawnTime, int harvestChance, int depletionMinRoll, int max) {
        ForagingNodeDAO.getInstance().create(new ForagingNode(id, levelRequirement, interactionExperience, SkillDictionary.Skill.FARMING.getId(), tableId, harvestChance,max));
        RenewableNodeDAO.getInstance().create(new RenewableNode(id,depletedId,respawnTime,depletionMinRoll));
    }

    private static void createTreeNodes(int... id) {
        Arrays.stream(id).forEach(NodeTest::createTreeGatheringNode);
    }

    private static void createOakNodes(int... id) {
        Arrays.stream(id).forEach(NodeTest::createOakGatheringNode);
    }

    private static void createWillowNodes(int... id) {
        Arrays.stream(id).forEach(NodeTest::createWillowGatheringNode);
    }

    private static void createMapleNodes(int... id) {
        Arrays.stream(id).forEach(NodeTest::createMapleGatheringNode);
    }

    private static void createYewNodes(int... id) {
        Arrays.stream(id).forEach(NodeTest::createYewGatheringNode);
    }

    private static void createMagicNodes(int... id) {
        Arrays.stream(id).forEach(NodeTest::createMagicGatheringNode);
    }

    private static void createClayNodes(int... id) {
        Arrays.stream(id).forEach(NodeTest::createClayGatheringNode);
    }

    private static void createCopperNodes(int... id) {
        Arrays.stream(id).forEach(NodeTest::createCopperGatheringNode);
    }

    private static void createTinNodes(int... id) {
        Arrays.stream(id).forEach(NodeTest::createTinGatheringNode);
    }

    private static void createIronNodes(int... id) {
        Arrays.stream(id).forEach(NodeTest::createIronGatheringNode);
    }

    private static void createCoalNodes(int... id) {
        Arrays.stream(id).forEach(NodeTest::createCoalGatheringNode);
    }

    private static void createGoldNodes(int... id) {
        Arrays.stream(id).forEach(NodeTest::createGoldGatheringNode);
    }

    private static void createMithrilNodes(int... id) {
        Arrays.stream(id).forEach(NodeTest::createMithrilGatheringNode);
    }

    private static void createAdamantiteNodes(int... id) {
        Arrays.stream(id).forEach(NodeTest::createAdamantGatheringNode);
    }

    private static void createRuniteNodes(int... id) {
        Arrays.stream(id).forEach(NodeTest::createRuniteGatheringNode);
    }

    private static void createWoodcuttingNode(int id, int levelRequirement, int interactionExperience, long tableId,
                                              int depletedId, int respawnTime, int harvestChance, int depletionMinRoll, int max) {
        WoodcuttingNodeDAO.getInstance().create(new WoodcuttingNode(id, levelRequirement, interactionExperience, SkillDictionary.Skill.WOODCUTTING.getId(), tableId, harvestChance,max));
        RenewableNodeDAO.getInstance().create(new RenewableNode(id,depletedId,respawnTime,depletionMinRoll));
    }

    private static void createMiningNode(int id, int levelRequirement, int interactionExperience, long tableId,
                                         int depletedId, int respawnTime, int harvestChance, int depletionMinRoll, int miningCycleTicks, int max) {
        MiningNodeDAO.getInstance().create(new MiningNode(id, levelRequirement, interactionExperience,SkillDictionary.Skill.MINING.getId(), tableId, harvestChance,max,miningCycleTicks));
        RenewableNodeDAO.getInstance().create(new RenewableNode(id,depletedId,respawnTime,depletionMinRoll));
    }

    private static void createFishingNode(int id, int levelRequirement, long tableId, int harvestChance, int baitId, int spotId, int xp, int toolId,
                                          int respawnTime, int max) {
        FishingNodeDAO.getInstance().create(new FishingNode(id, levelRequirement, xp, SkillDictionary.Skill.FISHING.getId(), tableId, harvestChance,max,baitId,spotId,toolId ));
        RenewableNodeDAO.getInstance().create(new RenewableNode(spotId,-1,respawnTime,-1));
    }

    private static void createFishLevel(int itemId, int level, int xp, int baitId) {
        FishLevel fishLevel = new FishLevel(itemId,level,xp,baitId);
        FishLevelDAO.getInstance().create(fishLevel);
    }

    private static void createAdvancedFishingSpot(int id, int levelRequirement, long tableId, int baitId, int spotId, int toolId, int harvestChance, int max) {
        createFishingSpot(id,levelRequirement,tableId,harvestChance,baitId,spotId,toolId,45,max);
    }

//    private static void createBasicFishingSpot(int id, int levelRequirement, long tableId, int baitId, int spotId, int toolId, int max) {
//        createFishingSpot(id,levelRequirement,tableId,24,baitId,spotId,toolId,45,max);
//    }

    private static void createFishingSpot(int id, int levelRequirement, long tableId, int harvestChance, int baitId, int spotId, int toolId, int respawnTime, int max) {
        createFishingNode(id,levelRequirement,tableId,harvestChance,baitId,spotId,0,toolId,respawnTime, max);
    }

    private static void createSmallNet1() {
        createFishingNode(1,1,-5718067131746465035L,24,-1,20927,0,303,45,128);
    }
    private static void createBait1() {
        createFishingNode(2,5,3301933395932890975L,24,313,20927,0,307,45,128);
    }
//    private static void createShrimpFishingNode() {
//        createFishingNode(317,1,-1,30,-1,1,10);
//    }
//
//    private static void createAnchovyFishingNode() {
//        createFishingNode(321,15,-1,40,-1,1,40);
//    }
//
//    private static void createSardineFishingNode() {
//        createFishingNode(327,5,-1,32,313,2,20);
//    }
//
//    private static void createHerringFishingNode() {
//        createFishingNode(345,10,-1,35,313,2,30);
//    }

    private static void createTreeGatheringNode(int nodeId) {
        createWoodcuttingNode(nodeId, 1, 25, -7793573946903476076L, 1342, 15, 64, 0,200);
    }

    private static void createOakGatheringNode(int nodeId) {
        createWoodcuttingNode(nodeId, 15, 38, 6507190171293207098L, 1356, 25, 32, 87,100);
    }

    private static void createWillowGatheringNode(int nodeId) {
        createWoodcuttingNode(nodeId, 30, 68, -3866761142122035288L, 9711, 35, 16, 87,50);
    }

    private static void createMapleGatheringNode(int nodeId) {
        createWoodcuttingNode(nodeId, 45, 100, 7040907870021173253L, 9712, 45, 1, 87,25);
    }

    private static void createYewGatheringNode(int nodeId) {
        createWoodcuttingNode(nodeId, 60, 175, -2896030443866405078L, 9714, 65, 4, 87,12);

    }

    private static void createMagicGatheringNode(int nodeId) {
        createWoodcuttingNode(nodeId, 75, 175, 6173248919233382318L, 9713, 75, 2, 87,6);
    }

    private static void createClayGatheringNode(int nodeId) {
        createMiningNode(nodeId, 1, 18, -7663036617566696989L, 2835, 3, 128, 0,15,400);
    }

    private static void createCopperGatheringNode(int nodeId) {
        createMiningNode(nodeId, 1, 18, 7501536363911856927L, 2835, 4, 128, 0,15,400);
    }

    private static void createTinGatheringNode(int nodeId) {
        createMiningNode(nodeId, 1, 18, -2630700682957665248L, 2835, 4, 128, 0,15,400);
    }

    private static void createIronGatheringNode(int nodeId) {
        createMiningNode(nodeId, 15, 35, 8944265982660791207L, 2835, 9, 110, 0,18,350);
    }

    private static void createCoalGatheringNode(int nodeId) {
        createMiningNode(nodeId, 30, 50, -2020329836359956340L, 2835, 15, 16, 0,29,100);
    }

    private static void createGoldGatheringNode(int nodeId) {
        createMiningNode(nodeId, 40, 65, -7131016907391613334L, 2835, 25, 7, 0,32,75);
    }

    private static void createMithrilGatheringNode(int nodeId) {
        createMiningNode(nodeId, 55, 80, -8883524116013432186L, 2835, 40, 4, 0,35,50);
    }

    private static void createAdamantGatheringNode(int nodeId) {
        createMiningNode(nodeId, 70, 95, -4922639131369324427L, 2835, 50, 1, 0,37,25);
    }

    private static void createRuniteGatheringNode(int nodeId) {
        createMiningNode(nodeId, 85, 125, 5102813157125670410L, 2835, 100, 1, 0,39,18);
    }

    private static void storeNode(GatheringNode node) {
        GatheringNodeDAO.getInstance().create(node);
    }

    private static GatheringNode readGatheringNode(int id) {
        return GatheringNodeDAO.getInstance().read(id);
    }
}

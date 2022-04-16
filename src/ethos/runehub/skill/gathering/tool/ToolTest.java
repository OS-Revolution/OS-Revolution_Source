package ethos.runehub.skill.gathering.tool;

public class ToolTest {

    public static void main(String args[]) {
        storeTool(createGatheringTool(1351,1,8,1,0,1.0f,879)); //bronze
        storeTool(createGatheringTool(1349,1,8,1.5,0,1.0f,877)); //iron
        storeTool(createGatheringTool(1353,5,8,2,0,1.0f,875)); //steel
        storeTool(createGatheringTool(1361,10,8,2.5,0,1.0f,873)); //black
        storeTool(createGatheringTool(1355,20,8,3,0,1.0f,871)); //mithril
        storeTool(createGatheringTool(1357,30,8,3.5,0,1.0f,869)); //adamant
        storeTool(createGatheringTool(1359,40,8,4,0,1.0f,867)); //rune
        storeTool(createGatheringTool(6739,60,8,4.5,0,1.0f,2846)); //dragon
        storeTool(createGatheringTool(13241,60,8,4.5,0,1.0f,2117)); //infernal
        storeTool(createGatheringTool(20011,60,8,4.5,5,1.0f,7264)); //3a

        storeTool(createGatheringTool(1265,1,14,1,1,1.0f,6747)); //bronze
        storeTool(createGatheringTool(1267,1,14,1.5,1,1.0f,6748)); //iron
        storeTool(createGatheringTool(1269,5,14,2,2,1.0f,6749)); //steel
        storeTool(createGatheringTool(12297,10,14,2.5,3,1.0f,6751)); //black
        storeTool(createGatheringTool(1273,20,14,3,4,1.0f,6751)); //mithril
        storeTool(createGatheringTool(1271,30,14,3.5,5,1.0f,6750)); //adamant
        storeTool(createGatheringTool(1275,40,14,4,6,1.0f,6746)); //rune
        storeTool(createGatheringTool(11920,60,14,4.5,7,1.0f,7139)); //dragon
        storeTool(createGatheringTool(12797,60,14,4.5,7,1.0f,335)); //dragon or
        storeTool(createGatheringTool(13243,60,14,4.5,8,1.0f,4482)); //infernal
        storeTool(createGatheringTool(20014,60,14,4.5,9,1.0f,7283)); //3a

        storeTool(createGatheringTool(303,1,10,1,0,1.0f,621)); //small fishing net
        storeTool(createGatheringTool(305,1,10,1,0,1.0f,621)); //big fishing net
        storeTool(createGatheringTool(307,1,10,1,0,1.0f,622)); //fishing rod
        storeTool(createGatheringTool(309,1,10,1,0,1.0f,622)); //fly fishing rod
        storeTool(createGatheringTool(301,1,10,1,0,1.0f,619)); //lobster pot
        storeTool(createGatheringTool(311,1,10,1,0,1.0f,618)); //harpoon
        storeTool(createGatheringTool(10129,1,10,1.5,0,1.0f,618)); //harpoon
        storeTool(createGatheringTool(21028,1,10,4.5,0,1.0f,618)); //harpoon
        storeTool(createGatheringTool(21031,1,10,4.5,0,1.0f,618)); //harpoon

        storeTool(createGatheringTool(5329,1,19,1,0,1.0f,2286)); //secateurs
        storeTool(createGatheringTool(7409,1,19,1.5,10,1.0f,2286)); //magic secateurs
        storeTool(createGatheringTool(7411,1,19,1.5,30,1.5f,2286)); //jewel secateurs
    }

    private static GatheringTool createGatheringTool(int itemId, int levelRequired, int skillId, double basePower, int baseEfficiency, float xpGainMultiplier, int animationId) {
        return new GatheringTool( itemId,  levelRequired,  skillId,  basePower,  baseEfficiency,  xpGainMultiplier,  animationId);
    }

//    private static void createFishingTool(int itemId, int levelRequired, int skillId, int basePower, int baseEfficiency, float xpGainMultiplier, int animationId,
//                                          int baitId, int baitConsumed, int minRollToConsumeBait) {
//        FishingTool tool = new FishingTool(itemId,levelRequired,skillId,basePower,baseEfficiency,xpGainMultiplier,animationId,baitId,baitConsumed,minRollToConsumeBait);
//        FishingToolDAO.getInstance().create(tool);
//    }

    private static void createBronzeAxe() {
        //8 is average depletion min roll
        final GatheringTool tool = createGatheringTool(1351,1,8,1,0,1.0f,879);
        GatheringToolDAO.getInstance().create(tool);

    }

    private static void storeTool(GatheringTool tool) {
        GatheringToolDAO.getInstance().create(tool);
    }

    private static GatheringTool readGatheringNode(int id) {
        return GatheringToolLoader.getInstance().read(id);
    }
}

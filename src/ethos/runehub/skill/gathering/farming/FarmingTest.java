package ethos.runehub.skill.gathering.farming;

import ethos.runehub.skill.gathering.farming.crops.CropProperties;
import ethos.runehub.skill.gathering.farming.crops.CropPropertiesDatabase;
import ethos.runehub.skill.gathering.farming.crops.GrowthStage;
import ethos.runehub.skill.gathering.farming.patch.Patch;
import ethos.runehub.skill.gathering.farming.patch.PatchDatabase;
import ethos.runehub.skill.gathering.farming.patch.PatchProperties;
import ethos.runehub.skill.gathering.farming.patch.PatchPropertiesDatabase;
import ethos.runehub.skill.gathering.tool.GatheringTool;
import ethos.runehub.skill.gathering.tool.GatheringToolLoader;
import org.runehub.api.model.entity.item.GameItem;
import org.runehub.api.util.SkillDictionary;
import org.runehub.api.util.math.geometry.Point;
import org.runehub.api.util.math.geometry.impl.Polygon;
import org.runehub.api.util.math.geometry.impl.Rectangle;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class FarmingTest {

    public static final GatheringTool RAKE = new GatheringTool(5341, 1, SkillDictionary.Skill.FARMING.getId(), 0, 0, 1.0f, 2273);

    public static final ScheduledExecutorService FARMING_GROWTH_TICK_SERVICE = Executors.newSingleThreadScheduledExecutor();

//    private static Runnable update(int minutes) {
//        return () -> {
//            PatchDatabase.getInstance().getAllEntries().forEach(patch -> {
//                if (patch.getPlantTime() > 0) {
//                    if (patch.getSeedId() > -1) {
//                        PatchFactoryProducer.PatchType patchType = FarmingUtils.getPatchType(patch.getObjectId());
//                        if (patchType != null) {
//                            PatchProperties patchProperties = PatchFactoryProducer.getPatchFactory(patchType)
//                                    .getPatchProperties(patch.getPatchId());
//                            if (patchProperties.getGrowthCycleMS() == minutes * 60000) {
//                                patch.update();
//                            }
//                        }
//
//                    }
//                }
//            });
//        };
//    }

    public static void startFarmingUpdates() {
//        FARMING_GROWTH_TICK_SERVICE.scheduleAtFixedRate(update(1), 0, 1, TimeUnit.MINUTES); //test
//        FARMING_GROWTH_TICK_SERVICE.scheduleAtFixedRate(update(5), 0, 5, TimeUnit.MINUTES);
//        FARMING_GROWTH_TICK_SERVICE.scheduleAtFixedRate(update(10), 0, 10, TimeUnit.MINUTES);
//        FARMING_GROWTH_TICK_SERVICE.scheduleAtFixedRate(update(20), 0, 20, TimeUnit.MINUTES);
//        FARMING_GROWTH_TICK_SERVICE.scheduleAtFixedRate(update(40), 0, 40, TimeUnit.MINUTES);
//        FARMING_GROWTH_TICK_SERVICE.scheduleAtFixedRate(update(80), 0, 80, TimeUnit.MINUTES);
//        FARMING_GROWTH_TICK_SERVICE.scheduleAtFixedRate(update(160), 0, 160, TimeUnit.MINUTES);
//        FARMING_GROWTH_TICK_SERVICE.scheduleAtFixedRate(update(320), 0, 320, TimeUnit.MINUTES);
//        FARMING_GROWTH_TICK_SERVICE.scheduleAtFixedRate(update(640), 0, 640, TimeUnit.MINUTES);
    }

    public FarmingTest() {

    }

    private void createHerbPatch(int baseStateObjectId) {

    }

    private void createHerbPatchProperties(int id, Polygon boundary, boolean waterable) {
        PatchProperties patchProperties = new PatchProperties(id, boundary, 5L, waterable);
        PatchPropertiesDatabase.getInstance().create(patchProperties);
    }

    private void createCropProperties(int id, int cycles, int seedId, int cropId, int plantXp, int harvestXp, int level, int seedAmout,
                                      int baseHarvestAmount, float diseaseChance, ArrayList<GrowthStage> growthStages) {
        CropProperties cropProperties = new CropProperties(id, cycles, seedId, cropId, plantXp, harvestXp, level, seedAmout, baseHarvestAmount, diseaseChance, growthStages);
        CropPropertiesDatabase.getInstance().create(cropProperties);
    }

//    private void createArdougneHerbPatchWeedsNode() {
//        FarmingNode baseNode = new FarmingNode(8152, 1, 4, 19, -2966189725657772939L, 0);
//        FarmingNode stage3Node = new FarmingNode(8135, 1, 4, 19, -2966189725657772939L, 0);
//        FarmingNode stage2Node = new FarmingNode(8134, 1, 4, 19, -2966189725657772939L, 0);
//        FarmingNode stage1Node = new FarmingNode(8133, 1, 4, 19, -2966189725657772939L, 0);
//        FarmingNodeLoader.getInstance().createAndPush(baseNode.getId(), baseNode);
//        FarmingNodeLoader.getInstance().createAndPush(stage3Node.getId(), stage3Node);
//        FarmingNodeLoader.getInstance().createAndPush(stage2Node.getId(), stage2Node);
//        FarmingNodeLoader.getInstance().createAndPush(stage1Node.getId(), stage1Node);
//
//        RenewableNodeLoader.getInstance().createAndPush(8152,new RenewableNode(8152,8135,500,0));
//        RenewableNodeLoader.getInstance().createAndPush(8135,new RenewableNode(8135,8134,500,0));
//        RenewableNodeLoader.getInstance().createAndPush(8134,new RenewableNode(8134,8133,500,0));
//        RenewableNodeLoader.getInstance().createAndPush(8133,new RenewableNode(8133,8132,500,0));
//        RenewableNodeLoader.getInstance().createAndPush(8132,new RenewableNode(8132,8132,500,0));
//    }

    public static void main(String[] args) {
        FarmingTest farming = new FarmingTest();

        GatheringToolLoader.getInstance().createAndPush(RAKE.getItemId(),RAKE);

        farming.createHerbPatchProperties(8152, new Rectangle(new Point(2671, 3374), new Point(2670, 3375)), false);
//        farming.createArdougneHerbPatchWeedsNode();
//
////        RegionDataAccessObject.getInstance().getAllEntries().get(0).getBoundingShape().getAllPoints();
//
//        farming.createPotato();
//        farming.createAllotmentWeeds(8550);
//        farming.createAllotmentWeeds(8551);
//
//        PatchProperties faladorNorthAllotmentProperties = new AllotmentPatchProperties(8550,
//                new IrregularPolygon(
//                        new Point(3050, 3307),
//                        new Point(3050, 3312),
//                        new Point(3054, 3312),
//                        new Point(3054, 3311),
//                        new Point(3051, 3311),
//                        new Point(3051, 3307),
//                        new Point(3050,3307)
//                ));
//
//        PatchProperties faladorSouthAllotmentProperties = new AllotmentPatchProperties(8551,
//                new IrregularPolygon(
//                        new Point(3055, 3303),
//                        new Point(3059, 3303),
//                        new Point(3059, 3308),
//                        new Point(3058, 3308),
//                        new Point(3058, 3304),
//                        new Point(3055, 3304),
//                        new Point(3055, 3303)
//                ));
//
//        Patch faladorNorthAllotmentPatch = new Patch(8550,new ArrayList<>());
//
//        farming.createPatchProperties(faladorNorthAllotmentProperties);
//        farming.createPatchProperties(faladorSouthAllotmentProperties);
//
//        farming.createPatch(faladorNorthAllotmentPatch);
////
////        faladorNorthAllotmentPatch.getPatchData().add(new PlayerPatchData(
////                1L,
////                faladorNorthAllotmentPatch.getPatchId()
////        ));
////
////        PatchDatabase.getInstance().read(8550);
////
////        PatchDatabase.getInstance().getAllEntries().forEach(patch -> System.out.println(patch.getPatchData().size()));
//
//
////        System.out.println(PatchDatabase.getInstance().read(8551).getPlayerPatchData(-288113388807177876L));
////        farming.updatePatch(faladorNorthAllotmentPatch);
////        faladorNorthAllotmentPatch.setState(Patch.State.DEAD);
////        faladorNorthAllotmentPatch.getPatchData().get(0).setObjectId(8573);
////        farming.updatePatch(faladorNorthAllotmentPatch);
//
////        FARMING_GROWTH_TICK_SERVICE.scheduleAtFixedRate(() -> {
////            System.out.println("Farming Growth Tick");
////            PatchDatabase.getInstance().getAllEntries().forEach(Patch::update);
////        },30,30,TimeUnit.SECONDS);
//
//        ;
//
//
////        System.out.println(FarmingUtils.getPatchFromLocation(3055,3303));
//
////3055 3303
//
////        System.out.println(farming.readCropProperties(8550).getGrowthStages().size());
////
////        PatchDatabase.getInstance().getAllEntries().forEach(patch -> System.out.println(patch.getOwnerId()));

    }

    private CropProperties readCropProperties(int id) {
        return CropPropertiesDatabase.getInstance().read(id);
    }

    private void updatePatch(Patch patch) {
        PatchDatabase.getInstance().update(patch);
    }

    private Patch readPatch(long id) {
        return PatchDatabase.getInstance().read(id);
    }

    private void createPatch(Patch patch) {
        PatchDatabase.getInstance().create(patch);
    }

    private void createPatchProperties(PatchProperties properties) {
        PatchPropertiesDatabase.getInstance().create(properties);
    }

    private void updatePatchProperties(PatchProperties properties) {
        PatchPropertiesDatabase.getInstance().update(properties);
    }

    private PatchProperties readPatchProperties(int id) {
        return PatchPropertiesDatabase.getInstance().read(id);
    }

    private void createAllotmentWeeds(int baseId) {
        final ArrayList<GrowthStage> growthStages = new ArrayList<>();

        growthStages.add(0, new GrowthStage(
                8573, 8569, 8572, 8566
        ));
        growthStages.add(1, new GrowthStage(
                8574, 8568, 8571, 8565
        ));
        growthStages.add(2, new GrowthStage(
                8575, 8567, 8570, 8564
        ));
        growthStages.add(3, new GrowthStage(
                8576, -1, -1, 8563
        ));
        growthStages.add(4, new GrowthStage(
                baseId, -1, -1, -1
        ));

        CropPropertiesDatabase.getInstance().create(new CropProperties(
                baseId,
                4,
                -1,
                6055,
                0,
                4,
                1,
                0,
                1,
                0f,
                new ArrayList<>(),
                new ArrayList<>(),
                growthStages
        ));
    }

    private void createPotato() {
        final ArrayList<GameItem> payment = new ArrayList<>();
        final ArrayList<GrowthStage> growthStages = new ArrayList<>();

        payment.add(new GameItem(1040, 2));

        growthStages.add(0, new GrowthStage(
                8558, -1, -1, 8563
        ));
        growthStages.add(1, new GrowthStage(
                8559, 8567, 8570, 8564
        ));
        growthStages.add(2, new GrowthStage(
                8560, 8568, 8571, 8565
        ));
        growthStages.add(3, new GrowthStage(
                8561, 8569, 8572, 8566
        ));
        growthStages.add(4, new GrowthStage(
                8562, -1, -1, -1
        ));

        CropPropertiesDatabase.getInstance().create(new CropProperties(
                7548,
                4,
                7548,
                1942,
                8,
                9,
                1,
                3,
                3,
                0.9f,
                new ArrayList<>(),
                payment,
                growthStages
        ));
    }

    public static void farmingTick() {

    }
}

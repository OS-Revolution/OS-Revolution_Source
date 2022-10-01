package ethos.runehub.skill.gathering.farming;

import ethos.model.players.Player;
import ethos.model.players.PlayerHandler;
import ethos.runehub.TimeUtils;
import ethos.runehub.db.PlayerCharacterContextDataAccessObject;
import ethos.runehub.entity.player.PlayerCharacterContext;
import ethos.runehub.skill.gathering.farming.crop.Crop;
import ethos.runehub.skill.gathering.farming.crop.CropDAO;
import ethos.runehub.skill.gathering.farming.crop.CropPayment;
import ethos.runehub.skill.gathering.farming.crop.GrowthStage;
import ethos.runehub.skill.gathering.farming.patch.Patch;
import ethos.runehub.skill.gathering.farming.patch.PatchDAO;
import ethos.runehub.skill.gathering.farming.patch.PatchState;
import ethos.runehub.skill.gathering.farming.patch.PatchType;
import org.runehub.api.util.math.geometry.Point;
import org.runehub.api.util.math.geometry.impl.IrregularPolygon;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GrowthCycleController {
//
//    private boolean isCropGrowing(PlayerCharacterContext player, Patch patch) {
//        return player.getPlayerSaveData().getPatchGrowthStage(patch.getId()) < CropDAO.getInstance().read(player.getPlayerSaveData().getCropId(patch.getId())).getGrowthCycles();
//    }
//
//    private boolean isCropVulnerable(PlayerCharacterContext player, Patch patch, Crop crop) {
//        int growthStage = player.getPlayerSaveData().getPatchGrowthStage(patch.getId());
//        PatchState patchState = player.getPlayerSaveData().getPatchState(patch.getId());
//        System.out.println("Growth Stage:  " + growthStage);
//        System.out.println("Patch State: " + patchState);
//        return (growthStage > 0 && growthStage < crop.getGrowthCycles()) && patchState == PatchState.HEALTHY;
//    }
//
//    private void doGrowthCycle(PlayerCharacterContext player, PatchType patchType) {
//        PatchDAO.getInstance().getAllEntries().stream().filter(patch -> patch.getPatchType() == patchType).forEach(patch -> {
//            if (player.getPlayerSaveData().hasCrop(patch.getId())) {
//                if (isCropGrowing(player, patch)) {
//                    System.out.println("Crop is Growing");
//                    Crop crop = CropDAO.getInstance().read(player.getPlayerSaveData().getCropId(patch.getId()));
//                    if (isCropVulnerable(player, patch, crop)) {
//                        System.out.println("Crop is vulnerable");
//                        if (giveDiseaseToCrop(crop)) {
//                            System.out.println("Giving disease to crop");
//                            this.setCropState(player, patch, PatchState.DISEASED);
//                        } else {
//                            System.out.println("Growing crop to next stage");
//                            player.getPlayerSaveData().incrementGrowthStage(patch.getId());
//                        }
//                    } else if (player.getPlayerSaveData().getPatchState(patch.getId()) == PatchState.DISEASED) {
//                        System.out.println("Killing crop");
//                        this.setCropState(player, patch, PatchState.DEAD);
//                    } else if (player.getPlayerSaveData().getPatchState(patch.getId()) == PatchState.DEAD) {
//                        System.out.println("Crop is dead");
//                    } else if (player.getPlayerSaveData().getPatchGrowthStage(patch.getId()) == 0) {
//                        System.out.println("Growing crop from infancy ");
//                        player.getPlayerSaveData().incrementGrowthStage(patch.getId());
//                        setCropState(player, patch, PatchState.HEALTHY);
//                    }
//                } else {
//                    System.out.println("Crop is full grown for: " + player.getId());
//                }
//            }
//        });
//    }
//
//    private void doWeedGrowthCycle(PlayerCharacterContext player,PatchType patchType) {
//        PatchDAO.getInstance().getAllEntries().stream().filter(patch -> patch.getPatchType() == patchType).forEach(patch -> {
//            if (!player.getPlayerSaveData().hasCrop(patch.getId())) {
//                if (isCropGrowing(player, patch)) {
//                    System.out.println("Growing Weeds");
//                    player.getPlayerSaveData().incrementGrowthStage(patch.getId());
//                }
//            }
//        });
//    }
//
//    private boolean giveDiseaseToCrop(Crop crop) {
//        return random.nextFloat() < crop.getBaseDiseaseChance();
//    }
//
//    private void setCropState(PlayerCharacterContext player, Patch patch, PatchState state) {
//        player.getPlayerSaveData().setGrowthStage(patch.getId(), state);
//    }
//
//
//    public void doGrowthCycleForOfflinePlayer(PlayerCharacterContext player, PatchType patchType) {
//        this.doGrowthCycle(player, patchType);
//        PlayerCharacterContextDataAccessObject.getInstance().update(player);
//    }
//
//    public void doGrowthCycleForOnlinePlayer(Player player, PatchType patchType) {
//        this.doGrowthCycle(player.getContext(), patchType);
//        player.save();
//    }
//
//    public void doWeedGrowthCycleForOfflinePlayer(PlayerCharacterContext player, PatchType patchType) {
//        this.doWeedGrowthCycle(player,patchType);
//        PlayerCharacterContextDataAccessObject.getInstance().update(player);
//    }
//
//    public void doWeedGrowthCycleForOnlinePlayer(Player player, PatchType patchType) {
//        this.doWeedGrowthCycle(player.getContext(),patchType);
//        player.save();
//    }
//
//    public void initializeGrowthCycles() {
//        this.startGrowthCycle(10, () -> {
//            System.out.println("Allotment Growth Cycle");
//            System.out.println("Hops Growth Cycle");
//            this.grow(PatchType.ALLOTMENT);
//        });
//        this.startGrowthCycle(5, () -> {
//            System.out.println("Flower Growth Cycle");
//            System.out.println("Weed Growth Cycle");
//            Arrays.stream(PatchType.values()).forEach(this::grow);
//        });
//        this.startGrowthCycle(20, () -> {
//            System.out.println("Herb Growth Cycle");
//            System.out.println("Bush Growth Cycle");
//        });
//        this.startGrowthCycle(40, () -> {
//            System.out.println("Tree Growth Cycle");
//        });
//        this.startGrowthCycle(160, () -> {
//            System.out.println("Fruit Tree Growth Cycle");
//        });
//    }
//
//    private void grow(PatchType patchType) {
//        System.out.println(patchType + " Growth Cycle");
//            PlayerCharacterContextDataAccessObject.getInstance().getAllEntries().forEach(player -> {
//                if (PlayerHandler.getPlayer(player.getId()).isPresent()) {
//                    this.doWeedGrowthCycleForOnlinePlayer(PlayerHandler.getPlayer(player.getId()).get(),patchType);
//                    this.doGrowthCycleForOnlinePlayer(PlayerHandler.getPlayer(player.getId()).get(), patchType);
//                } else {
//                    this.doWeedGrowthCycleForOfflinePlayer(player,patchType);
//                    this.doGrowthCycleForOfflinePlayer(player, patchType);
//                }
//            });
//    }
//
//    public Duration getNextFruitTreeGrowthCycle() {
//        return Duration.between(ZonedDateTime.now(ZoneId.of("UTC")), this.getNextGrowthCycle(160));
//    }
//
//    public Duration getNextTreeGrowthCycle() {
//        return Duration.between(ZonedDateTime.now(ZoneId.of("UTC")), this.getNextGrowthCycle(40));
//    }
//
//    public Duration getNextBushGrowthCycle() {
//        return this.getNextHerbGrowthCycle();
//    }
//
//    public Duration getNextHerbGrowthCycle() {
//        return Duration.between(ZonedDateTime.now(ZoneId.of("UTC")), this.getNextGrowthCycle(20));
//    }
//
//    public Duration getNextHopsGrowthCycle() {
//        return this.getNextAllotmentGrowthCycle();
//    }
//
//    public Duration getNextAllotmentGrowthCycle() {
//        return Duration.between(ZonedDateTime.now(ZoneId.of("UTC")), this.getNextGrowthCycle(10));
//    }
//
//    public Duration getNextFlowerGrowthCycle() {
//        return Duration.between(ZonedDateTime.now(ZoneId.of("UTC")), this.getNextGrowthCycle(5));
//    }
//
//    private ZonedDateTime getNextGrowthCycle(int minuteInterval) {
//        final ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));
//        final int minuteDifference = minuteInterval - (now.getMinute() % minuteInterval);
//        return now.plusMinutes(minuteDifference);
//    }
//
//    private void startGrowthCycle(int minuteInterval, Runnable onGrowthCycle) {
//        final ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));
//        ZonedDateTime nextRun = this.getNextGrowthCycle(minuteInterval);
//        final Duration initialDelay = Duration.between(now, nextRun);
//        System.out.println("Next Growth Cycle: " + TimeUtils.getDurationString(initialDelay));
//        growthCycleExecutorService.scheduleAtFixedRate(
//                onGrowthCycle,
//                initialDelay.toMillis(),
//                Duration.ofMinutes(minuteInterval).toMillis(),
//                TimeUnit.MILLISECONDS);
//    }
//
//
//    public GrowthCycleController() {
//        this.random = new Random();
////        PatchDAO.getInstance().create(new Patch(1, true, new IrregularPolygon( //Falador North Allotment patch
////                new Point(3059, 3311),
////                new Point(3058, 3311),
////                new Point(3058, 3312),
////                new Point(3059, 3312),
////                new Point(3059, 3311)
////        ),
////                0,
////                PatchType.HERB.ordinal()
////        ));
////                CropDAO.getInstance().create(
////                new Crop(
////                        0,
////                        3,
////                        6055,
////                        0,
////                        4,
////                        1,
////                        0,
////                        1,
////                        0,
////                        true,
////                        new CropPayment(0, 0),
////                        new GrowthStage[]{
////                                new GrowthStage(15066,15066,15066,15066),
////                                new GrowthStage(15065,15065,15065,15065),
////                                new GrowthStage(15064,15064,15064,15064),
////                                new GrowthStage(15063,15063,15063,15063)
////                        },
////                        PatchType.WEEDS.ordinal()
////                )
////        );
////
////                CropDAO.getInstance().create(
////                new Crop(
////                        5318,
////                        4,
////                        1942,
////                        8,
////                        9,
////                        1,
////                        3,
////                        3,
////                        0.5f,
////                        false,
////                        new CropPayment(6032, 2),
////                        new GrowthStage[]{
////                                new GrowthStage(8558,8558,8558,8563),
////                                new GrowthStage(8559,8567,8570,8564),
////                                new GrowthStage(8560,8568,8571,8565),
////                                new GrowthStage(8561,8569,8572,8566),
////                                new GrowthStage(8562,8562,8562,8562)
////                        },
////                        PatchType.ALLOTMENT.ordinal()
////                )
////        );
//    }

//    private final Random random;
    private final ScheduledExecutorService growthCycleExecutorService = Executors.newScheduledThreadPool(1);
}

package ethos.runehub.skill.gathering.farming;

import ethos.model.players.Player;
import ethos.model.players.PlayerHandler;
import ethos.runehub.TimeUtils;
import ethos.runehub.db.PlayerCharacterContextDataAccessObject;
import ethos.runehub.entity.player.PlayerCharacterContext;
import ethos.runehub.skill.gathering.farming.crop.*;
import ethos.runehub.skill.gathering.farming.patch.PatchType;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FarmController {

    private static FarmController instance = null;

    public static FarmController getInstance() {
        if (instance == null)
            instance = new FarmController();
        return instance;
    }

    private boolean isOvergrown(FarmingConfig config) {
        return config.getCrop() <= 0 && config.getStage() == 0;
    }

    private boolean isDead(FarmingConfig config) {
        return config.isWatered() == 1 && config.isDiseased() == 1;
    }

    private boolean isSuccessful(FarmingConfig config) {
        final float roll = random.nextFloat();
        return roll >= this.getBaseSuccessChance(config);
    }

    private double getActionSuccessChance(FarmingConfig config, double bonus) {
        double value = (CropCache.getInstance().read(config.getCrop()).getMinDiseaseRoll() * bonus) / 128;
        return value;
    }

    private double getBaseSuccessChance(FarmingConfig config) {
        if (config.getCrop() != 0) {
            double bonus = 1;
            if (config.watered()) {
                bonus += 0.2;
            }
            if (config.getCompost() == Farming.COMPOST) {
                bonus += 0.5;
            } else if (config.getCompost() == Farming.SUPERCOMPOST) {
                bonus += 0.8;
            } else if(config.getCompost() == Farming.ULTRACOMPOST) {
                bonus += 0.9;
            }
            return getActionSuccessChance(config, bonus);
        }
        return 0;
    }

    /**
     * disabled herb patches getting diseased to prevent graphical bug
     * @param config
     */
    private void rollForDisease(FarmingConfig config) {
        if (config.getStage() > 0 && config.getStage() < (CropCache.getInstance().read(config.getCrop()).getGrowthCycles() - 1)) {
            if (isSuccessful(config) && config.getType() != PatchType.HERB.ordinal()) {
                config.setDiseased(true);
            }
        }
    }

    private void updateCropHealthState(FarmingConfig config) {
        if (config.diseased()) {
            config.setWatered(true);
        } else {
            if (config.watered()) {
                config.setWatered(false);
            }
            this.rollForDisease(config);
            config.incrementCycle();
        }
    }

    private void doGrowthCycle(PlayerCharacterContext player, PatchType patchType) {
        player.getPlayerSaveData().farmingConfig().keySet().forEach(regionId -> {
            List<FarmingConfig> patches = player.getPlayerSaveData().farmingConfig().get(regionId);
            patches.stream()
                    .filter(config -> config.getType() == patchType.ordinal())
                    .filter(farmingConfig -> !isOvergrown(farmingConfig)) //filters patches with nothing
                    .filter(farmingConfig -> !isDead(farmingConfig)) //filters patches that are dead
                    .filter(config -> config.getCrop() != 0)
                    .filter(farmingConfig -> farmingConfig.getStage() < CropCache.getInstance().read(farmingConfig.getCrop()).getGrowthCycles())
                    .forEach(farmingConfig -> {
                        System.out.println("Executing Growth Cycle for patch: " + farmingConfig.getPatch());
                        this.updateCropHealthState(farmingConfig);
                    });
        });
    }


    public void doGrowthCycleForOfflinePlayer(PlayerCharacterContext player, PatchType patchType) {
        this.doGrowthCycle(player, patchType);
        PlayerCharacterContextDataAccessObject.getInstance().update(player);
    }

    public void doGrowthCycleForOnlinePlayer(Player player, PatchType patchType) {
        this.doGrowthCycle(player.getContext(), patchType);
        player.save();
        int regionX = player.absX >> 3;
        int regionY = player.absY >> 3;
        int regionId = ((regionX / 8) << 8) + (regionY / 8);
        if (player.getContext().getPlayerSaveData().farmingConfig().containsKey(regionId)) {
            final int varbit = player.getContext().getPlayerSaveData().farmingConfig().get(regionId).stream().mapToInt(FarmingConfig::varbit).sum();
            player.getPA().sendConfig(529, varbit);
        }
    }

    public void growWeeds() {
        PlayerCharacterContextDataAccessObject.getInstance().getAllEntries().forEach(player -> {
            player.getPlayerSaveData().farmingConfig().keySet().forEach(key -> {
                player.getPlayerSaveData().farmingConfig().get(key).stream()
                        .filter(config -> config.getCrop() == 0)
                        .filter(config -> config.getStage() != 0)
                        .forEach(config -> {
                            Optional<Player> playerOptional = PlayerHandler.getPlayer(player.getId());
                            if (playerOptional.isPresent()) {
                                Player p = playerOptional.get();
                                p.getContext().getPlayerSaveData().farmingConfig().get(key).stream()
                                        .filter(patch -> patch.getPatch() == config.getPatch())
                                        .findAny().ifPresent(patch -> patch.setStage(patch.getStage() - 1));
                                p.save();
                            } else {
                                config.setStage(config.getStage() - 1);
                                PlayerCharacterContextDataAccessObject.getInstance().update(player);
                            }
                        });
            });
        });
    }

    public void grow(PatchType patchType) {
        System.out.println(patchType + " Growth Cycle");
        PlayerCharacterContextDataAccessObject.getInstance().getAllEntries().forEach(player -> {
            if (PlayerHandler.getPlayer(player.getId()).isPresent()) {
                this.doGrowthCycleForOnlinePlayer(PlayerHandler.getPlayer(player.getId()).get(), patchType);
            } else {
                this.doGrowthCycleForOfflinePlayer(player, patchType);
            }
        });
    }

//    private ZonedDateTime getNextGrowthCycle(int minuteInterval) {
//        final ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));
//        final int minuteDifference = minuteInterval - (now.getMinute() % minuteInterval);
//        return now.plusMinutes(minuteDifference);
//    }

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

    private void createAllotments() {
        CropDAO.getInstance().create(new Crop(
                5318,
                5,
                1942,
                8,
                9,
                1,
                3,
                3,
                60,
                false,
                new CropPayment(995, 10000),
                PatchType.ALLOTMENT.ordinal(),
                6
        ));

        CropDAO.getInstance().create(new Crop(
                5319,
                5,
                1957,
                10,
                11,
                5,
                3,
                3,
                60,
                false,
                new CropPayment(995, 10000),
                PatchType.ALLOTMENT.ordinal(),
                13
        ));

        CropDAO.getInstance().create(new Crop(
                5324,
                5,
                1965,
                11,
                12,
                7,
                3,
                3,
                60,
                false,
                new CropPayment(995, 10000),
                PatchType.ALLOTMENT.ordinal(),
                20
        ));

        CropDAO.getInstance().create(new Crop( //tomato
                5322,
                5,
                1982,
                13,
                14,
                12,
                3,
                3,
                60,
                false,
                new CropPayment(995, 10000),
                PatchType.ALLOTMENT.ordinal(),
                27
        ));

        CropDAO.getInstance().create(new Crop( //corn
                5320,
                7,
                5986,
                17,
                19,
                20,
                3,
                3,
                60,
                false,
                new CropPayment(995, 10000),
                PatchType.ALLOTMENT.ordinal(),
                34
        ));

        CropDAO.getInstance().create(new Crop( //strawberry
                5323,
                7,
                5504,
                26,
                29,
                31,
                3,
                3,
                60,
                false,
                new CropPayment(995, 10000),
                PatchType.ALLOTMENT.ordinal(),
                43
        ));

        CropDAO.getInstance().create(new Crop( //watermelon
                5321,
                9,
                5982,
                49,
                55,
                47,
                3,
                3,
                60,
                false,
                new CropPayment(995, 10000),
                PatchType.ALLOTMENT.ordinal(),
                52
        ));
    }


    private FarmController() {
        this.random = new Random();
        this.createAllotments();

    }

    private final Random random;
    private final ScheduledExecutorService growthCycleExecutorService = Executors.newScheduledThreadPool(1);
}

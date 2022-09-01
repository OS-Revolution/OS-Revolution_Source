package ethos.runehub.world;

import ethos.model.players.ClientGameTimer;
import ethos.model.players.Player;
import ethos.model.players.PlayerHandler;
import ethos.runehub.RunehubConstants;
import ethos.runehub.db.PlayerCharacterContextDataAccessObject;
import ethos.runehub.entity.merchant.impl.exchange.ExchangePriceController;
import ethos.runehub.skill.support.sailing.voyage.Voyage;
import ethos.runehub.skill.support.sailing.voyage.VoyageDAO;
import ethos.runehub.world.wushanko.island.Island;
import ethos.runehub.world.wushanko.island.IslandDAO;
import ethos.runehub.world.wushanko.region.IslandRegion;
import ethos.runehub.world.wushanko.region.IslandRegionDAO;
import org.runehub.api.model.math.AdjustableNumber;
import org.runehub.api.model.math.impl.AdjustableInteger;
import org.runehub.api.model.math.impl.AdjustableLong;
import org.runehub.api.util.SkillDictionary;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class WorldSettingsController {

    public static final String SAVE_LOCATION = RunehubConstants.WORLD_SETTINGS;
    public static final ZonedDateTime DAILY_RESET = ZonedDateTime.now(ZoneId.of("UTC")).withHour(0).withMinute(0).withSecond(0);

    private static WorldSettingsController instance = null;

    public static WorldSettingsController getInstance() {
        if (instance == null)
            instance = new WorldSettingsController();
        return instance;
    }

    public void updateTimers() {
        if (isRunning(worldSettings.getBonusXpTimer()))
            worldSettings.getBonusXpTimer().decrement();
        if (isRunning(worldSettings.getDoubleDropRateTimer()))
            worldSettings.getDoubleDropRateTimer().decrement();

        Arrays.stream(SkillDictionary.Skill.values()).filter(skill -> worldSettings.getSkillPowerTimer().containsKey(skill.getId())).forEach(skill -> worldSettings.getSkillPowerTimer().get(skill.getId()).decrement());
        Arrays.stream(SkillDictionary.Skill.values()).filter(skill -> worldSettings.getSkillGainsTimer().containsKey(skill.getId())).forEach(skill -> worldSettings.getSkillGainsTimer().get(skill.getId()).decrement());
        Arrays.stream(SkillDictionary.Skill.values()).filter(skill -> worldSettings.getSkillEfficiencyTimer().containsKey(skill.getId())).forEach(skill -> worldSettings.getSkillEfficiencyTimer().get(skill.getId()).decrement());
        WorldSettingsController.getInstance().saveSettings();
    }

    public void saveSettings() {
        final WorldSettingsSerializer serializer = new WorldSettingsSerializer();
        serializer.write(new File(SAVE_LOCATION), serializer.serialize(worldSettings));
    }

    public void addBonusXp(Player player, int time) {
        PlayerHandler.executeGlobalMessage("@blu@[News]@red@ " + player.getName() + "@blu@ has activated a @red@" + time + "@blu@ hour XP boost for the server!");
        worldSettings.getBonusXpTimer().add((long) time * 60);
        PlayerHandler.getPlayers().forEach(p -> p.getPA().sendGameTimer(ClientGameTimer.EXPERIENCE, TimeUnit.MINUTES, Math.toIntExact(worldSettings.getBonusXpTimer().value())));
        this.saveSettings();
    }

    public void addMagicFind(Player player, int time) {
        PlayerHandler.executeGlobalMessage("@blu@[News]@red@ " + player.getName() + "@blu@ has activated a @red@" + time + "@blu@ hour Magic Find boost for the server!");
        worldSettings.getDoubleDropRateTimer().add((long) time * 60);
        PlayerHandler.getPlayers().forEach(p -> p.getPA().sendGameTimer(ClientGameTimer.DROPS, TimeUnit.MINUTES, Math.toIntExact(worldSettings.getDoubleDropRateTimer().value())));
        this.saveSettings();
    }

    public void addSkillPower(Player player, int time, int skillId) {
        PlayerHandler.executeGlobalMessage("@blu@[News]@red@ " + player.getName() + "@blu@ has activated a @red@" + time + "@blu@ hour "
                + SkillDictionary.getSkillNameFromId(skillId) + " power boost for the server!");
        this.addSkillPower(skillId, time);
        this.saveSettings();
    }

    public void addSkillEfficiency(Player player, int time, int skillId) {
        PlayerHandler.executeGlobalMessage("@blu@[News]@red@ " + player.getName() + "@blu@ has activated a @red@" + time + "@blu@ hour "
                + SkillDictionary.getSkillNameFromId(skillId) + " efficiency boost for the server!");
        this.addSkillEfficiency(skillId, time);
        this.saveSettings();
    }

    private void addSkillPower(int skillID, int time) {
        long hours = (long) time * 60;
        AdjustableNumber<Integer> timer = new AdjustableInteger(ClientGameTimer.SKILL_POWER.getTimerId());
        if (worldSettings.getSkillPowerTimer().containsKey(skillID)) {
            worldSettings.getSkillPowerTimer().get(skillID).add(hours);
        } else {
            worldSettings.getSkillPowerTimer().put(skillID, new AdjustableLong(hours));
        }
        PlayerHandler.getPlayers().forEach(p -> p.getPA().sendGameTimer(ClientGameTimer.getPowerTimerForSkillId(skillID), TimeUnit.MINUTES, Math.toIntExact(worldSettings.getSkillPowerTimer().get(skillID).value())));
    }

    private void addSkillEfficiency(int skillID, int time) {
        long hours = (long) time * 60;
        if (worldSettings.getSkillPowerTimer().containsKey(skillID)) {
            worldSettings.getSkillPowerTimer().get(skillID).add(hours);
        } else {
            worldSettings.getSkillPowerTimer().put(skillID, new AdjustableLong(hours));
        }
        PlayerHandler.getPlayers().forEach(p -> p.getPA().sendGameTimer(ClientGameTimer.getEfficiencyTimerForSkillId(skillID), TimeUnit.MINUTES, Math.toIntExact(worldSettings.getSkillEfficiencyTimer().get(skillID).value())));

    }

    private void sendTimer(Player player, ClientGameTimer timer, AdjustableLong number) {
        if (number.value() > 0)
            player.getPA().sendGameTimer(timer, TimeUnit.MINUTES, Math.toIntExact(number.value()));
    }

    private boolean isRunning(AdjustableLong timer) {
        return timer.value() > 0;
    }

    private boolean isEfficiencyRunning(int skillId) {
        return worldSettings.getSkillEfficiencyTimer().containsKey(skillId) && worldSettings.getSkillEfficiencyTimer().get(skillId).value() > 0;
    }

    private boolean isPowerRunning(int skillId) {
        return worldSettings.getSkillPowerTimer().containsKey(skillId) && worldSettings.getSkillPowerTimer().get(skillId).value() > 0;
    }

    private boolean isGainsRunning(int skillId) {
        return worldSettings.getSkillGainsTimer().containsKey(skillId) && worldSettings.getSkillGainsTimer().get(skillId).value() > 0;
    }


    private void sendInitializationMessage(Player player) {
        if (isRunning(worldSettings.getBonusXpTimer()))
            player.sendMessage("@blu@[News]@red@ Double Global XP @blu@is active");
        if (isRunning(worldSettings.getDoubleDropRateTimer()))
            player.sendMessage("@blu@[News]@red@Magic Find Boost @blu@is active @red@(50% Increased Drop Rate)");

        Arrays.stream(SkillDictionary.Skill.values()).filter(skill -> isEfficiencyRunning(skill.getId())).forEach(skill -> player.sendMessage("^[News] $" + skill.name().toLowerCase() + " $Efficiency $Boost is active" + " (#" + worldSettings.getEfficiencyModifier() + "x $Depletion $Chance $Reduction)"));
        Arrays.stream(SkillDictionary.Skill.values()).filter(skill -> isPowerRunning(skill.getId())).forEach(skill -> player.sendMessage("^[News] $" + skill.name().toLowerCase() + " $Power $Boost is active" + " (#" + worldSettings.getPowerModifer() + "x $Increased $Success $Chance)"));
        Arrays.stream(SkillDictionary.Skill.values()).filter(skill -> isGainsRunning(skill.getId())).forEach(skill -> player.sendMessage("^[News] $" + skill.name().toLowerCase() + " $Gains $Boost is active" + " (#" + worldSettings.getGainsModifier() + "x $Increased $XP)"));
    }

    public void initializeTimers(Player player) {
        this.sendTimer(player, ClientGameTimer.EXPERIENCE, worldSettings.getBonusXpTimer());
        this.sendTimer(player, ClientGameTimer.DROPS, worldSettings.getDoubleDropRateTimer());

        Arrays.stream(SkillDictionary.Skill.values()).filter(skill -> isEfficiencyRunning(skill.getId())).forEach(skill -> sendTimer(player, ClientGameTimer.getEfficiencyTimerForSkillId(skill.getId()), worldSettings.getSkillEfficiencyTimer().get(skill.getId())));
        Arrays.stream(SkillDictionary.Skill.values()).filter(skill -> isEfficiencyRunning(skill.getId())).forEach(skill -> sendTimer(player, ClientGameTimer.getPowerTimerForSkillId(skill.getId()), worldSettings.getSkillPowerTimer().get(skill.getId())));
        Arrays.stream(SkillDictionary.Skill.values()).filter(skill -> isEfficiencyRunning(skill.getId())).forEach(skill -> sendTimer(player, ClientGameTimer.getGainsTimerForSkillId(skill.getId()), worldSettings.getSkillGainsTimer().get(skill.getId())));

        this.sendInitializationMessage(player);
    }

    public void resetDailies() {
        System.out.println("Resetting Dailies");
        worldSettings.setLastDailyResetTimestamp(System.currentTimeMillis());
        ExchangePriceController.getInstance().updatePrices();
        PlayerCharacterContextDataAccessObject.getInstance().getAllEntries().forEach(ctx -> {
            if (PlayerHandler.isPlayerOn(ctx.getId())) {
                PlayerHandler.getPlayer(ctx.getId()).ifPresent(player -> {
                    player.save();
                    player.sendMessage("Your daily activities have been refreshed! Please log back in to do them.");
                });
            }
            ctx.getPlayerSaveData().setDailiesAvailable(true);
            PlayerCharacterContextDataAccessObject.getInstance().update(ctx);
        });
        saveSettings();
    }

    private void initializeDailies() {
        // Next run at midnight (UTC) - Replace with local time zone, if needed
        final ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));
        ZonedDateTime nextRun = now.withHour(0).withMinute(0).withSecond(0);

//        System.out.println("MS: " + (7.3 / 3600000));
        System.out.println("Now: " + now);
        System.out.println("Next Run: " + nextRun);

        // If midnight is in the past, add one day
        if (now.compareTo(nextRun) > 0) {
            nextRun = nextRun.plusDays(1);
        }

        // Get duration between now and midnight
        final Duration initialDelay = Duration.between(now, nextRun);

        System.out.println("Duration Until Next Run: " + initialDelay);

        // Schedule a task to run at midnight and then every day
        dailyScheduledExecutorService.scheduleAtFixedRate(this::resetDailies,
                initialDelay.toMillis(),
                Duration.ofDays(1).toMillis(),
                TimeUnit.MILLISECONDS);

        // Print time to midnight (UTC!), for debugging
        System.out.println("Time until first run: " + initialDelay);
    }

    private void startGameTick() {
        gameTickExecutorService.scheduleAtFixedRate(() -> {
                    System.out.println("Executing Game Tick");
                },
                0L,
                600L,
                TimeUnit.MILLISECONDS);
    }

    private void test() {
        IslandRegionDAO.getInstance().create(new IslandRegion(0,"The Arc"));
        IslandRegionDAO.getInstance().create(new IslandRegion(1,"The Skull"));
        IslandRegionDAO.getInstance().create(new IslandRegion(2,"The Hook"));
        IslandRegionDAO.getInstance().create(new IslandRegion(3,"The Scythe"));
        IslandRegionDAO.getInstance().create(new IslandRegion(4,"The Bowl"));
        IslandRegionDAO.getInstance().create(new IslandRegion(5,"The Pincers"));
        IslandRegionDAO.getInstance().create(new IslandRegion(6,"The Loop"));
        IslandRegionDAO.getInstance().create(new IslandRegion(7,"The Shield"));
        IslandRegionDAO.getInstance().create(new IslandRegion(8,"Uncharted Waters"));

        IslandDAO.getInstance().create(new Island(0,"Tuai Leit"));
        IslandDAO.getInstance().create(new Island(1,"Whale's Maw"));
        IslandDAO.getInstance().create(new Island(2,"Waiko"));
        IslandDAO.getInstance().create(new Island(3,"Goshima"));
        IslandDAO.getInstance().create(new Island(4,"The Islands that once were Turtles"));
        IslandDAO.getInstance().create(new Island(5,"Aminishi"));
        IslandDAO.getInstance().create(new Island(6,"Cyclosis"));

        IslandDAO.getInstance().create(new Island(7,"Ai Jei"));
        IslandDAO.getInstance().create(new Island(8,"Hanto"));
        IslandDAO.getInstance().create(new Island(9,"The Sunlit Veil"));
        IslandDAO.getInstance().create(new Island(10,"Thalassia"));
        IslandDAO.getInstance().create(new Island(11,"Echo Bay"));
        IslandDAO.getInstance().create(new Island(12,"The Siren's Shell"));

        IslandDAO.getInstance().create(new Island(13,"Rei Ti"));
        IslandDAO.getInstance().create(new Island(14,"The Earthquake Straits"));
        IslandDAO.getInstance().create(new Island(15,"Teardrop Islands"));
        IslandDAO.getInstance().create(new Island(16,"Rapa Causeway"));
        IslandDAO.getInstance().create(new Island(17,"Bay of Playful Sirens"));
        IslandDAO.getInstance().create(new Island(18,"The Fistmarks of Genma"));
        IslandDAO.getInstance().create(new Island(19,"The Forgotten Chimera Straits"));
        IslandDAO.getInstance().create(new Island(20,"Hubbub's Lovetubs"));
        IslandDAO.getInstance().create(new Island(21,"Carlos's Fusion Kitchen"));

        IslandDAO.getInstance().create(new Island(22,"Ren Bo"));
        IslandDAO.getInstance().create(new Island(23,"Haranu"));
        IslandDAO.getInstance().create(new Island(24,"The Islands that Reflect the Moon"));
        IslandDAO.getInstance().create(new Island(25,"Aloft Dagger"));
        IslandDAO.getInstance().create(new Island(26,"Jade Straits"));
        IslandDAO.getInstance().create(new Island(27,"Wind's Home"));

        IslandDAO.getInstance().create(new Island(28,"Falling Blossom"));
        IslandDAO.getInstance().create(new Island(29,"Isle of Juniper"));
        IslandDAO.getInstance().create(new Island(30,"Tokoko"));
        IslandDAO.getInstance().create(new Island(31,"Kei Pi"));
        IslandDAO.getInstance().create(new Island(32,"Tattanogi"));
        IslandDAO.getInstance().create(new Island(33,"Crescent Island"));
        IslandDAO.getInstance().create(new Island(34,"Glittercaves"));

        IslandDAO.getInstance().create(new Island(35,"New Heritage"));
        IslandDAO.getInstance().create(new Island(36,"Khanoka"));
        IslandDAO.getInstance().create(new Island(37,"The Hole in the World"));
        IslandDAO.getInstance().create(new Island(38,"Flou Tar-Shei"));
        IslandDAO.getInstance().create(new Island(39,"Ashihama"));
        IslandDAO.getInstance().create(new Island(40,"Yamada Island"));
        IslandDAO.getInstance().create(new Island(41,"Shambling Lair"));

        IslandDAO.getInstance().create(new Island(42,"Dhar Pei's Vantage"));
        IslandDAO.getInstance().create(new Island(43,"The Reef that Lies to Mapmakers"));
        IslandDAO.getInstance().create(new Island(44,"Straits of the Oyster Pearl"));
        IslandDAO.getInstance().create(new Island(45,"Light Under the Sea"));

        IslandDAO.getInstance().create(new Island(46,"Tengu Archipelago"));
        IslandDAO.getInstance().create(new Island(47,"Bladewing Crag"));
        IslandDAO.getInstance().create(new Island(48,"The Lair of Tavi and Vynal"));
        IslandDAO.getInstance().create(new Island(49,"Exile's Point"));
        IslandDAO.getInstance().create(new Island(50,"The Island that is Blamed for Nothing"));
        IslandDAO.getInstance().create(new Island(51,"Adamant Isles"));

//        VoyageDAO.getInstance().create(new Voyage(1,new Voyage(
//                1,
//                "Bamboo Runner",
//                10,10,10,
//        )));
    }

    private WorldSettingsController() {
        try {
            this.worldSettings = new WorldSettingsSerializer().read(new File(SAVE_LOCATION));
            this.initializeDailies();
//            this.startGameTick();
//            this.test();
        } catch (IOException e) {
            this.worldSettings = new WorldSettings();
            this.saveSettings();
            Logger.getLogger("World Logger").severe("Failed to load world settings.");
        }
    }


    public WorldSettings getWorldSettings() {
        return worldSettings;
    }

    private WorldSettings worldSettings;
    private final ScheduledExecutorService dailyScheduledExecutorService = Executors.newScheduledThreadPool(1);
    private final ScheduledExecutorService gameTickExecutorService = Executors.newScheduledThreadPool(1);
}

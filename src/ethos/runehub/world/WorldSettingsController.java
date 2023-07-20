package ethos.runehub.world;

import ethos.model.players.ClientGameTimer;
import ethos.model.players.Player;
import ethos.model.players.PlayerHandler;
import ethos.runehub.RunehubConstants;
import ethos.runehub.TimeUtils;
import ethos.runehub.db.PlayerCharacterContextDataAccessObject;
import ethos.runehub.entity.merchant.impl.exchange.ExchangePriceController;
import ethos.runehub.event.DailyResetEvent;
import ethos.runehub.event.FixedScheduleEvent;
import ethos.runehub.event.FixedScheduledEventController;
import ethos.runehub.event.chest.*;
import ethos.runehub.event.dnd.*;
import ethos.runehub.event.shop.impl.GeneralStoreRestockEvent;
import ethos.runehub.skill.gathering.farming.FarmController;
import ethos.util.Misc;
import org.runehub.api.model.math.AdjustableNumber;
import org.runehub.api.model.math.impl.AdjustableInteger;
import org.runehub.api.model.math.impl.AdjustableLong;
import org.runehub.api.util.SkillDictionary;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
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
        Arrays.stream(worldSettings.getSkillStationExpirationTimeMS()).filter(this::isRunning).forEach(timer -> timer.subtract(60000L));
        Arrays.stream(SkillDictionary.Skill.values()).filter(skill -> worldSettings.getSkillPowerTimer().containsKey(skill.getId())).forEach(skill -> worldSettings.getSkillPowerTimer().get(skill.getId()).decrement());
        Arrays.stream(SkillDictionary.Skill.values()).filter(skill -> worldSettings.getSkillGainsTimer().containsKey(skill.getId())).forEach(skill -> worldSettings.getSkillGainsTimer().get(skill.getId()).decrement());
        Arrays.stream(SkillDictionary.Skill.values()).filter(skill -> worldSettings.getSkillEfficiencyTimer().containsKey(skill.getId())).forEach(skill -> worldSettings.getSkillEfficiencyTimer().get(skill.getId()).decrement());
        WorldSettingsController.getInstance().saveSettings();
    }

    public void saveSettings() {
        final WorldSettingsSerializer serializer = new WorldSettingsSerializer();
        serializer.write(new File(SAVE_LOCATION), serializer.serialize(worldSettings));
    }

    public void addTicketToSkillStation(Player player, int stationId, int amount) {
        int ticketId = -1;
        switch (stationId) {
            case 6799:
                ticketId = 5020;
                break;
            case 878:
                ticketId = 5022;
                break;
            case 13542:
                ticketId = 5021;
                break;
            case 15468:
                ticketId = 5023;
                break;
            case 11017:
                ticketId = 8548;
                break;
        }
        if (player.getItems().playerHasItem(ticketId, amount)) {
            worldSettings.setSkillStationExpirationTimeMS(stationId, amount * 300000L);
            player.getItems().deleteItem2(ticketId, amount);
            player.sendMessage("You activate the station for " + (amount * 5) + " minutes.");
            saveSettings();
        }
    }

    public void addBonusXp(int time) {
        worldSettings.getBonusXpTimer().add((long) time * 60);
        PlayerHandler.getPlayers().forEach(p -> p.getPA().sendGameTimer(ClientGameTimer.EXPERIENCE, TimeUnit.MINUTES, Math.toIntExact(worldSettings.getBonusXpTimer().value())));
        this.saveSettings();
    }

    public void addBonusXp(Player player, int time) {
        PlayerHandler.executeGlobalMessage("^News $" + player.getName() + " has activated a $" + time + " hour $XP $boost for the server!");
        this.addBonusXp(time);
    }

    public void addMagicFind(Player player, int time) {
        PlayerHandler.executeGlobalMessage("^News $" + player.getName() + " has activated a $" + time + " hour $Magic $Find boost for the server!");
        worldSettings.getDoubleDropRateTimer().add((long) time * 60);
        PlayerHandler.getPlayers().forEach(p -> p.getPA().sendGameTimer(ClientGameTimer.DROPS, TimeUnit.MINUTES, Math.toIntExact(worldSettings.getDoubleDropRateTimer().value())));
        this.saveSettings();
    }

    public void addSkillPower(Player player, int time, int skillId) {
        PlayerHandler.executeGlobalMessage("^News $" + player.getName() + " has activated a $" + time + " hour $"
                + Misc.capitalize(SkillDictionary.getSkillNameFromId(skillId).toLowerCase()) + " $power boost for the server!");
        this.addSkillPower(skillId, time);
        this.saveSettings();
    }

    public void addSkillEfficiency(Player player, int time, int skillId) {
        PlayerHandler.executeGlobalMessage("^News $" + player.getName() + " has activated a $" + time + " hour $"
                + Misc.capitalize(SkillDictionary.getSkillNameFromId(skillId).toLowerCase()) + " $efficiency boost for the server!");
        this.addSkillEfficiency(skillId, time);
        this.saveSettings();
    }

    public void addSkillPower(int skillID, int time) {
        long hours = (long) time * 60;
        AdjustableNumber<Integer> timer = new AdjustableInteger(ClientGameTimer.SKILL_POWER.getTimerId());
        if (worldSettings.getSkillPowerTimer().containsKey(skillID)) {
            worldSettings.getSkillPowerTimer().get(skillID).add(hours);
        } else {
            worldSettings.getSkillPowerTimer().put(skillID, new AdjustableLong(hours));
        }
        PlayerHandler.getPlayers().forEach(p -> p.getPA().sendGameTimer(ClientGameTimer.getPowerTimerForSkillId(skillID), TimeUnit.MINUTES, Math.toIntExact(worldSettings.getSkillPowerTimer().get(skillID).value())));
    }

    public void addSkillEfficiency(int skillID, int time) {
        long hours = (long) time * 60;
        if (worldSettings.getSkillEfficiencyTimer().containsKey(skillID)) {
            worldSettings.getSkillEfficiencyTimer().get(skillID).add(hours);
        } else {
            worldSettings.getSkillEfficiencyTimer().put(skillID, new AdjustableLong(hours));
        }
        PlayerHandler.getPlayers().stream().filter(Objects::nonNull).forEach(p -> p.getPA().sendGameTimer(ClientGameTimer.getEfficiencyTimerForSkillId(skillID), TimeUnit.MINUTES, Math.toIntExact(worldSettings.getSkillEfficiencyTimer().get(skillID).value())));

    }

    private void sendTimer(Player player, ClientGameTimer timer, AdjustableLong number) {
        if (number != null && number.value() > 0)
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

        Arrays.stream(SkillDictionary.Skill.values()).filter(skill -> isEfficiencyRunning(skill.getId())).forEach(skill -> player.sendMessage("^News $" + Misc.capitalize(skill.name().toLowerCase()) + " $Efficiency $boost is active" + " ( $" + worldSettings.getEfficiencyModifier() + "x Depletion chance reduction)"));
        Arrays.stream(SkillDictionary.Skill.values()).filter(skill -> isPowerRunning(skill.getId())).forEach(skill -> player.sendMessage("^News $" + Misc.capitalize(skill.name().toLowerCase()) + " $Power $boost is active" + " ( $" + worldSettings.getPowerModifer() + "x Increased success chance)"));
        Arrays.stream(SkillDictionary.Skill.values()).filter(skill -> isGainsRunning(skill.getId())).forEach(skill -> player.sendMessage("^News $" + Misc.capitalize(skill.name().toLowerCase()) + " $Gains $boost is active" + " ( $" + worldSettings.getGainsModifier() + "x Increased XP)"));
    }

    public void initializeTimers(Player player) {
        this.sendTimer(player, ClientGameTimer.EXPERIENCE, worldSettings.getBonusXpTimer());
        this.sendTimer(player, ClientGameTimer.DROPS, worldSettings.getDoubleDropRateTimer());

        Arrays.stream(SkillDictionary.Skill.values()).filter(skill -> isEfficiencyRunning(skill.getId())).forEach(skill -> sendTimer(player, ClientGameTimer.getEfficiencyTimerForSkillId(skill.getId()), worldSettings.getSkillEfficiencyTimer().get(skill.getId())));
        Arrays.stream(SkillDictionary.Skill.values()).filter(skill -> isEfficiencyRunning(skill.getId())).forEach(skill -> sendTimer(player, ClientGameTimer.getPowerTimerForSkillId(skill.getId()), worldSettings.getSkillPowerTimer().get(skill.getId())));
        Arrays.stream(SkillDictionary.Skill.values()).filter(skill -> isEfficiencyRunning(skill.getId())).forEach(skill -> sendTimer(player, ClientGameTimer.getGainsTimerForSkillId(skill.getId()), worldSettings.getSkillGainsTimer().get(skill.getId())));

        this.sendInitializationMessage(player);
    }


//    private void initializeDailies() {
//        // Next run at midnight (UTC) - Replace with local time zone, if needed
//        final ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));
//        ZonedDateTime nextRun = now.withHour(0).withMinute(0).withSecond(0);
//
////        System.out.println("MS: " + (7.3 / 3600000));
//        System.out.println("Last Daily Reset: " + TimeUtils.getDurationString(TimeUtils.getDurationBetween(worldSettings.getLastDailyResetTimestamp(),now.toInstant().toEpochMilli())));
//        System.out.println("Now: " + now);
//        System.out.println("Next Run: " + nextRun);
//
//        // If midnight is in the past, add one day
//        if (now.compareTo(nextRun) > 0) {
//            nextRun = nextRun.plusDays(1);
//        }
//
//        // Get duration between now and midnight
//        final Duration initialDelay = Duration.between(now, nextRun);
//
//        System.out.println("Duration Until Next Run: " + TimeUtils.getDurationString(initialDelay));
//
//        // Schedule a task to run at midnight and then every day
//        dailyScheduledExecutorService.scheduleAtFixedRate(this::resetDailies,
//                initialDelay.toMillis(),
//                Duration.ofDays(1).toMillis(),
//                TimeUnit.MILLISECONDS);
//
//        // Print time to midnight (UTC!), for debugging
//        System.out.println("Time until first run: " + TimeUtils.getDurationString(initialDelay));
//    }


    private void startGameTick() {
        gameTickExecutorService.scheduleAtFixedRate(() -> {
                    System.out.println("Executing Game Tick");
                },
                0L,
                600L,
                TimeUnit.MILLISECONDS);
    }

    public String getTimeUntilNextSeason() {
        return TimeUtils.getShortDurationString(
                TimeUtils.getDurationBetween(System.currentTimeMillis(),
                        FixedScheduledEventController.getInstance().getNextCycle(FixedScheduledEventController.getInstance().getFixedScheduleEvents()[6]).toInstant().toEpochMilli()));
    }

    public int getSkillOfTheHourEffect(int sId) {
        return skillOfTheHourEffect[sId];
    }

    public void setSkillOfTheHourEffect(int sId, int eId) {
        skillOfTheHourEffect[sId] = eId;
    }

    public String getWeekendBonusName() {
        switch (worldSettings.getWeekendEventId()) {
            case 1:
                return "Double_XP_Weekend";
            case 2:
                return "Gheed's_Fortune";
            case 3:
                return "Double_Slayer_Points_Weekend";
            case 4:
                return "Boon_of_the_Hoarder";
            case 5:
                return "Sailors_Delight";
            case 6:
                return "Double_PvM_Points_Weekend";
        }
        return "inactive";
    }

    public Map<Integer, PromotionalChestEvent> getPromotionalEvents() {
        return promotionalEvents;
    }

    private WorldSettingsController() {
        this.skillOfTheHourEffect = new int[SkillDictionary.Skill.values().length];
        this.promotionalEvents = new HashMap<>();
        promotionalEvents.put(1, new PrismaniaPromotionalChestEvent());
        promotionalEvents.put(2, new LootDuelsPromotionalChestEvent());
        promotionalEvents.put(3, new NatureSentinelPromotionalChestEvent());
        try {
            this.worldSettings = new WorldSettingsSerializer().read(new File(SAVE_LOCATION));
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
    private final int[] skillOfTheHourEffect;

    private final Map<Integer, PromotionalChestEvent> promotionalEvents;
}

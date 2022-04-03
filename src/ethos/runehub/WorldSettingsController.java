package ethos.runehub;

import ethos.Server;
import ethos.model.players.ClientGameTimer;
import ethos.model.players.Player;
import ethos.model.players.PlayerHandler;
import org.rhd.api.math.impl.AdjustableLong;
import org.runehub.api.model.math.AdjustableNumber;
import org.runehub.api.model.math.impl.AdjustableInteger;
import org.runehub.api.util.SkillDictionary;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class WorldSettingsController {

    public static final String SAVE_LOCATION = "Data/rune-script/world-settings.json";

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
        if (isRunning(worldSettings.getWoodcuttingEfficiencyTimer()))
            worldSettings.getWoodcuttingEfficiencyTimer().decrement();
        if (isRunning(worldSettings.getWoodcuttingPowerTimer()))
            worldSettings.getWoodcuttingPowerTimer().decrement();
        if (isRunning(worldSettings.getWoodcuttingGainsTimer()))
            worldSettings.getWoodcuttingGainsTimer().decrement();
        if (isRunning(worldSettings.getMiningEfficiencyTimer()))
            worldSettings.getMiningEfficiencyTimer().decrement();
        if (isRunning(worldSettings.getMiningPowerTimer()))
            worldSettings.getMiningPowerTimer().decrement();
        if (isRunning(worldSettings.getMiningGainsTimer()))
            worldSettings.getMiningGainsTimer().decrement();
        if (isRunning(worldSettings.getFishingEfficiencyTimer()))
            worldSettings.getFishingEfficiencyTimer().decrement();
        if (isRunning(worldSettings.getFishingPowerTimer()))
            worldSettings.getFishingPowerTimer().decrement();
        if (isRunning(worldSettings.getFishingGainsTimer()))
            worldSettings.getFishingGainsTimer().decrement();
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
        switch (skillID) {
            case 8:
                worldSettings.getWoodcuttingPowerTimer().add(hours);
                PlayerHandler.getPlayers().forEach(p -> p.getPA().sendGameTimer(ClientGameTimer.WOODCUTTING_POWER, TimeUnit.MINUTES, Math.toIntExact(worldSettings.getWoodcuttingPowerTimer().value())));
                break;
            case 10:
                worldSettings.getFishingPowerTimer().add(hours);
                PlayerHandler.getPlayers().forEach(p -> p.getPA().sendGameTimer(ClientGameTimer.FISHING_POWER, TimeUnit.MINUTES, Math.toIntExact(worldSettings.getFishingPowerTimer().value())));
                break;
            case 14:
                worldSettings.getMiningPowerTimer().add(hours);
                PlayerHandler.getPlayers().forEach(p -> p.getPA().sendGameTimer(ClientGameTimer.MINING_POWER, TimeUnit.MINUTES, Math.toIntExact(worldSettings.getMiningPowerTimer().value())));
                break;
            default:
                break;
        }

    }

    private void addSkillEfficiency(int skillID, int time) {
        long hours = (long) time * 60;
        switch (skillID) {
            case 8:
                worldSettings.getWoodcuttingEfficiencyTimer().add(hours);
                PlayerHandler.getPlayers().forEach(p -> p.getPA().sendGameTimer(ClientGameTimer.WOODCUTTING_EFFICIENCY, TimeUnit.MINUTES, Math.toIntExact(worldSettings.getWoodcuttingEfficiencyTimer().value())));
                break;
            case 10:
                worldSettings.getFishingEfficiencyTimer().add(hours);
                PlayerHandler.getPlayers().forEach(p -> p.getPA().sendGameTimer(ClientGameTimer.FISHING_EFFICIENCY, TimeUnit.MINUTES, Math.toIntExact(worldSettings.getFishingEfficiencyTimer().value())));
                break;
            case 14:
                worldSettings.getMiningEfficiencyTimer().add(hours);
                PlayerHandler.getPlayers().forEach(p -> p.getPA().sendGameTimer(ClientGameTimer.MINING_EFFICIENCY, TimeUnit.MINUTES, Math.toIntExact(worldSettings.getMiningEfficiencyTimer().value())));
                break;
            default:
                break;
        }

    }

    private void sendTimer(Player player, ClientGameTimer timer, AdjustableLong number) {
        if (number.value() > 0)
            player.getPA().sendGameTimer(timer, TimeUnit.MINUTES, Math.toIntExact(number.value()));
    }

    private boolean isRunning(AdjustableLong timer) {
        return timer.value() > 0;
    }


    private void sendInitializationMessage(Player player) {
        if (isRunning(worldSettings.getBonusXpTimer()))
            player.sendMessage("@blu@[News]@red@ Double Global XP @blu@is active");
         if (isRunning(worldSettings.getDoubleDropRateTimer()))
            player.sendMessage("@blu@[News]@red@Magic Find Boost @blu@is active @red@(50% Increased Drop Rate)");
         if (isRunning(worldSettings.getWoodcuttingEfficiencyTimer()))
            player.sendMessage("@blu@[News]@red@ Woodcutting Efficiency Boost @blu@is active" + " (@red@" + worldSettings.getEfficiencyModifier()  + "x @blu@Deplete Chance Reduction)");
         if (isRunning(worldSettings.getWoodcuttingPowerTimer()))
            player.sendMessage("@blu@[News]@red@ Woodcutting Power Boost @blu@is active" + " (@red@" + worldSettings.getPowerModifer()  + "x @blu@Chop Chance)");
         if (isRunning(worldSettings.getWoodcuttingGainsTimer()))
            player.sendMessage("@blu@[News]@red@ Woodcutting Gains Boost @blu@is active" + " (@red@" + worldSettings.getGainsModifier()  + "x @blu@Woodcutting XP)");
         if (isRunning(worldSettings.getMiningEfficiencyTimer()))
            player.sendMessage("@blu@[News]@red@ Mining Efficiency Boost @blu@is active" + " (@red@" + worldSettings.getEfficiencyModifier()  + "x @blu@Deplete Chance Reduction)");
         if (isRunning(worldSettings.getMiningPowerTimer()))
            player.sendMessage("@blu@[News]@red@ Mining Power Boost @blu@is active" + " (@red@" + worldSettings.getPowerModifer()  + "x @blu@Mine Chance)");
         if (isRunning(worldSettings.getMiningGainsTimer()))
            player.sendMessage("@blu@[News]@red@ Mining Gains Boost @blu@is active" + " (@red@" + worldSettings.getGainsModifier()  + "x @blu@Mining XP)");
         if (isRunning(worldSettings.getFishingEfficiencyTimer()))
            player.sendMessage("@blu@[News]@red@ Fishing Efficiency Boost @blu@is active" + " (@red@" + worldSettings.getEfficiencyModifier()  + "x @blu@Deplete Chance Reduction)");
         if (isRunning(worldSettings.getFishingPowerTimer()))
            player.sendMessage("@blu@[News]@red@ Fishing Power Boost @blu@is active" + " (@red@" + worldSettings.getPowerModifer()  + "x @blu@Catch Chance)");
         if (isRunning(worldSettings.getFishingGainsTimer()))
            player.sendMessage("@blu@[News]@red@ Fishing Gains Boost @blu@is active" + " (@red@" + worldSettings.getGainsModifier()  + "x @blu@Fishing XP)");
    }

    public void initializeTimers(Player player) {
        this.sendTimer(player, ClientGameTimer.EXPERIENCE, worldSettings.getBonusXpTimer());
        this.sendTimer(player, ClientGameTimer.DROPS, worldSettings.getDoubleDropRateTimer());

        this.sendTimer(player, ClientGameTimer.WOODCUTTING_GAINS, worldSettings.getWoodcuttingGainsTimer());
        this.sendTimer(player, ClientGameTimer.WOODCUTTING_EFFICIENCY, worldSettings.getWoodcuttingEfficiencyTimer());
        this.sendTimer(player, ClientGameTimer.WOODCUTTING_POWER, worldSettings.getWoodcuttingPowerTimer());

        this.sendTimer(player, ClientGameTimer.MINING_GAINS, worldSettings.getMiningGainsTimer());
        this.sendTimer(player, ClientGameTimer.MINING_EFFICIENCY, worldSettings.getMiningEfficiencyTimer());
        this.sendTimer(player, ClientGameTimer.MINING_POWER, worldSettings.getMiningPowerTimer());

        this.sendTimer(player, ClientGameTimer.FISHING_GAINS, worldSettings.getFishingGainsTimer());
        this.sendTimer(player, ClientGameTimer.FISHING_EFFICIENCY, worldSettings.getFishingEfficiencyTimer());
        this.sendTimer(player, ClientGameTimer.FISHING_POWER, worldSettings.getFishingPowerTimer());

        this.sendTimer(player, ClientGameTimer.SKILL_GAINS, worldSettings.getSkillGainsTimer());
        this.sendTimer(player, ClientGameTimer.SKILL_EFFICIENCY, worldSettings.getSkillEfficiencyTimer());
        this.sendTimer(player, ClientGameTimer.SKILL_POWER, worldSettings.getSkillPowerTimer());

        this.sendInitializationMessage(player);
    }

    private WorldSettingsController() {
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
}

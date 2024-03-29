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
import java.util.Arrays;
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

        Arrays.stream(SkillDictionary.Skill.values()).filter(skill -> worldSettings.getSkillPowerTimer().containsKey(skill.getId())).forEach(skill -> worldSettings.getSkillPowerTimer().get(skill.getId()).decrement());
        Arrays.stream(SkillDictionary.Skill.values()).filter(skill -> worldSettings.getSkillGainsTimer().containsKey(skill.getId())).forEach(skill -> worldSettings.getSkillGainsTimer().get(skill.getId()).decrement());
        Arrays.stream(SkillDictionary.Skill.values()).filter(skill -> worldSettings.getSkillEfficiencyTimer().containsKey(skill.getId())).forEach(skill -> worldSettings.getSkillEfficiencyTimer().get(skill.getId()).decrement());
//        if (isRunning(worldSettings.getWoodcuttingEfficiencyTimer()))
//            worldSettings.getWoodcuttingEfficiencyTimer().decrement();
//        if (isRunning(worldSettings.getWoodcuttingPowerTimer()))
//            worldSettings.getWoodcuttingPowerTimer().decrement();
//        if (isRunning(worldSettings.getWoodcuttingGainsTimer()))
//            worldSettings.getWoodcuttingGainsTimer().decrement();
//        if (isRunning(worldSettings.getMiningEfficiencyTimer()))
//            worldSettings.getMiningEfficiencyTimer().decrement();
//        if (isRunning(worldSettings.getMiningPowerTimer()))
//            worldSettings.getMiningPowerTimer().decrement();
//        if (isRunning(worldSettings.getMiningGainsTimer()))
//            worldSettings.getMiningGainsTimer().decrement();
//        if (isRunning(worldSettings.getFishingEfficiencyTimer()))
//            worldSettings.getFishingEfficiencyTimer().decrement();
//        if (isRunning(worldSettings.getFishingPowerTimer()))
//            worldSettings.getFishingPowerTimer().decrement();
//        if (isRunning(worldSettings.getFishingGainsTimer()))
//            worldSettings.getFishingGainsTimer().decrement();
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
        if(worldSettings.getSkillPowerTimer().containsKey(skillID)) {
            worldSettings.getSkillPowerTimer().get(skillID).add(hours);
        } else {
            worldSettings.getSkillPowerTimer().put(skillID,new AdjustableLong(hours));
        }
        PlayerHandler.getPlayers().forEach(p -> p.getPA().sendGameTimer(ClientGameTimer.getPowerTimerForSkillId(skillID), TimeUnit.MINUTES, Math.toIntExact(worldSettings.getSkillPowerTimer().get(skillID).value())));
    }

    private void addSkillEfficiency(int skillID, int time) {
        long hours = (long) time * 60;
        if(worldSettings.getSkillPowerTimer().containsKey(skillID)) {
            worldSettings.getSkillPowerTimer().get(skillID).add(hours);
        } else {
            worldSettings.getSkillPowerTimer().put(skillID,new AdjustableLong(hours));
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

        Arrays.stream(SkillDictionary.Skill.values()).filter(skill -> isEfficiencyRunning(skill.getId())).forEach(skill -> player.sendMessage("^[News] $" + skill.name().toLowerCase() + " $Efficiency $Boost is active" + " (#" + worldSettings.getEfficiencyModifier()  + "x $Depletion $Chance $Reduction)"));
        Arrays.stream(SkillDictionary.Skill.values()).filter(skill -> isPowerRunning(skill.getId())).forEach(skill -> player.sendMessage("^[News] $" + skill.name().toLowerCase() + " $Power $Boost is active" + " (#" + worldSettings.getPowerModifer()  + "x $Increased $Success $Chance)"));
        Arrays.stream(SkillDictionary.Skill.values()).filter(skill -> isGainsRunning(skill.getId())).forEach(skill -> player.sendMessage("^[News] $" + skill.name().toLowerCase() + " $Gains $Boost is active" + " (#" + worldSettings.getGainsModifier()  + "x $Increased $XP)"));

//        if (isEfficiencyRunning(SkillDictionary.Skill.WOODCUTTING.getId()))
//            player.sendMessage("@blu@[News]@red@ Woodcutting Efficiency Boost @blu@is active" + " (@red@" + worldSettings.getEfficiencyModifier()  + "x @blu@Deplete Chance Reduction)");
//         if (isRunning(worldSettings.getWoodcuttingPowerTimer()))
//            player.sendMessage("@blu@[News]@red@ Woodcutting Power Boost @blu@is active" + " (@red@" + worldSettings.getPowerModifer()  + "x @blu@Chop Chance)");
//         if (isRunning(worldSettings.getWoodcuttingGainsTimer()))
//            player.sendMessage("@blu@[News]@red@ Woodcutting Gains Boost @blu@is active" + " (@red@" + worldSettings.getGainsModifier()  + "x @blu@Woodcutting XP)");
//         if (isRunning(worldSettings.getMiningEfficiencyTimer()))
//            player.sendMessage("@blu@[News]@red@ Mining Efficiency Boost @blu@is active" + " (@red@" + worldSettings.getEfficiencyModifier()  + "x @blu@Deplete Chance Reduction)");
//         if (isRunning(worldSettings.getMiningPowerTimer()))
//            player.sendMessage("@blu@[News]@red@ Mining Power Boost @blu@is active" + " (@red@" + worldSettings.getPowerModifer()  + "x @blu@Mine Chance)");
//         if (isRunning(worldSettings.getMiningGainsTimer()))
//            player.sendMessage("@blu@[News]@red@ Mining Gains Boost @blu@is active" + " (@red@" + worldSettings.getGainsModifier()  + "x @blu@Mining XP)");
//         if (isRunning(worldSettings.getFishingEfficiencyTimer()))
//            player.sendMessage("@blu@[News]@red@ Fishing Efficiency Boost @blu@is active" + " (@red@" + worldSettings.getEfficiencyModifier()  + "x @blu@Deplete Chance Reduction)");
//         if (isRunning(worldSettings.getFishingPowerTimer()))
//            player.sendMessage("@blu@[News]@red@ Fishing Power Boost @blu@is active" + " (@red@" + worldSettings.getPowerModifer()  + "x @blu@Catch Chance)");
//         if (isRunning(worldSettings.getFishingGainsTimer()))
//            player.sendMessage("@blu@[News]@red@ Fishing Gains Boost @blu@is active" + " (@red@" + worldSettings.getGainsModifier()  + "x @blu@Fishing XP)");
    }

    public void initializeTimers(Player player) {
        this.sendTimer(player, ClientGameTimer.EXPERIENCE, worldSettings.getBonusXpTimer());
        this.sendTimer(player, ClientGameTimer.DROPS, worldSettings.getDoubleDropRateTimer());

        Arrays.stream(SkillDictionary.Skill.values()).filter(skill -> isEfficiencyRunning(skill.getId())).forEach(skill -> sendTimer(player,ClientGameTimer.getEfficiencyTimerForSkillId(skill.getId()),worldSettings.getSkillEfficiencyTimer().get(skill.getId())));
        Arrays.stream(SkillDictionary.Skill.values()).filter(skill -> isEfficiencyRunning(skill.getId())).forEach(skill -> sendTimer(player,ClientGameTimer.getPowerTimerForSkillId(skill.getId()),worldSettings.getSkillPowerTimer().get(skill.getId())));
        Arrays.stream(SkillDictionary.Skill.values()).filter(skill -> isEfficiencyRunning(skill.getId())).forEach(skill -> sendTimer(player,ClientGameTimer.getGainsTimerForSkillId(skill.getId()),worldSettings.getSkillGainsTimer().get(skill.getId())));
//        this.sendTimer(player, ClientGameTimer.WOODCUTTING_GAINS, worldSettings.getWoodcuttingGainsTimer());
//        this.sendTimer(player, ClientGameTimer.WOODCUTTING_EFFICIENCY, worldSettings.getWoodcuttingEfficiencyTimer());
//        this.sendTimer(player, ClientGameTimer.WOODCUTTING_POWER, worldSettings.getWoodcuttingPowerTimer());
//
//        this.sendTimer(player, ClientGameTimer.MINING_GAINS, worldSettings.getMiningGainsTimer());
//        this.sendTimer(player, ClientGameTimer.MINING_EFFICIENCY, worldSettings.getMiningEfficiencyTimer());
//        this.sendTimer(player, ClientGameTimer.MINING_POWER, worldSettings.getMiningPowerTimer());
//
//        this.sendTimer(player, ClientGameTimer.FISHING_GAINS, worldSettings.getFishingGainsTimer());
//        this.sendTimer(player, ClientGameTimer.FISHING_EFFICIENCY, worldSettings.getFishingEfficiencyTimer());
//        this.sendTimer(player, ClientGameTimer.FISHING_POWER, worldSettings.getFishingPowerTimer());
//
//        this.sendTimer(player, ClientGameTimer.SKILL_GAINS, worldSettings.getSkillGainsTimer());
//        this.sendTimer(player, ClientGameTimer.SKILL_EFFICIENCY, worldSettings.getSkillEfficiencyTimer());
//        this.sendTimer(player, ClientGameTimer.SKILL_POWER, worldSettings.getSkillPowerTimer());

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

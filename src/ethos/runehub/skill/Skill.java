package ethos.runehub.skill;

import ethos.Server;
import ethos.model.players.Player;
import ethos.runehub.world.WorldSettingsController;
import ethos.util.Misc;
import org.runehub.api.model.math.impl.AdjustableLong;
import org.runehub.api.util.SkillDictionary;

import java.util.Arrays;
import java.util.Random;

public abstract class Skill {

    public static final Random SKILL_RANDOM = new Random();

    public abstract int getId();

    protected double getEquipmentBonuses() {
        return Arrays.stream(this.getPlayer().playerEquipment).mapToDouble(equippedItem ->{
            switch (equippedItem) {
            }
            return 0D;
        }).sum();
    }

    protected int getPowerModifier() {
        double modifier = 1.0D;
        if (WorldSettingsController.getInstance().getWorldSettings().getSkillPowerTimer().getOrDefault(this.getId(), new AdjustableLong(0L)).greaterThan(0L)) {
            modifier += WorldSettingsController.getInstance().getWorldSettings().getPowerModifer();
        }

        if (WorldSettingsController.getInstance().getSkillOfTheHourEffect(this.getId()) == 2) {
            modifier += 1.5;
        }
        return Math.toIntExact(Math.round(modifier));
    }

    protected int getEfficiencyModifier() {
        double modifier = 1.0D;
        if (WorldSettingsController.getInstance().getWorldSettings().getSkillEfficiencyTimer().getOrDefault(this.getId(), new AdjustableLong(0L)).greaterThan(0L)) {
            modifier += WorldSettingsController.getInstance().getWorldSettings().getEfficiencyModifier();
        }

        if (WorldSettingsController.getInstance().getSkillOfTheHourEffect(this.getId()) == 3) {
            modifier += 1.5;
        }
        return Math.toIntExact(Math.round(modifier));
    }

    protected int getGainsModifier() {
        double modifier = 1.0D;
        if (WorldSettingsController.getInstance().getWorldSettings().getSkillGainsTimer().getOrDefault(this.getId(), new AdjustableLong(0L)).greaterThan(0L)) {
            modifier += WorldSettingsController.getInstance().getWorldSettings().getGainsModifier();
        }

        if (WorldSettingsController.getInstance().getSkillOfTheHourEffect(this.getId()) == 1) {
            modifier += 1.5;
        }
        return Math.toIntExact(Math.round(modifier));
    }

    public double getGainsBonus() {
        return this.getGainsModifier() * gains;
    }

    public double getEfficiencyBonus() {
        return Math.toIntExact(Math.round(this.getEfficiencyModifier())) * efficiency;
    }

    public double getPowerBonus() {
        return Math.toIntExact(Math.round(this.getPowerModifier())) * power;
    }

    public double getEfficiency() {
        return efficiency;
    }

    public double getPower() {
        return power;
    }

    public double getGains() {
        return gains;
    }

    public void setGains(double gains) {
        this.gains = gains;
    }

    public void setEfficiency(double efficiency) {
        this.efficiency = efficiency;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public void train(SkillAction action) {
        try {
            action.getActor().getSkilling().stop();
            action.getActor().getSkilling().setSkill(action.getSkillId());
            Server.getEventHandler().submit(action);
        } catch (Exception e) {
            e.printStackTrace();
            player.sendMessage(e.getMessage());
        }
    }

    public double getActionSuccessChance(int min, int max) {
        double brOne = Math.floor(min * (99 - player.getSkillController().getLevel(this.getId())) / 98.0);
        double brTwo = Math.floor(max * (player.getSkillController().getLevel(this.getId()) - 1) / 98.0);
        double top = 1 + brOne + brTwo;
        double value = top / 256;
        return value;
    }

    @Override
    public String toString() {
        if (this.getId() == SkillDictionary.Skill.FARMING.getId())
            return "Foraging";
        else if (this.getId() == 23)
            return "Sailing";
        return Misc.capitalize(SkillDictionary.getSkillNameFromId(this.getId()).toLowerCase());
    }

    public Player getPlayer() {
        return player;
    }

    protected Skill(Player player) {
        this.player = player;
    }

    private final Player player;
    protected double power = 1.0D; //adds to roll for chance to harvest node
    protected double efficiency; //adds to min roll required to deplete node
    protected double gains = 1.0D; //multiplier for xp
}

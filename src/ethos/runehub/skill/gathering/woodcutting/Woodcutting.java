package ethos.runehub.skill.gathering.woodcutting;

import ethos.model.players.Player;
import ethos.runehub.world.WorldSettingsController;
import ethos.runehub.skill.gathering.GatheringSkill;
import org.runehub.api.model.math.impl.AdjustableLong;
import org.runehub.api.util.SkillDictionary;

import java.util.Arrays;

public class Woodcutting extends GatheringSkill {

    @Override
    protected double getEquipmentBonuses() {
        return Arrays.stream(this.getPlayer().playerEquipment).mapToDouble(equippedItem ->{
            switch (equippedItem) {
                case 8122:
                case 8123:
                case 8124:
                    return 0.05;
                case 8125:
                    return 0.08;
            }
            return 0D;
        }).sum() + super.getEquipmentBonuses();
    }

    @Override
    public double getPowerBonus() {
        double modifier = Arrays.stream(this.getPlayer().playerEquipment).mapToDouble(equippedItem ->{
            switch (equippedItem) {
                case 8122:
                case 8123:
                case 8124:
                    return 0.05;
                case 8125:
                    return 0.07;
            }
            return 0D;
        }).sum();
        return modifier + super.getPowerBonus();
    }

    @Override
    public double getEfficiencyBonus() {
        double modifier = Arrays.stream(this.getPlayer().playerEquipment).mapToDouble(equippedItem ->{
            switch (equippedItem) {
                case 8125:
                    return 0.1;
            }
            return 0D;
        }).sum();
        return modifier + super.getPowerBonus();
    }

    @Override
    public int getEventOdds() {
        double modifier = Arrays.stream(this.getPlayer().playerEquipment).mapToDouble(equippedItem ->{
            switch (equippedItem) {
                case 8122:
                case 8123:
                case 8124:
                    return 0.05;
                case 8125:
                    return 0.07;
                case 10132:
                case 9807:
                case 9808:
                    return 0.1;
            }
            return 0D;
        }).sum();
        return (int) (EVENT_ODDS - (EVENT_ODDS * modifier));
    }

    @Override
    public int getId() {
        return SkillDictionary.Skill.WOODCUTTING.getId();
    }

    public boolean wearingSkillRing() {
        return Arrays.stream(this.getPlayer().playerEquipment).anyMatch(equippedItem ->{
            switch (equippedItem) {
                case 8122:
                case 8123:
                case 8124:
                case 8125:
                    return true;
            }
            return false;
        });
    }

    public boolean wearingEliteSkillRing() {
        return Arrays.stream(this.getPlayer().playerEquipment).anyMatch(equippedItem ->{
            switch (equippedItem) {
                case 8125:
                    return true;
            }
            return false;
        });
    }

    public Woodcutting(Player player) {
        super(player);
    }


}

package ethos.runehub.skill.gathering.fishing;

import ethos.model.content.achievement.AchievementType;
import ethos.model.content.achievement.Achievements;
import ethos.model.players.Player;
import ethos.runehub.skill.gathering.GatheringSkill;

import java.util.Arrays;

public class Fishing extends GatheringSkill {
private Player c;
    @Override
    protected double getEquipmentBonuses() {

        return Arrays.stream(this.getPlayer().playerEquipment).mapToDouble(equippedItem ->{
            switch (equippedItem) {
                case 13258:
                    return 0.04;
                case 13259:
                    return 0.08;
                case 13260:
                    return 0.06;
                case 13261:
                    return 0.02;
                case 3742:
                    return 0.5;
            }
            return 0D;	
        }).sum() + super.getEquipmentBonuses();
    }

    @Override
    public int getId() {
        return 10;

    }

    public Fishing(Player player) {
        super(player);
    }

}

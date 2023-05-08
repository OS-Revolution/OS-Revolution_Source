package ethos.runehub.skill.artisan.cooking;

import ethos.model.players.Player;
import ethos.runehub.skill.artisan.ArtisanSkill;
import org.runehub.api.util.SkillDictionary;

import java.util.Arrays;

public class Cooking extends ArtisanSkill {

    public Cooking(Player player) {
        super(player);
    }

    @Override
    protected double getEquipmentBonuses() {
        return Arrays.stream(this.getPlayer().playerEquipment).mapToDouble(equippedItem ->{
            switch (equippedItem) {
                case 1949:
                case 1005:
                    return 0.02;
            }
            return 0D;
        }).sum();
    }

    @Override
    public double getActionSuccessChance(int min, int max) {
        System.out.println(this.getSuccessValuesBonus() + " bonuses");
        double brOne = Math.floor((min * this.getSuccessValuesBonus()) * (99 - this.getPlayer().getSkillController().getLevel(this.getId())) / 98.0);
        double brTwo = Math.floor((max * this.getSuccessValuesBonus()) * (this.getPlayer().getSkillController().getLevel(this.getId()) - 1) / 98.0);
        double top = 1 + brOne + brTwo;
        double value = top / 256;
        return value;
    }

    @Override
    public int getId() {
        return SkillDictionary.Skill.COOKING.getId();
    }
}

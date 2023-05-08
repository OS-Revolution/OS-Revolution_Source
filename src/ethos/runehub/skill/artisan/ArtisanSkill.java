package ethos.runehub.skill.artisan;

import ethos.model.players.Player;
import ethos.runehub.skill.Skill;

import java.util.Arrays;

public abstract class ArtisanSkill extends Skill {

    public double getSuccessValuesBonus() {
        return Math.max(1,Arrays.stream(this.getPlayer().playerEquipment).mapToDouble(equippedItem ->{
            switch (equippedItem) {
            }
            return 0D;
        }).sum());
    }

    @Override
    public double getEfficiencyBonus() {
        return this.getEfficiencyModifier();
    }

    protected ArtisanSkill(Player player) {
        super(player);
    }
}

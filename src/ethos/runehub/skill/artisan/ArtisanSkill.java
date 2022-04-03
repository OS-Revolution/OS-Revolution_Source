package ethos.runehub.skill.artisan;

import ethos.model.players.Player;
import ethos.runehub.skill.Skill;

public abstract class ArtisanSkill extends Skill {

    @Override
    public double getEfficiency() {
        return this.getEfficiencyModifier();
    }

    protected ArtisanSkill(Player player) {
        super(player);
    }
}

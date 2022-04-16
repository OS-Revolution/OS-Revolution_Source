package ethos.runehub.skill.artisan.runecraft;

import ethos.model.players.Player;
import ethos.runehub.skill.artisan.ArtisanSkill;
import org.runehub.api.util.SkillDictionary;

public class Runecraft extends ArtisanSkill {

    public static final int[] TALISMAN = {};

    @Override
    public int getId() {
        return SkillDictionary.Skill.RUNECRAFTING.getId();
    }

    public Runecraft(Player player) {
        super(player);
    }
}

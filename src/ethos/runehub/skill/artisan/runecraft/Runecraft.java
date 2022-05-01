package ethos.runehub.skill.artisan.runecraft;

import ethos.model.players.Player;
import ethos.runehub.skill.artisan.ArtisanSkill;
import org.runehub.api.util.SkillDictionary;

public class Runecraft extends ArtisanSkill {

    public static final int[] TALISMAN = {};
    public static final int RUNECRAFTING_ANIMATION = 791;
    public static final int RUNECRAFTING_GRAPHIC = 186;

    @Override
    public int getId() {
        return SkillDictionary.Skill.RUNECRAFTING.getId();
    }

    public Runecraft(Player player) {
        super(player);
    }
}

package ethos.runehub.skill.artisan.cooking;

import ethos.model.players.Player;
import ethos.runehub.skill.artisan.ArtisanSkill;
import org.runehub.api.util.SkillDictionary;

public class Cooking extends ArtisanSkill {

    public Cooking(Player player) {
        super(player);
    }

    @Override
    public int getId() {
        return SkillDictionary.Skill.COOKING.getId();
    }
}

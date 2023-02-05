package ethos.runehub.skill.artisan.crafting;

import ethos.model.players.Player;
import ethos.runehub.skill.artisan.ArtisanSkill;
import org.runehub.api.util.SkillDictionary;

public class Crafting extends ArtisanSkill {

    @Override
    public int getId() {
        return SkillDictionary.Skill.CRAFTING.getId();
    }

    public Crafting(Player player) {
        super(player);
    }
}

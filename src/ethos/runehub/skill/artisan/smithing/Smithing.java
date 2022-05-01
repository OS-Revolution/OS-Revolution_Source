package ethos.runehub.skill.artisan.smithing;

import ethos.model.players.Player;
import ethos.runehub.skill.artisan.ArtisanSkill;
import org.runehub.api.util.SkillDictionary;

public class Smithing extends ArtisanSkill {

    public Smithing(Player player) {
        super(player);
    }

    @Override
    public int getId() {
        return SkillDictionary.Skill.SMITHING.getId();
    }
}

package ethos.runehub.skill.artisan.herblore;

import ethos.model.players.Player;
import ethos.runehub.skill.artisan.ArtisanSkill;
import org.runehub.api.util.SkillDictionary;

public class Herblore extends ArtisanSkill {

    public static final int PESTLE = 233;

    @Override
    public int getId() {
        return SkillDictionary.Skill.HERBLORE.getId();
    }

    public Herblore(Player player) {
        super(player);
    }
}

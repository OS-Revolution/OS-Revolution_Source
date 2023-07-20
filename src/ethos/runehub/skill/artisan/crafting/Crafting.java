package ethos.runehub.skill.artisan.crafting;

import ethos.model.players.Player;
import ethos.runehub.skill.artisan.ArtisanSkill;
import org.runehub.api.util.SkillDictionary;

public class Crafting extends ArtisanSkill {

    public float getUpgradeBonus() {
        return this.getPlayer().getSkillController().getLevel(SkillDictionary.Skill.CRAFTING.getId()) * 0.005f;
    }

    @Override
    public int getId() {
        return SkillDictionary.Skill.CRAFTING.getId();
    }

    public Crafting(Player player) {
        super(player);
    }
}

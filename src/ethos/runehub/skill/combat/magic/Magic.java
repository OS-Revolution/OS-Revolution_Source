package ethos.runehub.skill.combat.magic;

import ethos.model.players.Player;
import ethos.runehub.skill.combat.CombatSkill;
import org.runehub.api.util.SkillDictionary;

public class Magic extends CombatSkill {

    @Override
    public int getId() {
        return SkillDictionary.Skill.MAGIC.getId();
    }

    public Magic(Player player) {
        super(player);
    }
}

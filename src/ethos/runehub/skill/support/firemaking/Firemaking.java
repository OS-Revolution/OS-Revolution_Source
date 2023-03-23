package ethos.runehub.skill.support.firemaking;

import ethos.model.players.Player;
import ethos.runehub.skill.support.SupportSkill;
import org.runehub.api.util.SkillDictionary;

public class Firemaking extends SupportSkill {

    @Override
    public int getId() {
        return SkillDictionary.Skill.FIREMAKING.getId();
    }

    public Firemaking(Player player) {
        super(player);
    }
}

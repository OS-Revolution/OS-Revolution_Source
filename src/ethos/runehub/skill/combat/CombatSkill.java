package ethos.runehub.skill.combat;

import ethos.model.players.Player;
import ethos.runehub.skill.Skill;

public abstract class CombatSkill extends Skill {

    protected CombatSkill(Player player) {
        super(player);
    }
}

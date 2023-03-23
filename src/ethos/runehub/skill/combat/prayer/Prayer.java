package ethos.runehub.skill.combat.prayer;

import ethos.model.players.Player;
import ethos.runehub.skill.combat.CombatSkill;
import org.runehub.api.util.SkillDictionary;

public class Prayer extends CombatSkill {

    public int getRemainsUsedOnAltar() {
        return remainsUsedOnAltar;
    }

    public void setRemainsUsedOnAltar(int remainsUsedOnAltar) {
        this.remainsUsedOnAltar = remainsUsedOnAltar;
    }

    @Override
    public int getId() {
        return SkillDictionary.Skill.PRAYER.getId();
    }

    public Prayer(Player player) {
        super(player);
    }

    private int remainsUsedOnAltar;
}

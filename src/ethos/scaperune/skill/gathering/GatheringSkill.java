package ethos.scaperune.skill.gathering;

import ethos.model.players.Player;
import ethos.scaperune.skill.Skill;
import ethos.scaperune.skill.SkillAction;

public abstract class GatheringSkill extends Skill {

    protected GatheringSkill(int id, Player player) {
        super(id,player);
    }
}

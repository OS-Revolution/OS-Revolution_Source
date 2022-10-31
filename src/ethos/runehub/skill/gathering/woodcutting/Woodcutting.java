package ethos.runehub.skill.gathering.woodcutting;

import ethos.model.players.Player;
import ethos.runehub.world.WorldSettingsController;
import ethos.runehub.skill.gathering.GatheringSkill;
import org.runehub.api.model.math.impl.AdjustableLong;
import org.runehub.api.util.SkillDictionary;

public class Woodcutting extends GatheringSkill {

    @Override
    public int getId() {
        return SkillDictionary.Skill.WOODCUTTING.getId();
    }

    public Woodcutting(Player player) {
        super(player);
    }


}

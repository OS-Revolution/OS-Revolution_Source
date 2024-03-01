package ethos.runehub.skill.gathering.mining;

import ethos.model.players.Player;
import ethos.runehub.skill.gathering.GatheringSkill;
import org.runehub.api.util.SkillDictionary;

public class Mining extends GatheringSkill {

    @Override
    public double getEfficiencyBonus() {
        if (this.getBestAvailableTool() != null)
            return this.getBestAvailableTool().getBaseEfficiency();
        return 0;
    }


    @Override
    public int getId() {
        return SkillDictionary.Skill.MINING.getId();
    }

    public Mining(Player player) {
        super(player);
    }


}

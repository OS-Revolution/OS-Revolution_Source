package ethos.runehub.skill.gathering.mining;

import ethos.model.players.Player;
import ethos.runehub.world.WorldSettingsController;
import ethos.runehub.skill.gathering.GatheringSkill;
import org.runehub.api.model.math.impl.AdjustableLong;
import org.runehub.api.util.SkillDictionary;

public class Mining extends GatheringSkill {

    @Override
    public double getEfficiencyBonus() {
        if (this.getGetBestAvailableTool() != null)
            return this.getGetBestAvailableTool().getBaseEfficiency();
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

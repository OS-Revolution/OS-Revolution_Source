package ethos.runehub.skill.gathering.mining;

import ethos.model.players.Player;
import ethos.runehub.WorldSettingsController;
import ethos.runehub.skill.gathering.GatheringSkill;
import org.rhd.api.math.impl.AdjustableLong;
import org.runehub.api.util.SkillDictionary;

public class Mining extends GatheringSkill {

    @Override
    public double getEfficiency() {
        if (this.getGetBestAvailableTool() != null)
            return this.getGetBestAvailableTool().getBasePower();
        return 0;
    }

    @Override
    protected int getPowerModifier() {
        return Math.toIntExact(Math.round(WorldSettingsController.getInstance().getWorldSettings().getSkillPowerTimer().getOrDefault(this.getId(),new AdjustableLong(0L)).greaterThan(0L) ? WorldSettingsController.getInstance().getWorldSettings().getPowerModifer() : 1.0D));
    }

    @Override
    protected int getEfficiencyModifier() {
        return Math.toIntExact(Math.round(WorldSettingsController.getInstance().getWorldSettings().getSkillEfficiencyTimer().getOrDefault(this.getId(),new AdjustableLong(0L)).greaterThan(0L) ? WorldSettingsController.getInstance().getWorldSettings().getEfficiencyModifier() : 1.0D));
    }

    @Override
    protected int getGainsModifier() {
        return Math.toIntExact(Math.round(WorldSettingsController.getInstance().getWorldSettings().getSkillGainsTimer().getOrDefault(this.getId(),new AdjustableLong(0L)).greaterThan(0L) ? WorldSettingsController.getInstance().getWorldSettings().getGainsModifier() : 1.0D));
    }

    @Override
    public int getId() {
        return SkillDictionary.Skill.MINING.getId();
    }

    public Mining(Player player) {
        super(player);
    }


}

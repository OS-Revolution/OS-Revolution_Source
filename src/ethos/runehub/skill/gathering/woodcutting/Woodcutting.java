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

    public Woodcutting(Player player) {
        super(player);
    }


}

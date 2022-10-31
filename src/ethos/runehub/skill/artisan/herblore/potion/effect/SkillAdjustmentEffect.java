package ethos.runehub.skill.artisan.herblore.potion.effect;

import com.mysql.jdbc.log.Log;
import ethos.model.players.Player;

import java.util.logging.Logger;

public class SkillAdjustmentEffect extends Effect {

    @Override
    public void doEffectOnPlayer(Player player) {
        int adjustedSkillLevel = (int) (((capped ? player.getLevelForXP(player.playerXP[skillId]) : player.playerLevel[skillId]) * modifier) + baseChange);
        int boost = adjustedSkillLevel - player.getLevelForXP(player.playerXP[skillId]);
        if (adjustedSkillLevel < 1) {
            player.playerLevel[skillId] = 1;
        }

        Logger.getGlobal().fine("Skill: " + skillId);
        Logger.getGlobal().fine("Adjusted Skill Level: " + adjustedSkillLevel);
        Logger.getGlobal().fine("Skill Boost: " + boost);

        if (skillId == 3) {
            player.getHealth().setMaximum(adjustedSkillLevel);
            player.getHealth().increase(boost);
        } else {
            player.playerLevel[skillId] = adjustedSkillLevel;
        }

        player.getPA().refreshSkill(skillId);
//        player.getPA().requestUpdates();
    }

    public int getSkillId() {
        return skillId;
    }

    public float getModifier() {
        return modifier;
    }

    public int getBaseChange() {
        return baseChange;
    }

    public boolean isCapped() {
        return capped;
    }

    public SkillAdjustmentEffect(int id, int skillId, int baseChange, float modifier, boolean capped) {
        super(id);
        this.skillId = skillId;
        this.baseChange = baseChange;
        this.modifier = modifier;
        this.capped = capped;
    }

    public SkillAdjustmentEffect(int id, int skillId, int baseChange, float modifier) {
        this(id,skillId,baseChange,modifier,true);
    }

    private final int skillId;
    private final int baseChange;
    private final float modifier;
    private final boolean capped;
}

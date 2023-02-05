package ethos.runehub.skill.artisan.herblore.potion.effect;

import ethos.model.players.Player;

public class RestoreEffect extends SkillAdjustmentEffect {

    @Override
    public void doEffectOnPlayer(Player player) {
        int adjustedSkillLevel = (int) ((player.playerLevel[getSkillId()] * this.getModifier()) + this.getBaseChange());

        if(player.playerLevel[this.getSkillId()] < adjustedSkillLevel) {
            if (adjustedSkillLevel <= player.getLevelForXP(player.playerXP[this.getSkillId()])) {
                player.playerLevel[this.getSkillId()] = adjustedSkillLevel;
            } else {
                player.playerLevel[this.getSkillId()] = player.getLevelForXP(player.playerXP[this.getSkillId()]);
            }
        }
        player.getPA().refreshSkill(this.getSkillId());
    }

    public RestoreEffect(int id, int skillId, int baseChange, float modifier) {
        super(id,skillId,baseChange,modifier);
    }
}

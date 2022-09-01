package ethos.runehub.skill.artisan.herblore.potion.effect;

import ethos.model.players.Player;
import org.runehub.api.util.SkillDictionary;

import java.util.Arrays;
import java.util.HashMap;

public class XPBoostingEffect extends TimedEffect {

    @Override
    public void doEffectOnPlayer(Player player) {
        if (player.getContext().getPlayerSaveData().getBonusXpTimers() == null) {
            player.getContext().getPlayerSaveData().setBonusXpTimers(new HashMap<>());
            Arrays.stream(SkillDictionary.Skill.values()).forEach(skill -> player.getContext().getPlayerSaveData().getBonusXpTimers().put(skill.getId(),0L));
        }
        player.getContext().getPlayerSaveData().getBonusXpTimers().computeIfPresent(skillId, (key,val) -> val + (getDurationMs()));
    }

    public XPBoostingEffect(int id, long duration, int skillId) {
        super(id,duration);
        this.skillId = skillId;
    }

    private final int skillId;
}

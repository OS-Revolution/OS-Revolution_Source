package ethos.runehub.event.dnd;

import ethos.model.players.ClientGameTimer;
import ethos.model.players.PlayerHandler;
import ethos.runehub.RunehubUtils;
import ethos.runehub.event.FixedScheduleEvent;
import ethos.runehub.skill.Skill;
import ethos.runehub.world.WorldSettingsController;
import org.runehub.api.model.math.AdjustableNumber;
import org.runehub.api.model.math.impl.AdjustableInteger;
import org.runehub.api.model.math.impl.AdjustableLong;
import org.runehub.api.util.SkillDictionary;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class SkillOfTheHourFixedScheduleEvent extends FixedScheduleEvent {

    public static final int XP = 1;
    public static final int POWER = 2;
    public static final int EFFICIENCY = 3;

    @Override
    public void execute() {
        this.sId = this.getSkillId();
        this.eId = this.getEffectId(sId);
        this.doEffect(eId, sId);
        PlayerHandler.executeGlobalMessage("^D&D $Skill $of $the $Hour is $" + RunehubUtils.getSkillName(sId) + " the effect is " + this.getEffectName(eId));
    }

    private String getEffectName(int eId) {
        switch (eId) {
            case 1:
                return "$1.5x $XP";
            case 2:
                return "$1.5x $Power";
            case 3:
                return "$1.5x $Efficiency";
        }
        return "$1.5x XP";
    }

    private int getSkillId() {
        int sId = Skill.SKILL_RANDOM.nextInt(SkillDictionary.Skill.values().length);
        return (sId != SkillDictionary.Skill.HUNTER.getId() && sId != SkillDictionary.Skill.CONSTRUCTION.getId() && sId != this.sId) ? sId : getSkillId();
    }

    private int getEffectId(int skillId) {
        if (skillId == SkillDictionary.Skill.FISHING.getId()
                || skillId == SkillDictionary.Skill.WOODCUTTING.getId()
                || skillId == SkillDictionary.Skill.FARMING.getId()
                || skillId == SkillDictionary.Skill.MINING.getId()
        ) {
            int eId = Skill.SKILL_RANDOM.nextInt(3);
            return eId == 0 ? getEffectId(skillId) : eId;
        }
        return 1;
    }

    private void doEffect(int effectId, int skillId) {
        for (int i = 0; i < SkillDictionary.Skill.values().length; i++) {
            WorldSettingsController.getInstance().setSkillOfTheHourEffect(i, 0);
        }
        WorldSettingsController.getInstance().setSkillOfTheHourEffect(skillId, effectId);
    }

    @Override
    public String toString() {
        return sId == -1 ? "^D&D $Skill $of $the $Hour is $inactive." : "^D&D $Skill $of $the $Hour is $" + RunehubUtils.getSkillName(sId) + " the effect is " + this.getEffectName(eId);
    }

    public SkillOfTheHourFixedScheduleEvent() {
        super(Duration.ofHours(1).toMillis(), "Skill of the Hour");
    }

    private int sId = -1, eId = -1;
}

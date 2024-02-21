package ethos.scaperune.skill;

import ethos.scaperune.action.impl.PriorityAction;
import ethos.scaperune.action.priority.Priority;

import java.util.logging.Logger;

public abstract class SkillAction extends PriorityAction {

    @Override
    public void onScheduled() {
        Logger.getGlobal().fine("Scheduled Action: " + this.getClass().getName());
    }

    @Override
    public void onCancelled() {
        Logger.getGlobal().fine("Cancelled Action: " + this.getClass().getName());
    }

    public int getSkillId() {
        return skillId;
    }

    public SkillAction(int skillId) {
        this(Priority.LOW, skillId);
    }

    public SkillAction(Priority priority, int skillId) {
        super(priority);
        this.skillId = skillId;
    }

    private final int skillId;
}

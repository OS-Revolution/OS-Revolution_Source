package ethos.scaperune.skill.gathering;

import ethos.scaperune.action.priority.Priority;
import ethos.scaperune.skill.SkillAction;

public abstract class GatheringSkillAction extends SkillAction {

    protected GatheringSkillAction(int skillId) {
        this(Priority.LOW,skillId);
    }

    protected GatheringSkillAction(Priority priority,int skillId) {
        super(priority,skillId);
    }
}

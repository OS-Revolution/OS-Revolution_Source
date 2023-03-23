package ethos.runehub.skill.support;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.skill.Skill;
import ethos.runehub.skill.SkillAction;
import ethos.runehub.skill.node.impl.support.SupportNode;
import ethos.util.PreconditionUtils;

public abstract class SupportSkillAction extends SkillAction {

    protected abstract void onEvent();

    @Override
    protected void addItems(int id, int amount) {
        this.getActor().getItems().addItem(id,amount);
    }

    @Override
    protected void validateLevelRequirements() {
        Preconditions.checkArgument(PreconditionUtils.isTrue(this.getActor().getSkillController().getLevel(this.getSkillId()) >= node.getLevelRequirement()),
                "You need a $"
                        + RunehubUtils.getSkillName(this.getSkillId())
                        + " level of at least $"
                        + node.getLevelRequirement()
                        + " to do this.");

    }

    protected boolean isEventTick() {
        final int ROLL = 100;
        return (Skill.SKILL_RANDOM.nextInt(ROLL) + 1) == ROLL;
    }

    public SupportSkillAction(Player actor, int skillId, int ticks, SupportNode node) {
        super(actor, skillId, ticks);
        this.node = node;
    }

    protected final SupportNode node;
}

package ethos.runehub.action.click.node.impl.first;

import ethos.model.players.Player;
import ethos.model.players.combat.Hitmark;
import ethos.runehub.action.click.node.ClickNodeAction;
import ethos.runehub.skill.Skill;
import org.runehub.api.util.SkillDictionary;
import org.runehub.api.util.math.geometry.Point;

public abstract class FirstClickAbyssalNodeAction extends ClickNodeAction {

    public abstract void move();

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onUpdate() {

    }

    @Override
    protected void onTick() {
        if(this.successCheck(skillId, failedMessage)) {
            this.move();
            this.getActor().getPA().addSkillXP(25,skillId,true);
        } else {
            this.getActor().appendDamage(2, Hitmark.HIT);
        }
        this.stop();
    }

    @Override
    protected void onActionStart() {
        this.getActor().startAnimation(animationId);
    }

    protected boolean successCheck(int skill, String failureMessage) {
        final int level = this.getActor().getSkillController().getLevel(skill);
        final int roll = Skill.SKILL_RANDOM.nextInt(100) + 1;
        if ((roll + level) >= 90) {
            return true;
        } else {
            this.getActor().sendMessage(failureMessage);
        }
        return false;
    }

    public int getSkillId() {
        return skillId;
    }

    public int getAnimationId() {
        return animationId;
    }

    public String getFailedMessage() {
        return failedMessage;
    }

    public FirstClickAbyssalNodeAction(Player attachment, int ticks, int nodeId, int nodeX, int nodeY, int animationId, int skillId,String failedMessage) {
        super(attachment, ticks, nodeId, nodeX, nodeY);
        this.animationId = animationId;
        this.skillId = skillId;
        this.failedMessage = failedMessage;
    }

    private final int animationId;
    private final int skillId;
    private final String failedMessage;
}

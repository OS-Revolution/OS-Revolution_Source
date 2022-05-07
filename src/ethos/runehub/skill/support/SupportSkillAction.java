package ethos.runehub.skill.support;

import ethos.model.players.Player;
import ethos.runehub.skill.SkillAction;

public abstract class SupportSkillAction extends SkillAction {

    @Override
    protected void addItems(int id, int amount) {
        this.getActor().getItems().addItem(id,amount);
    }

    public SupportSkillAction(Player actor, int skillId, int ticks) {
        super(actor, skillId, ticks);
    }
}

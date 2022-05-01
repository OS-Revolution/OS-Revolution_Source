package ethos.runehub.action.click.node.impl.first;

import ethos.model.players.Player;
import ethos.model.players.skills.Skill;
import ethos.runehub.action.click.node.ClickNodeAction;
import ethos.runehub.skill.artisan.runecraft.action.CraftRuneAction;
import ethos.runehub.skill.gathering.mining.action.ActiveMiningSkillAction;
import ethos.runehub.skill.node.impl.gatherable.impl.MiningNode;
import ethos.runehub.skill.node.io.MiningNodeLoader;

import java.util.logging.Logger;

public class FirstClickRunecraftingAltarAction extends ClickNodeAction {

    @Override
    protected void onActionStart() {
        this.getActor().getSkillController().getRunecraft().train(new CraftRuneAction(
                this.getActor(),
                this.getNodeId()
        ));
    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {
        this.stop();
    }

    @Override
    protected void onUpdate() {

    }

    public FirstClickRunecraftingAltarAction(Player attachment, int nodeId, int nodeX, int nodeY, int nodeZ) {
        super(attachment, 1, nodeId, nodeX, nodeY);
    }

}

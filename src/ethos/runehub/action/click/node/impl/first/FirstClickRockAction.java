package ethos.runehub.action.click.node.impl.first;

import ethos.model.players.Player;
import ethos.model.players.skills.Skill;
import ethos.runehub.action.click.node.ClickNodeAction;
import ethos.runehub.skill.gathering.mining.action.ActiveMiningSkillAction;
import ethos.runehub.skill.node.impl.gatherable.impl.MiningNode;
import ethos.runehub.skill.node.io.MiningNodeLoader;

import java.util.logging.Logger;

public class FirstClickRockAction extends ClickNodeAction {

    @Override
    protected void onActionStart() {
        Logger.getGlobal().fine(
                "-----Mining Tick Info-----"
                        + "\nBase Cycle: " + baseCycle
                        + "\nEfficiency Modifier: " + cycleModifier
                        + "\nMining Ticks: " + miningTicks
                        + "\n--------------------------"
        );
//        int percentage = (int) (activeNode.getLevelRequirement() * (this.getActor().getSkillController().getLevel(this.getActor().getSkillController().getMining().getId()) / 100.0f));
        int value = this.getActor().getSkillController().getLevel(this.getActor().getSkillController().getMining().getId())-activeNode.getLevelRequirement();
        int cycles = Math.max(baseCycle - (value + cycleModifier), 5);
        this.getActor().getSkillController().getMining().train(new ActiveMiningSkillAction(
                        this.getActor(),
                        Skill.MINING.getId(),
                        this.getNodeId(),
                        this.getNodeX(),
                        this.getNodeY(),
                        nodeZ,
                        cycles
                )
        );
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

    public FirstClickRockAction(Player attachment, int nodeId, int nodeX, int nodeY, int nodeZ) {
        super(attachment, 1, nodeId, nodeX, nodeY);
        this.activeNode = MiningNodeLoader.getInstance().read(nodeId);
        this.nodeZ = nodeZ;
        this.baseCycle = activeNode.getMiningCycleTicks();
        this.cycleModifier = (int) attachment.getSkillController().getMining().getEfficiencyBonus();
        this.miningTicks = Math.max(baseCycle - cycleModifier, 4);
    }

    private final int nodeZ;
    private final int miningTicks;
    private final int baseCycle;
    private final int cycleModifier;
    private final MiningNode activeNode;
}

package ethos.runehub.skill.gathering.mining.action;

import ethos.model.players.Player;
import ethos.runehub.skill.gathering.GatheringSkillAction;
import ethos.runehub.skill.node.context.impl.MiningNodeContext;

import java.util.logging.Logger;

public class ActiveMiningSkillAction extends GatheringSkillAction {

    @Override
    protected void onEvent() {
        Logger.getGlobal().fine("Mining Gem");
    }

    @Override
    protected void onUpdate() {
        super.onUpdate();
        try {
            this.validateWorldRequirements();
        } catch (Exception e) {
            this.stop();
            this.getActor().sendMessage(e.getMessage());
        }
    }

    @Override
    protected void updateAnimation() {
        Logger.getGlobal().finer("Updating Animation");
        if (super.getElapsedTicks() == 4 || super.getElapsedTicks() % 4 == 0) {
            this.getActor().startAnimation(this.getActor().getSkillController().getMining().getGetBestAvailableTool().getAnimationId());
        }
    }


    public ActiveMiningSkillAction(Player player, int skillId, int nodeId, int nodeX, int nodeY, int nodeZ, int ticks) {
        super(player, skillId, new MiningNodeContext(nodeId,nodeX,nodeY,nodeZ), ticks,player.getSkillController().getMining().getGetBestAvailableTool());
    }
}

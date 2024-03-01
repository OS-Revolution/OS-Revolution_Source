package ethos.runehub.skill.gathering.mining.action;

import ethos.model.content.achievement.AchievementType;
import ethos.model.content.achievement.Achievements;
import ethos.model.players.Player;
import ethos.runehub.content.journey.JourneyStepType;
import ethos.runehub.skill.gathering.GatheringSkillAction;
import ethos.runehub.skill.gathering.tool.GatheringTool;
import ethos.runehub.skill.node.context.impl.MiningNodeContext;

import java.util.logging.Logger;

public class ActiveMiningSkillAction extends GatheringSkillAction {

    @Override
    protected void onEvent() {
        Logger.getGlobal().fine("Mining Gem");
    }

    @Override
    protected boolean isException() {
        if (this.getTargetedNodeContext().getNodeId() == 7471) {
            return true;
        }
        return super.isException();
    }

    @Override
    protected void onException() {
        if (this.getTargetedNodeContext().getNodeId() == 7471) {
            if (this.getActor().getSkillController().getLevel(this.getSkillId()) >= 30) {
                this.addItems(7936, 1);
            } else {
                this.addItems(1436, 1);
            }
        }
    }

    @Override
    protected void onUpdate() {
        super.onUpdate();
        try {
            this.validateWorldRequirements();
        } catch (Exception e) {
            this.stop();
        }
    }

    @Override
    protected void onActionStart() {
        this.getActor().sendMessage("You swing your pick at the rock.");
        super.onActionStart();
    }

    @Override
    protected void updateAnimation() {
        Logger.getGlobal().finer("Updating Animation");
        if (super.getElapsedTicks() == 4 || super.getElapsedTicks() % 4 == 0) {
            this.getActor().startAnimation(this.getActor().getSkillController().getMining().getBestAvailableTool().getAnimationId());
        }
    }

    protected void addItems(int id, int amount) {
        this.getActor().sendMessage("You manage to mine some @" + id);
        super.addItems(id, amount);
		Achievements.increase(this.getActor(), AchievementType.MINE, 1);

        // Update journey steps for collection and challenge
        this.getActor().getAttributes().getJourneyController().checkJourney(id, amount, JourneyStepType.COLLECTION);
        this.getActor().getAttributes().getJourneyController().checkJourney(id, amount, JourneyStepType.CHALLENGE);

        // Example logic for a specific item ID, similar to the woodcutting skill action
        if (id == 434) {
            this.getActor().getAttributes().getAchievementController().completeAchievement(-6598849538144907698L);
        }

        // Add any additional special cases or logic here as needed
    }

    @Override
    protected GatheringTool getGetBestAvailableTool() throws NullPointerException {
        return this.getActor().getSkillController().getMining().getBestAvailableTool();
    }


    public ActiveMiningSkillAction(Player player, int skillId, int nodeId, int nodeX, int nodeY, int nodeZ, int ticks) {
        super(player, skillId, new MiningNodeContext(nodeId, nodeX, nodeY, nodeZ), ticks);
    }
}

package ethos.runehub.action.click.node.impl.first;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import ethos.runehub.content.journey.JourneyStepType;
import org.runehub.api.util.SkillDictionary;

public class FirstClickAbyssalWoodcuttingAction extends FirstClickAbyssalNodeAction {

    @Override
    protected void validate() {
        Preconditions.checkArgument(this.getActor().getSkillController().getWoodcutting().getBestAvailableTool() != null, "You need an axe.");
    }

    @Override
    protected void onActionStart() {
        this.getActor().startAnimation(this.getActor().getSkillController().getWoodcutting().getBestAvailableTool().getAnimationId());
    }

    @Override
    public void move() {
        this.getActor().getAttributes().getJourneyController().checkJourney(getNodeId(),1, JourneyStepType.NODE_INTERACTION);
        if(this.getNodeId() == 26189) {
            this.getActor().getPA().movePlayer(3027,4831);
        } else if(this.getNodeId() == 26253) {
            this.getActor().getPA().movePlayer(3050, 4824);
        }
    }

    public FirstClickAbyssalWoodcuttingAction(Player attachment, int nodeId, int nodeX, int nodeY) {
        super(attachment, 4, nodeId, nodeX, nodeY, -1, SkillDictionary.Skill.WOODCUTTING.getId(), "The tendrils snap back!");
    }
}

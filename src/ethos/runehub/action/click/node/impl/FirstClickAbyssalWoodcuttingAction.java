package ethos.runehub.action.click.node.impl;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import ethos.runehub.skill.gathering.tool.GatheringTool;
import ethos.runehub.skill.gathering.tool.GatheringToolLoader;
import org.runehub.api.util.SkillDictionary;
import org.runehub.api.util.math.geometry.Point;

import java.util.List;
import java.util.stream.Collectors;

public class FirstClickAbyssalWoodcuttingAction extends FirstClickAbyssalNodeAction {

    @Override
    protected void validate() {
        final List<Integer> axes = GatheringToolLoader.getInstance().readAll().stream()
                .filter(tool -> tool.getSkillId() == SkillDictionary.Skill.WOODCUTTING.getId())
                .map(GatheringTool::getItemId)
                .collect(Collectors.toList());
        Preconditions.checkArgument(this.getActor().getSkillController().getWoodcutting().getGetBestAvailableTool() != null, "You need an axe.");
    }

    @Override
    public void move() {
        if(this.getNodeId() == 26189) {
            this.getActor().getPA().movePlayer(3027,4831);
        } else if(this.getNodeId() == 26253) {
            this.getActor().getPA().movePlayer(3050, 4824);
        }
    }

    private boolean hasAxe(List<Integer> axes) {
        return axes.stream().anyMatch(itemId -> this.getActor().getItems().playerHasItem(itemId));
    }

    public FirstClickAbyssalWoodcuttingAction(Player attachment, int nodeId, int nodeX, int nodeY) {
        super(attachment, 4, nodeId, nodeX, nodeY, attachment.getSkillController().getWoodcutting().getGetBestAvailableTool().getAnimationId(), SkillDictionary.Skill.WOODCUTTING.getId(), "The tendrils snap back!");
    }
}

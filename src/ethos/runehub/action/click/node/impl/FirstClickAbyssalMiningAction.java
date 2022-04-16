package ethos.runehub.action.click.node.impl;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import ethos.runehub.skill.gathering.tool.GatheringTool;
import ethos.runehub.skill.gathering.tool.GatheringToolLoader;
import org.runehub.api.util.SkillDictionary;

import java.util.List;
import java.util.stream.Collectors;

public class FirstClickAbyssalMiningAction extends FirstClickAbyssalNodeAction {

    @Override
    protected void validate() {
//        final List<Integer> axes = GatheringToolLoader.getInstance().readAll().stream()
//                .filter(tool -> tool.getSkillId() == this.getSkillId())
//                .map(GatheringTool::getItemId)
//                .collect(Collectors.toList());
        Preconditions.checkArgument(this.getActor().getSkillController().getMining().getGetBestAvailableTool() != null, "You need a pickaxe.");
    }

    @Override
    public void move() {
        if(this.getNodeId() == 26574) {
            this.getActor().getPA().movePlayer(3050, 4824);
        } else if(this.getNodeId() == 26188) {
            this.getActor().getPA().movePlayer(3050, 4824);
        }
    }

//    private boolean hasAxe(List<Integer> axes) {
//        return axes.stream().anyMatch(itemId -> this.getActor().getItems().playerHasItem(itemId));
//    }

    public FirstClickAbyssalMiningAction(Player attachment, int nodeId, int nodeX, int nodeY) {
        super(attachment, 4, nodeId, nodeX, nodeY, attachment.getSkillController().getMining().getGetBestAvailableTool().getAnimationId(), SkillDictionary.Skill.MINING.getId(), "The rock shatters in your face!");
    }
}

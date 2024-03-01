package ethos.runehub.action.click.node.impl.first;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import org.runehub.api.util.SkillDictionary;

public class FirstClickAbyssalMiningAction extends FirstClickAbyssalNodeAction {

    @Override
    protected void validate() {
//        final List<Integer> axes = GatheringToolLoader.getInstance().readAll().stream()
//                .filter(tool -> tool.getSkillId() == this.getSkillId())
//                .map(GatheringTool::getItemId)
//                .collect(Collectors.toList());
        Preconditions.checkArgument(this.getActor().getSkillController().getMining().getBestAvailableTool() != null, "You need a pickaxe.");
    }

    @Override
    protected void onActionStart() {
        this.getActor().startAnimation(this.getActor().getSkillController().getMining().getBestAvailableTool().getAnimationId());
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
        super(attachment, 4, nodeId, nodeX, nodeY, -1, SkillDictionary.Skill.MINING.getId(), "The rock shatters in your face!");
    }
}

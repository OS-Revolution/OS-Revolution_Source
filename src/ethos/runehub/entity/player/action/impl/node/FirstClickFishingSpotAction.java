package ethos.runehub.entity.player.action.impl.node;

import ethos.model.players.Player;
import ethos.runehub.entity.player.action.impl.node.ClickNodeAction;
import ethos.runehub.skill.gathering.fishing.action.CageFishingAction;
import ethos.runehub.skill.gathering.fishing.action.FishingPlatformFishingAction;
import ethos.runehub.skill.gathering.fishing.action.LureFishingAction;
import ethos.runehub.skill.gathering.fishing.action.NetFishingAction;
import ethos.runehub.skill.node.context.impl.FishingNodeContext;

public class FirstClickFishingSpotAction extends ClickNodeAction {

    @Override
    public void perform() {
        switch (nodeId) {
            case 20926:
                player.getSkillController().getFishing().train(new NetFishingAction(
                        player,
                        new FishingNodeContext(7, nodeX, nodeY, player.heightLevel)
                ));
                break;
            case 20927:
                player.getSkillController().getFishing().train(new NetFishingAction(
                        player,
                        new FishingNodeContext(1, nodeX, nodeY, player.heightLevel)
                ));
                break;
            case 20928:
                player.getSkillController().getFishing().train(new LureFishingAction(
                        player,
                        new FishingNodeContext(3, nodeX, nodeY, player.heightLevel)
                ));
                break;
            case 20929:
                player.getSkillController().getFishing().train(new CageFishingAction(
                        player,
                        new FishingNodeContext(5, nodeX, nodeY, player.heightLevel)
                ));
                break;
            case 20930:
                player.getSkillController().getFishing().train(new FishingPlatformFishingAction(player,nodeX,nodeY));
                break;
        }

    }

    public FirstClickFishingSpotAction(Player player, int nodeX, int nodeY, int nodeId) {
        super(player, nodeX, nodeY, nodeId);

    }
}

package ethos.runehub.entity.player.action.impl.node;

import ethos.model.players.Player;
import ethos.runehub.entity.player.action.impl.node.ClickNodeAction;
import ethos.runehub.skill.gathering.fishing.action.*;
import ethos.runehub.skill.node.context.impl.FishingNodeContext;

public class SecondClickFishingSpotAction extends ClickNodeAction {

    @Override
    public void perform() {
        switch (nodeId) {
            case 20926:
                player.getSkillController().getFishing().train(new HarpoonFishingAction(
                        player,
                        new FishingNodeContext(8, nodeX, nodeY, player.heightLevel)
                ));
                break;
            case 20927:
                player.getSkillController().getFishing().train(new LureFishingAction(
                        player,
                        new FishingNodeContext(2, nodeX, nodeY, player.heightLevel)
                ));
                break;
            case 20928:
                player.getSkillController().getFishing().train(new LureFishingAction(
                        player,
                        new FishingNodeContext(4, nodeX, nodeY, player.heightLevel)
                ));
                break;
            case 20929:
                player.getSkillController().getFishing().train(new HarpoonFishingAction(
                        player,
                        new FishingNodeContext(6, nodeX, nodeY, player.heightLevel)
                ));
                break;
        }

    }

    public SecondClickFishingSpotAction(Player player, int nodeX, int nodeY, int nodeId) {
        super(player, nodeX, nodeY, nodeId);

    }
}

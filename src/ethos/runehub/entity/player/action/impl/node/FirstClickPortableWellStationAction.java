package ethos.runehub.entity.player.action.impl.node;

import ethos.model.players.Player;
import ethos.runehub.dialog.DialogSequence;
import ethos.runehub.entity.player.action.impl.node.ClickNodeAction;

public class FirstClickPortableWellStationAction extends ClickNodeAction {

    public FirstClickPortableWellStationAction(Player player, int nodeX, int nodeY, int nodeId) {
        super(player, nodeX, nodeY, nodeId);
    }

    @Override
    public void perform() {
        player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                .addStatement("You will receive the bonus as long","as you are with a 3x3","area of the well")
                .build());
    }
}

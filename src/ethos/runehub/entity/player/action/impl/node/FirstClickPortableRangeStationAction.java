package ethos.runehub.entity.player.action.impl.node;

import ethos.model.players.Player;
import ethos.runehub.dialog.DialogSequence;
import ethos.runehub.entity.player.action.impl.node.ClickNodeAction;

public class FirstClickPortableRangeStationAction extends ClickNodeAction {

    public FirstClickPortableRangeStationAction(Player player, int nodeX, int nodeY, int nodeId) {
        super(player, nodeX, nodeY, nodeId);
    }

    @Override
    public void perform() {
        player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                .addStatement("Use your food on this like","it's a normal range")
                .build());
    }
}

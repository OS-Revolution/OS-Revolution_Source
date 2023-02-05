package ethos.runehub.entity.player.action.impl.node;

import ethos.model.players.Player;
import ethos.runehub.entity.player.action.impl.node.ClickNodeAction;
import ethos.runehub.ui.impl.MagicTabletCreationUI;

public class FirstClickLecternAction extends ClickNodeAction {

    @Override
    public void perform() {
        player.sendUI(new MagicTabletCreationUI(player));
    }

    public FirstClickLecternAction(Player player, int nodeX, int nodeY, int nodeId) {
        super(player, nodeX, nodeY, nodeId);
    }
}

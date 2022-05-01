package ethos.runehub.action.click.node.impl.second;

import ethos.model.players.Player;
import ethos.runehub.action.click.node.ClickNodeAction;
import ethos.runehub.dialog.DialogSequence;

public class SecondClickLecternSpaceAction extends ClickNodeAction {

    @Override
    protected void onActionStart() {

    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {
        this.stop();
    }

    @Override
    protected void onUpdate() {

    }

    public SecondClickLecternSpaceAction(Player attachment, int nodeId, int nodeX, int nodeY) {
        super(attachment, 1, nodeId, nodeX, nodeY);
    }
}

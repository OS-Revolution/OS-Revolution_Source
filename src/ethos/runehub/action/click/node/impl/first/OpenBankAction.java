package ethos.runehub.action.click.node.impl.first;

import ethos.model.players.Player;
import ethos.runehub.action.click.node.ClickNodeAction;

public class OpenBankAction extends ClickNodeAction {

    @Override
    protected void onActionStart() {

    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {
        this.getActor().sendAudio(1877);
        this.getActor().getPA().openUpBank();
        this.stop();
    }

    @Override
    protected void onUpdate() {

    }

    public OpenBankAction(Player attachment, int nodeId, int nodeX, int nodeY) {
        super(attachment, 1, nodeId, nodeX, nodeY);
    }
}

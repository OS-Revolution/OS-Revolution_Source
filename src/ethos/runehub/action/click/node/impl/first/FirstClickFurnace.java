package ethos.runehub.action.click.node.impl.first;

import ethos.model.players.Player;
import ethos.runehub.action.click.node.ClickNodeAction;
import ethos.runehub.ui.impl.SmeltingUI;

public class FirstClickFurnace extends ClickNodeAction {

    @Override
    protected void onActionStart() {
        this.getActor().sendUI(new SmeltingUI(this.getActor()));
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

    public FirstClickFurnace(Player attachment, int nodeId, int nodeX, int nodeY) {
        super(attachment, 1, nodeId, nodeX, nodeY);
    }
}

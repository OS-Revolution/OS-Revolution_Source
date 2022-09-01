package ethos.runehub.action.click.node.impl.second;

import ethos.model.players.Player;
import ethos.runehub.action.click.node.ClickNodeAction;
import ethos.runehub.loot.RewardCodeController;

public class SecondClickShinyChest extends ClickNodeAction {

    @Override
    protected void onActionStart() {

    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {
        RewardCodeController.getInstance().requestCodeEntryFromPlayer(this.getActor());
        this.stop();
    }

    @Override
    protected void onUpdate() {

    }

    public SecondClickShinyChest(Player attachment, int nodeId, int nodeX, int nodeY) {
        super(attachment, 1, nodeId, nodeX, nodeY);
    }
}

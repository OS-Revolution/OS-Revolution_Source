package ethos.runehub.entity.player.action.impl.node;

import ethos.model.players.Player;
import ethos.runehub.entity.player.action.Action;
import ethos.runehub.entity.player.action.ActionPriority;

public abstract class ClickNodeAction extends Action {


    public ClickNodeAction(Player player, int nodeX, int nodeY, int nodeId) {
        super(ActionPriority.MEDIUM, false, true, false);
        this.player = player;
        this.nodeX = nodeX;
        this.nodeY = nodeY;
        this.nodeId = nodeId;
    }

    protected final Player player;
    protected final int nodeX, nodeY, nodeId;
}

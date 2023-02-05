package ethos.runehub.entity.player.action.impl;

import ethos.model.players.Player;
import ethos.runehub.entity.player.action.Action;
import ethos.runehub.entity.player.action.ActionPriority;

public abstract class ClickItemAction extends Action {


    public ClickItemAction(Player player, int playerX, int playerY, int itemId) {
        super(ActionPriority.MEDIUM, false, true, false);
        this.player = player;
        this.playerX = playerX;
        this.playerY = playerY;
        this.itemId = itemId;
    }

    protected final Player player;
    protected final int playerX, playerY, itemId;
}

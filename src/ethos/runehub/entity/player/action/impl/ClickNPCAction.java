package ethos.runehub.entity.player.action.impl;

import ethos.model.players.Player;
import ethos.runehub.entity.player.action.Action;
import ethos.runehub.entity.player.action.ActionPriority;

public abstract class ClickNPCAction extends Action {


    public ClickNPCAction(Player player, int npcX, int npcY, int npcId, int npcIndex) {
        super(ActionPriority.MEDIUM, false, true, false);
        this.player = player;
        this.npcX = npcX;
        this.npcY = npcY;
        this.npcId = npcId;
        this.npcIndex = npcIndex;
    }

    protected final Player player;
    protected final int npcX, npcY, npcId,npcIndex;
}

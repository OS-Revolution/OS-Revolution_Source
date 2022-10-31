package ethos.runehub.entity.player.action.impl;

import ethos.model.players.Player;
import ethos.runehub.entity.item.equipment.EquipmentSlot;
import ethos.runehub.entity.player.action.Action;
import ethos.runehub.entity.player.action.ActionPriority;

public class ItemRenderUpdateAction extends Action {


    @Override
    public void perform() {
        if (player != null && player.getOutStream() != null) {
            player.getOutStream().createFrameVarSizeWord(53);
            player.getOutStream().writeWord(frame);
            player.getOutStream().writeWord(player.playerItems.length);
            for (int i = 0; i < player.playerItems.length; i++) {
                if (player.playerItemsN[i] > 254) {
                    player.getOutStream().writeByte(255);
                    player.getOutStream().writeDWord_v2(player.playerItemsN[i]);
                } else {
                    player.getOutStream().writeByte(player.playerItemsN[i]);
                }
                player.getOutStream().writeWordBigEndianA(player.playerItems[i]);
            }
            player.getOutStream().endFrameVarSizeWord();
            player.flushOutStream();
        }
    }

    public ItemRenderUpdateAction(Player player, int frame) {
        super(ActionPriority.MEDIUM, true, true, false);
        this.player = player;
        this.frame = frame;
    }

    private final Player player;
    private final int frame;
}

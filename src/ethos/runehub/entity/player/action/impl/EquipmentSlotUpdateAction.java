package ethos.runehub.entity.player.action.impl;

import ethos.model.players.Player;
import ethos.runehub.entity.item.equipment.EquipmentSlot;
import ethos.runehub.entity.player.action.Action;
import ethos.runehub.entity.player.action.ActionPriority;

public class EquipmentSlotUpdateAction extends Action {


    @Override
    public void perform() {
//        if (player != null && player.getOutStream() != null) {
//            final int itemId = player.getEquipmentIdInSlot(slot);
//            final int amount = player.getContext().getPlayerSaveData().getEquippedAmount()[slot.ordinal()];
//            player.getOutStream().createFrameVarSizeWord(34);
//            player.getOutStream().writeWord(1688);
//            player.getOutStream().writeByte(slot.ordinal());
//            player.getOutStream().writeWord(itemId + 1);
//            if (amount > 254) {
//                player.getOutStream().writeByte(255);
//                player.getOutStream().writeDWord(amount);
//            } else {
//                player.getOutStream().writeByte(amount);
//            }
//            player.getOutStream().endFrameVarSizeWord();
//            player.flushOutStream();
//        }
    }

    public EquipmentSlotUpdateAction(Player player, EquipmentSlot slot) {
        super(ActionPriority.MEDIUM, true, true, false);
        this.player = player;
        this.slot = slot == EquipmentSlot.TWO_HAND ? EquipmentSlot.MAIN_HAND : slot;
    }

    private final Player player;
    private final EquipmentSlot slot;
}

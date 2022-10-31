package ethos.runehub.entity.player.action.impl;

import ethos.model.items.Item;
import ethos.model.players.Player;
import ethos.runehub.entity.item.equipment.EquipmentSlot;
import ethos.runehub.entity.player.action.Action;
import ethos.runehub.entity.player.action.ActionPriority;
import ethos.util.Misc;
import ethos.util.Stream;

public class PlayerAppearanceUpdateAction extends Action {


    @Override
    public void perform() {
//        Player.getPlayerProps().currentOffset = 0;
//        Player.getPlayerProps().writeByte(player.playerAppearance[0]);
//        StringBuilder sb = new StringBuilder(player.titles.getCurrentTitle());
//        if (player.titles.getCurrentTitle().equalsIgnoreCase("None")) {
//            sb.delete(0, sb.length());
//        }
//        Player.getPlayerProps().writeString(sb.toString());
//        sb = new StringBuilder(player.rights.getPrimary().getColor());
//        if (player.titles.getCurrentTitle().equalsIgnoreCase("None")) {
//            sb.delete(0, sb.length());
//        }
//        Player.getPlayerProps().writeString(sb.toString());
//        Player.getPlayerProps().writeByte(player.getHealth().getStatus().getMask());
//        Player.getPlayerProps().writeByte(player.headIcon);
//        Player.getPlayerProps().writeByte(player.headIconPk);
//        Player.getPlayerProps().writeByte(player.modHeadIcon);
//        if (!player.isNpc) {
//            writeEquipment(EquipmentSlot.HEAD);
//            writeEquipment(EquipmentSlot.CAPE);
//            writeEquipment(EquipmentSlot.NECK);
//            writeEquipment(EquipmentSlot.MAIN_HAND);
//            writeEquipment(EquipmentSlot.BODY);
//            writeEquipment(EquipmentSlot.OFF_HAND);
//            if (!Item.isFullBody(player.getEquipmentIdInSlot(EquipmentSlot.BODY))) {
//                Player.getPlayerProps().writeWord(0x100 + player.playerAppearance[3]);
//            } else {
//                Player.getPlayerProps().writeByte(0);
//            }
//            writeEquipment(EquipmentSlot.LEGS);
//            if (!Item.isFullHat(player.getEquipmentIdInSlot(EquipmentSlot.HEAD)) && !Item.isFullMask(player.getEquipmentIdInSlot(EquipmentSlot.HEAD))) {
//                Player.getPlayerProps().writeWord(0x100 + player.playerAppearance[1]);
//            } else {
//                Player.getPlayerProps().writeByte(0);
//            }
//            writeEquipment(EquipmentSlot.HANDS);
//            writeEquipment(EquipmentSlot.FEET);
//            if (player.playerAppearance[0] != 1 && !Item.isFullMask(player.getEquipmentIdInSlot(EquipmentSlot.HEAD))) {
//                Player.getPlayerProps().writeWord(0x100 + player.playerAppearance[7]);
//            } else {
//                Player.getPlayerProps().writeByte(0);
//            }
//        } else {
//            Player.getPlayerProps().writeWord(-1);
//            Player.getPlayerProps().writeWord(player.npcId2);
//        }
//        Player.getPlayerProps().writeByte(player.playerAppearance[8]);
//        Player.getPlayerProps().writeByte(player.playerAppearance[9]);
//        Player.getPlayerProps().writeByte(player.playerAppearance[10]);
//        Player.getPlayerProps().writeByte(player.playerAppearance[11]);
//        Player.getPlayerProps().writeByte(player.playerAppearance[12]);
//        Player.getPlayerProps().writeWord(player.playerStandIndex); // standAnimIndex
//        Player.getPlayerProps().writeWord(player.playerTurnIndex); // standTurnAnimIndex
//        Player.getPlayerProps().writeWord(player.playerWalkIndex); // walkAnimIndex
//        Player.getPlayerProps().writeWord(player.playerTurn180Index); // turn180AnimIndex
//        Player.getPlayerProps().writeWord(player.playerTurn90CWIndex); // turn90CWAnimIndex
//        Player.getPlayerProps().writeWord(player.playerTurn90CCWIndex); // turn90CCWAnimIndex
//        Player.getPlayerProps().writeWord(player.playerRunIndex); // runAnimIndex
//        Player.getPlayerProps().writeQWord(Misc.playerNameToInt64(player.playerName));
//        Player.getPlayerProps().writeByte(player.invisible ? 1 : 0);
//        Player.getPlayerProps().writeByte(player.calculateCombatLevel()); // combat level
//        Player.getPlayerProps().writeByte(player.rights.getPrimary().getValue());
//        Player.getPlayerProps().writeWord(0);
//        stream.writeByteC(Player.getPlayerProps().currentOffset);
//        stream.writeBytes(Player.getPlayerProps().buffer, Player.getPlayerProps().currentOffset, 0);
    }

//    private void writeEquipment(EquipmentSlot slot) {
//        if (player.getEquipmentIdInSlot(slot) > 0) {
//            Player.getPlayerProps().writeWord(0x200 + player.getEquipmentIdInSlot(slot));
//        } else if (slot == EquipmentSlot.BODY) {
//            Player.getPlayerProps().writeWord(0x100 + player.playerAppearance[2]);
//        } else if (slot == EquipmentSlot.LEGS) {
//            Player.getPlayerProps().writeWord(0x100 + player.playerAppearance[5]);
//        } else if (slot == EquipmentSlot.HANDS) {
//            Player.getPlayerProps().writeWord(0x100 + player.playerAppearance[4]);
//        } else if (slot == EquipmentSlot.FEET) {
//            Player.getPlayerProps().writeWord(0x100 + player.playerAppearance[6]);
//        } else {
//            Player.getPlayerProps().writeByte(0);
//        }
//    }

    public PlayerAppearanceUpdateAction(Player player, Stream stream) {
        super(ActionPriority.HIGH, false, false, false);
        this.player = player;
        this.stream = stream;
    }

    private final Player player;
    private final Stream stream;
}

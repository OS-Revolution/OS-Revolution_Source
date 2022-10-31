package ethos.runehub.packet;

import ethos.model.players.PacketType;
import ethos.model.players.Player;

import java.util.logging.Logger;

public class EquipItemPacket implements PacketType {
    @Override
    public void processPacket(Player player, int packetType, int packetSize) {
        int itemId = player.getInStream().readUnsignedWord();
        int slot = player.getInStream().readUnsignedWordA();
        int interfaceId = player.getInStream().readUnsignedWordA();

        Logger.getGlobal().warning("Equipping Item: " + itemId + " Slot: " + slot + " Interface: " + interfaceId);

//        player.equip(itemId,slot);

    }
}

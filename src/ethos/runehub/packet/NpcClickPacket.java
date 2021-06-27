package ethos.runehub.packet;

import ethos.model.players.PacketType;
import ethos.model.players.Player;

public class NpcClickPacket implements PacketType {

    public static final int ATTACK_NPC = 72, MAGE_NPC = 131, FIRST_CLICK = 155, SECOND_CLICK = 17, THIRD_CLICK = 21,
            FOURTH_CLICK = 18;

    @Override
    public void processPacket(Player player, int packetType, int packetSize) {
        switch (packetType) {
            case ATTACK_NPC:

                break;
        }
    }
}

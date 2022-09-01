package ethos.runehub.packet;

import ethos.model.players.PacketType;
import ethos.model.players.Player;

public class BonusXpPacket implements PacketType
{
    @Override
    public void processPacket(Player c, int packetType, int packetSize) {
        System.out.println("Packet Received");
        if (c.getOutStream() != null && c != null) {
            c.getOutStream().createFrameVarSizeWord(127);
            c.getOutStream().writeString("test");
            c.getOutStream().writeWordA(100);
            c.getOutStream().endFrameVarSizeWord();
            c.flushOutStream();
        }
    }
}

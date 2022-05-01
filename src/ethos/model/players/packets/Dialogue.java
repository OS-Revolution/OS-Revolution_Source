package ethos.model.players.packets;

import ethos.model.players.PacketType;
import ethos.model.players.Player;

import java.util.logging.Logger;

/**
 * Dialogue
 **/
public class Dialogue implements PacketType {

	@Override
	public void processPacket(Player c, int packetType, int packetSize) {
		Logger.getGlobal().fine("Processing Dialog Packet");
		if (c.getAttributes().getActiveDialogSequence() != null) {
			Logger.getGlobal().fine("Sending Next in Sequence for: " + c.getAttributes().getActiveDialogSequence());
			c.getAttributes().getActiveDialogSequence().next();
		} else {
			c.getPA().closeAllWindows();
		}
//		if (c.nextChat > 0) {
//			System.out.println("Progress Dialog");
////			c.getDH().sendDialogues(c.nextChat, c.talkingNpc);
//		} else {
//			c.getDH().sendDialogues(0, -1);
//		}

	}

}

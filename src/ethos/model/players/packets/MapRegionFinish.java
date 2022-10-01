package ethos.model.players.packets;

import ethos.Server;
import ethos.model.players.PacketType;
import ethos.model.players.Player;
import ethos.runehub.skill.gathering.farming.FarmingConfig;

public class MapRegionFinish implements PacketType {

	@Override
	public void processPacket(Player c, int packetType, int packetSize) {
		Server.itemHandler.reloadItems(c);
		Server.getGlobalObjects().updateRegionObjects(c);
		int regionX = c.absX >> 3;
		int regionY = c.absY >> 3;
		int regionId = ((regionX / 8) << 8) + (regionY / 8);

		System.out.println("Region ID: " + regionId);
		if (c.getContext().getPlayerSaveData().getFarmingConfig().containsKey(regionId)) {
			final int varbit = c.getContext().getPlayerSaveData().getFarmingConfig().get(regionId).stream().mapToInt(FarmingConfig::varbit).sum();
			c.getPA().sendConfig(529,varbit);
		}
		if (c.getPA().viewingOtherBank) {
			c.getPA().resetOtherBank();
		}
		c.saveFile = true;

		if (c.skullTimer > 0) {
			c.isSkulled = true;
			c.headIconPk = 0;
			c.getPA().requestUpdates();
		}
	}

}

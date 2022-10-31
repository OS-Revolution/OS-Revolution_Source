package ethos.model.players.packets;

import ethos.model.players.PacketType;
import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.skill.gathering.farming.FarmingConfig;
import ethos.runehub.world.WorldController;

public class MapRegionChange implements PacketType {

	@Override
	public void processPacket(Player c, int packetType, int packetSize) {
		System.out.println("Changing region");
		int regionId = RunehubUtils.getRegionId(c.absX,c.absY);
		if (c.getAttributes().getInstanceNodes().containsKey(regionId)) {
			c.getAttributes().getInstanceNodes().get(regionId).forEach(node -> c.getPA().checkObjectSpawn(node));
		}
		if (c.getContext().getPlayerSaveData().farmingConfig().containsKey(regionId)) {
			final int varbit = c.getContext().getPlayerSaveData().farmingConfig().get(regionId).stream().mapToInt(FarmingConfig::varbit).sum();
			c.getPA().sendConfig(529,varbit);

		}
//		WorldController.getInstance().loadRegion(RunehubUtils.getRegionId(c.absX,c.absY));
	}

}

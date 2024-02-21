package ethos.model.players.packets.commands.admin;

import ethos.model.players.Player;
import ethos.model.players.packets.commands.Command;
import ethos.runehub.entity.mob.hostile.HostileMob;
import ethos.runehub.entity.mob.hostile.HostileMobContext;
import ethos.runehub.entity.mob.hostile.HostileMobContextDAO;
import ethos.runehub.entity.mob.hostile.HostileMobIdContextLoader;
import org.runehub.api.io.load.impl.HostileMobNameContextLoader;

/**
 * Gets the data on a specific parameter
 */
public class Data extends Command {

	@Override
	public void execute(Player c, String input) {
		final String[] args = input.split(" ");
		if (args.length == 3) {
			try {
				final int dataTypeParameter = Integer.parseInt(args[0]);
				final int searchTypeParameter = Integer.parseInt(args[1]);
				final String queryParameter = args[2];

				if (dataTypeParameter == 0 ) { //npc
					if (searchTypeParameter == 0) { //id
						int npcId = Integer.parseInt(queryParameter);
						HostileMobContext context = HostileMobIdContextLoader.getInstance().read(npcId);
						c.sendMessage("-----Start Report-----");
						c.sendMessage("Name: " + context.getName());
						c.sendMessage("Cat.: " + context.getCategory());
						c.sendMessage("ID: " + context.getId());
						c.sendMessage("-----End Report-----");
					} else if (searchTypeParameter == 1) { //name
						String npcName = queryParameter;
						HostileMobContextDAO.getInstance().getAllEntries()
								.stream().filter(hostileMobContext -> hostileMobContext.getName().contains(npcName))
								.forEach(hostileMobContext -> {
									c.sendMessage("Matching Name: " + hostileMobContext.getName() + " (" + hostileMobContext.getId() + ")");
								});
					} else {
						c.sendMessage("Invalid SearchType parameter use 0 for ID or 1 for name");
					}
				} else if (dataTypeParameter == 1) { //item
					if (searchTypeParameter == 0) { //id
						int itemId = Integer.parseInt(queryParameter);
					} else if (searchTypeParameter == 1) { //name
						String itemName = queryParameter;
					} else {
						c.sendMessage("Invalid SearchType parameter use 0 for ID or 1 for name");
					}
				} else {
					c.sendMessage("Invalid DataType parameter use 0 for npcs or 1 for items.");
				}

			} catch (NumberFormatException e) {
				c.sendMessage("Invalid parameter value for index 0 or 1 use valid integer.");
			}
		} else {
			c.sendMessage("Use as ::data DataType SearchType Query");
		}
	}

}

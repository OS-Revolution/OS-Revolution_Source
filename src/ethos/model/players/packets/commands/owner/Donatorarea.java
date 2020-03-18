package ethos.model.players.packets.commands.owner;

import java.util.Optional;

import ethos.Server;
import ethos.model.players.Player;
import ethos.model.players.packets.commands.Command;

/**
 * Teleport the player to new dzone
 * 
 * @author Emiel
 */
public class Donatorarea extends Command {

	@Override
	public void execute(Player c, String input) {
		if (Server.getMultiplayerSessionListener().inAnySession(c)) {
			return;
		}
		if (c.inClanWars() || c.inClanWarsSafe()) {
			c.sendMessage("@cr10@You can not teleport from here");
			return;
		}
		if (c.inWild()) {
			c.sendMessage("You can't use this command in the wilderness.");
			return;
		}
		c.getPA().spellTeleport(2910, 5468, 0, false);

	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Teleports you to the new Donator Zone");
	}

}
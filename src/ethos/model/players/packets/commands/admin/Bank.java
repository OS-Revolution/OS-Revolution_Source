package ethos.model.players.packets.commands.admin;

import ethos.model.items.ItemAssistant;
import ethos.model.players.ClientGameTimer;
import ethos.model.players.Player;
import ethos.model.players.packets.commands.Command;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Open the banking interface.
 * 
 * @author Emiel
 */
public class Bank extends Command {

	@Override
	public void execute(Player c, String input) {
		c.sendAudio(1877);
		c.getPA().openUpBank();
	}
}

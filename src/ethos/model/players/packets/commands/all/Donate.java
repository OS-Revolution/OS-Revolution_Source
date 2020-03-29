package ethos.model.players.packets.commands.all;

import java.util.Optional;

import ethos.model.players.Player;
import ethos.model.players.packets.commands.Command;

/**
 * Opens the store page in the default web browser.
 * 
 * @author Emiel
 */
public class Donate extends Command {

	@Override
	public void execute(Player c, String input) {
		c.getPA().sendFrame126("https://os-revolution.com/forum/index.php?/shop/", 12000);
	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Opens our donating page! Remember you donate osrs gp as well.");

	}


}

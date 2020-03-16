package ethos.model.players.packets.commands.all;

import java.util.Optional;

import ethos.model.players.Player;
import ethos.model.players.packets.commands.Command;

/**
 * Open the forums in the default web browser.
 * 
 * @author Emiel
 */
public class Twitter extends Command {

	@Override
	public void execute(Player c, String input) {
		c.getPA().sendFrame126("https://twitter.com/OSRevolution_?s=07", 12000);
	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Takes you to our Official Twitter page");
	}

}

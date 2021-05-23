package ethos.model.players.packets.commands.all;

import java.util.Optional;

import ethos.model.players.Player;
import ethos.model.players.packets.commands.Command;

/**
 * Opens the game rule page in the default web browser.
 * 
 * @author Emiel
 */
public class Rules extends Command {

	@Override
	public void execute(Player c, String input) {
		c.getPA().showInterface(8134);
		c.flushOutStream();
		c.getPA().sendFrame126("@dre@Your Title Here -- added by Pistol Pete :D", 8144);		
		c.getPA().sendFrame126("1", 8148);
		c.getPA().sendFrame126("2", 8149);
		c.getPA().sendFrame126("3", 8150);
		c.getPA().sendFrame126("4", 8151);
		c.getPA().sendFrame126("5", 8152);
		c.getPA().sendFrame126("6", 8153);
		c.getPA().sendFrame126("7", 8154);
		c.getPA().sendFrame126("8", 8155);
		c.getPA().sendFrame126("9", 8156);
		c.getPA().sendFrame126("10", 8157);
		c.getPA().sendFrame126("11", 8158);
		c.getPA().sendFrame126("12", 8159);
		c.getPA().sendFrame126("13", 8160); // replace the numbers with your text, between the ""
		}

}

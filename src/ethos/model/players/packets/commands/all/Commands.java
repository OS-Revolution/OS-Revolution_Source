package ethos.model.players.packets.commands.all;

import java.util.Map.Entry;

import ethos.Server;
import ethos.model.players.Player;
import ethos.model.players.packets.commands.Command;

import java.util.Optional;

/**
 * Shows a list of commands.
 * 
 * @author Emiel
 *
 */
public class Commands extends Command {

	@Override
	public void execute(Player c, String input) {
		if (Server.getMultiplayerSessionListener().inAnySession(c)) {
			return;
		} // 
		int counter = 8144;
		c.getPA().showInterface(8134);
		// Section 1
		c.getPA().sendFrame126("", counter++);
		c.getPA().sendFrame126("@gre@Os-Revolution Commands", counter++);
		c.getPA().sendFrame126("", counter++);
		c.getPA().sendFrame126("", counter++);
		c.getPA().sendFrame126("@dre@Community", counter++);
		c.getPA().sendFrame126("", counter++);
		c.getPA().sendFrame126("@blue@", counter++);
		c.getPA().sendFrame126("@blu@Discord: @bla@[dc] - [discord] - [disc] - [connect]", counter++);
		c.getPA().sendFrame126("@blu@Forums: @bla@[Forum]", counter++);
		c.getPA().sendFrame126("@blu@Vote: @bla@[vote]", counter++);
		c.getPA().sendFrame126("@blu@Hiscores: @bla@[hs] - [highscores] - [hiscores]", counter++);
		// Section 2
		c.getPA().sendFrame126("", counter++);
		c.getPA().sendFrame126("@dre@Donations", counter++);
		c.getPA().sendFrame126("", counter++);
		c.getPA().sendFrame126("@blu@Webstore: @bla@[store] - [shop]", counter++);
		c.getPA().sendFrame126("@blu@Redeem Items: @bla@[claim]", counter++);
		// Section 3
		c.getPA().sendFrame126("", counter++);
		c.getPA().sendFrame126("@dre@YouTube & Social Media", counter++);
		c.getPA().sendFrame126("", counter++);
		c.getPA().sendFrame126("@blu@YouTube: @bla@[yt] - [youtube] - [videos]", counter++);
		c.getPA().sendFrame126("@blu@Channels & Playlists: @bla@[devs] - [dd] - [devdiaries]", counter++);
		// Section 4
		c.getPA().sendFrame126("", counter++);
		c.getPA().sendFrame126("@dre@Tools", counter++);
		c.getPA().sendFrame126("", counter++);
		c.getPA().sendFrame126("@blu@Websearch: @bla@[google]", counter++);
		c.getPA().sendFrame126("@blu@Password / Security: @bla@[changepass]", counter++);
		// Section 5
		c.getPA().sendFrame126("", counter++);
		c.getPA().sendFrame126("@dre@Navigation & Teleporting", counter++);
		c.getPA().sendFrame126("", counter++);
		c.getPA().sendFrame126("@blu@Cities: @bla@[home]", counter++);
	}

	public int sendCommands(Player player, String rank, int counter) {
		for (Entry<String, Command> entry : ethos.model.players.packets.Commands.COMMAND_MAP.entrySet()) {
			if (entry.getKey().contains("." + rank.toLowerCase() + ".")) {
				if (entry.getValue().isHidden()) {
					continue;
				}
				String command = entry.getValue().getClass().getSimpleName().toLowerCase();
				if (entry.getValue().getParameter().isPresent()) {
					command += " @dre@" + entry.getValue().getParameter().get() + "@bla@";
				}
				String description = entry.getValue().getDescription().orElse("No description");
				player.getPA().sendFrame126("@blu@::" + command + "@bla@ - " + description, counter);
				counter++;
			}
		}
		return counter;
	}

	@Override
	public Optional<String> getDescription() {
		return Optional.of("Shows a list of all commands");
	}

}

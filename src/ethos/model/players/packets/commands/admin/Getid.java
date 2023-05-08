package ethos.model.players.packets.commands.admin;

import ethos.Server;
import ethos.model.players.Player;
import ethos.model.players.packets.commands.Command;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;
import org.apache.poi.hssf.record.formula.functions.Match;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.model.entity.item.ItemContext;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Send the item IDs of all matching items to the player.
 * 
 * @author Emiel
 *
 */
public class Getid extends Command {

	@Override
	public void execute(Player c, String input) {
		if (input.length() < 3) {
			c.sendMessage("You must give at least 3 letters of input to narrow down the item.");
		} else {
			List<String> itemNames = ItemIdContextLoader.getInstance().readAll().stream().map(ItemContext::getName).collect(Collectors.toList());
			// Perform fuzzy matching on the item names
			List<String> matches = FuzzySearch.extractTop(input, itemNames, 10)
					.stream().map(ExtractedResult::getString).collect(Collectors.toList());

			// Print the top 10 potential matches
			c.sendMessage("Top 10 potential matches:");
			for (int i = 0; i < matches.size(); i++) {
				c.sendMessage((i + 1) + ". " + matches.get(i));
			}
		}

	}
}

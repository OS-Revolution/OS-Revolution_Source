package ethos.model.players.packets.commands.owner;

import ethos.Server;
import ethos.model.players.Player;
import ethos.model.players.packets.commands.Command;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;
import me.xdrop.fuzzywuzzy.ratios.PartialRatio;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.record.formula.functions.Match;
import org.runehub.api.io.data.impl.ItemContextDAO;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.model.entity.item.ItemContext;

import java.util.*;
import java.util.stream.Collectors;
import org.apache.commons.text.similarity.JaroWinklerDistance;

/**
 * Send the item IDs of all matching items to the player.
 * 
 * @author Emiel
 *
 */
public class Getid extends Command {

	public void execute(Player c, String input) {
		String[] args = input.split("#");
		if (input.length() < 3) {
			c.sendMessage("You must give at least 3 letters of input to narrow down the item.");
		} else {
			int threshold = args.length > 1 ? Integer.parseInt(args[1]) : 50;
			int size = args.length > 2 ? Integer.parseInt(args[2]) : 7;
			List<ItemContext> contexts = ItemContextDAO.getInstance().getAllEntries();
			Map<ItemContext, Double> itemScores = new HashMap<>();
			for (ItemContext item : contexts) {
				double itemScore = score(input, item.getName());
				if (itemScore >= (double) threshold / 100) {
					itemScores.put(item, itemScore);
				}
			}

			List<Map.Entry<ItemContext, Double>> sortedItems = new ArrayList<>(itemScores.entrySet());
			sortedItems.sort((m1, m2) -> {
				int scoreComparison = Double.compare(m2.getValue(), m1.getValue());
				if (scoreComparison != 0) {
					return scoreComparison;
				}
				boolean m1StartsWith = m1.getKey().getName().toLowerCase().startsWith(input.toLowerCase());
				boolean m2StartsWith = m2.getKey().getName().toLowerCase().startsWith(input.toLowerCase());
				if (m1StartsWith && !m2StartsWith) {
					return -1;
				} else if (!m1StartsWith && m2StartsWith) {
					return 1;
				} else {
					return Integer.compare(m2.getKey().getName().length(), m1.getKey().getName().length());
				}
			});

			List<ItemContext> matches = sortedItems.stream().map(Map.Entry::getKey).collect(Collectors.toList());


			c.sendMessage("@blu@Top " + size + " potential matches for: " + input);
			for (int i = 0; i < Math.min(size, matches.size()); i++) {
				c.sendMessage("@blu@" + (i + 1) + ". " + matches.get(i).getName() + " - " + matches.get(i).getId());
			}
		}
	}

	private static double score(String s1, String s2) {
		return StringUtils.getJaroWinklerDistance(s1.toLowerCase(), s2.toLowerCase());
	}
}

package ethos.model.players.packets.commands.all;

import com.everythingrs.donate.Donation;
import ethos.model.players.Player;
import ethos.model.players.PlayerHandler;
import ethos.model.players.packets.commands.Command;
import ethos.util.Misc;
import org.rhd.api.io.loader.LootTableLoader;

/**
 * Auto Donation System / https://EverythingRS.com
 * @author Genesis
 *
 */

public class Claim extends Command {

	@Override
	public void execute(Player player, String input) {
		new Thread() {
			public void run() {
				try {
					Donation[] donations = Donation
							.donations("zv0KjfltVLgXGhSvTkHUbxmAttWqVDTV3DzCvXZBGsr6dHzhI0xJuKeQR30Q1xHHbhP4bsbw", player.playerName);
					if (donations.length == 0) {
						player.sendMessage("You currently don't have any items waiting. You must donate first!");
						return;
					}
					if (donations[0].message != null) {
						player.sendMessage(donations[0].message);
						return;
					}
					for (Donation donate : donations) {
						if(donate.product_name.contains("Bundle")) {
							for (int i = 0; i < donate.product_amount; i++) {
								LootTableLoader.getInstance().get(donate.product_id).roll().forEach(loot ->
										player.getItems().addItem(loot.getItemId(), loot.getAmount())
								);
							}
						} else {
							player.getItems().addItem(donate.product_id, donate.product_amount);
						}
					}
					PlayerHandler.executeGlobalMessage("[@red@DONATE@bla@] Thank you @red@ " + Misc.capitalize(player.playerName) + " @bla@for donating!");
				} catch (Exception e) {
					player.sendMessage("Api Services are currently offline. Please check back shortly");
					e.printStackTrace();
				}
			}
		}.start();
	}

}
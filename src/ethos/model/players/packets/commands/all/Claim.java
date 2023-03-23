package ethos.model.players.packets.commands.all;

import com.everythingrs.donate.Donation;
import ethos.model.players.Player;
import ethos.model.players.PlayerHandler;
import ethos.model.players.packets.commands.Command;
import ethos.runehub.world.WorldSettingsController;
import ethos.util.Misc;

/**
 * Auto Donation System / https://EverythingRS.com
 *
 * @author Genesis
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
                        if (donate.product_name.contains("Bundle")) {
                            for (int i = 0; i < donate.product_amount; i++) {
//                                LootTableLoader.getInstance().get(donate.product_id).roll().forEach(loot ->
//                                        player.getItems().addItem(loot.getItemId(), loot.getAmount())
//                                );
                            }
                        } else if(donate.product_id == 1459 && WorldSettingsController.getInstance().getWorldSettings().getWeekendEventId() == 4) {
                            player.getItems().addItem(donate.product_id, (int) (donate.product_amount * 1.5));
                        } else if (donate.product_id == 1) {
                            WorldSettingsController.getInstance().addBonusXp(player, donate.product_amount);
                        } else if (donate.product_id == 2) {
                            WorldSettingsController.getInstance().addBonusXp(player, 6 * donate.product_amount);
                        } else if (donate.product_id == 3) {
                            WorldSettingsController.getInstance().addBonusXp(player, 12 * donate.product_amount);
                        } else if (donate.product_id == 4) {
                            WorldSettingsController.getInstance().addBonusXp(player, 24 * donate.product_amount);
                        } else if (donate.product_id == 5) {
                            WorldSettingsController.getInstance().addMagicFind(player, donate.product_amount);
                        } else if (donate.product_id == 6) {
                            WorldSettingsController.getInstance().addMagicFind(player, 6 * donate.product_amount);
                        } else if (donate.product_id == 7) {
                            WorldSettingsController.getInstance().addMagicFind(player, 12 * donate.product_amount);
                        } else if (donate.product_id == 8) {
                            WorldSettingsController.getInstance().addMagicFind(player, 24 * donate.product_amount);
                        } else {
                            player.getItems().addItem(donate.product_id, donate.product_amount);
                        }
                    }
                    player.sendMessage("^OsR Your donation is greatly appreciated!");
//                    PlayerHandler.executeGlobalMessage("[@red@DONATE@bla@] Thank you @red@ " + Misc.capitalize(player.playerName) + " @bla@for donating!");
                } catch (Exception e) {
                    player.sendMessage("Api Services are currently offline. Please check back shortly");
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
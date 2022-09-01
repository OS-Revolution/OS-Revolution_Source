package ethos.model.players.packets.commands.admin;

import ethos.model.players.Player;
import ethos.model.players.packets.commands.Command;
import ethos.runehub.loot.RewardCodeController;

public class Generatecode extends Command {
    @Override
    public void execute(Player player, String input) {
        RewardCodeController.getInstance().generateCode();
    }
}

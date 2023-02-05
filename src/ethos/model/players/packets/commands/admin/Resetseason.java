package ethos.model.players.packets.commands.admin;

import ethos.model.players.Player;
import ethos.model.players.packets.commands.Command;
import ethos.runehub.content.PlayPassController;

public class Resetseason extends Command {
    @Override
    public void execute(Player player, String input) {
        PlayPassController.resetPreviousSeasonData();
    }
}

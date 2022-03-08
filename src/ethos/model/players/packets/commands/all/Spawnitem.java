package ethos.model.players.packets.commands.all;

import ethos.model.players.Player;
import ethos.model.players.packets.commands.Command;
import ethos.runehub.ItemSpawnController;
import ethos.util.Misc;

public class Spawnitem extends Command {
    @Override
    public void execute(Player player, String input) {
        try {
            String[] args = input.split(" ");
            if (args.length != 2) {
                throw new IllegalArgumentException();
            }

            int itemId = Integer.parseInt(args[0]);
            int amount = Misc.stringToInt(args[1]);

            ItemSpawnController.getInstance().spawn(player, itemId, amount);
        } catch (IllegalArgumentException e) {
            player.sendMessage("Usage error. Use as ::spawn itemId amount");
        }
    }
}

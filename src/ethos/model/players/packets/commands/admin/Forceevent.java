package ethos.model.players.packets.commands.admin;

import ethos.model.players.Player;
import ethos.model.players.packets.commands.Command;
import ethos.runehub.event.FixedScheduledEventController;
import ethos.runehub.world.WorldSettingsController;

public class Forceevent extends Command {
    @Override
    public void execute(Player player, String input) {
        String[] args = input.split(" ");
        FixedScheduledEventController.getInstance().forceEvent(Integer.parseInt(args[0]));
    }
}

package ethos.model.players.packets.commands.admin;

import ethos.model.players.Player;
import ethos.model.players.packets.commands.Command;
import ethos.phantasye.job.Employer;

public class Forcejob extends Command {
    @Override
    public void execute(Player player, String input) {
        new Employer(player).assignJob();
    }
}

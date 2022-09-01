package ethos.model.players.packets.commands.admin;

import ethos.model.players.Player;
import ethos.model.players.packets.commands.Command;

public class Forcejob extends Command {
    @Override
    public void execute(Player player, String input) {
//        new Employer(player).assignJob();
        final char c = '#';
        final int amount = 45;
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < amount; i++) {
            sb.append(c);
        }
//        player.getDH().send
        player.getDH().sendNpcChat1(sb.toString(),69,"test");
//        player.getDH().sendNpcChat(sb.toString());
    }
}

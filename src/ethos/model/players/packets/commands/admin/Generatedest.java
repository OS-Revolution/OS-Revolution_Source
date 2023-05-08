package ethos.model.players.packets.commands.admin;

import ethos.model.players.Player;
import ethos.model.players.packets.commands.Command;
import ethos.runehub.content.charter.Destination;
import ethos.runehub.content.charter.DestinationCache;
import ethos.runehub.loot.RewardCodeController;
import org.runehub.api.util.IDManager;
import org.runehub.api.util.math.geometry.Point;
import org.runehub.api.util.math.geometry.impl.Rectangle;

public class Generatedest extends Command {
    @Override
    public void execute(Player player, String input) {
        String[] args = input.split(" ");

        String name = args[0].replaceAll("_"," ");
        Rectangle rectangle = new Rectangle(new Point(player.absX,player.absY),new Point(player.absX,player.absY));
        long id = IDManager.getUUID();
        DestinationCache.getInstance().createAndPush(id,new Destination(id,rectangle,name));
        player.sendMessage("Generated destination: " + name);
    }
}

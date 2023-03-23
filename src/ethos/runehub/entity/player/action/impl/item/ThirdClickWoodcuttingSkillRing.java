package ethos.runehub.entity.player.action.impl.item;

import ethos.model.players.Player;
import ethos.runehub.entity.player.action.impl.ClickItemAction;
import org.runehub.api.io.load.impl.ItemIdContextLoader;

public class ThirdClickWoodcuttingSkillRing extends ClickItemAction {

    @Override
    public void perform() {
        if (player.getItems().playerHasItem(8122)
                && player.getItems().playerHasItem(8123)
                && player.getItems().playerHasItem(8124)
        ) {
            player.getItems().deleteItem(8122, 1);
            player.getItems().deleteItem(8123, 1);
            player.getItems().deleteItem(8124, 1);
            player.getItems().addItem(8125, 1);
            player.sendMessage("You fuse the fragments together and form the Nature's sentinel ring!");
        } else {
            player.sendMessage("You need all 3 sentinel fragment rings to do this. <ref=[Learn more],url=https://discordapp.com/channels/681881287166001224/1073595491637547148/1073797924691116062>");
        }
    }

    public ThirdClickWoodcuttingSkillRing(Player player, int playerX, int playerY, int itemId) {
        super(player, playerX, playerY, itemId);
    }
}

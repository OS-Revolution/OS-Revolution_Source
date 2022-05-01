package ethos.runehub;

import ethos.model.items.ItemDefinition;
import ethos.model.players.Player;

import java.text.NumberFormat;

public class ItemSpawnController {

    private static ItemSpawnController instance = null;

    public static ItemSpawnController getInstance() {
        if (instance == null)
            instance = new ItemSpawnController();
        return instance;
    }

    /**
     * returns the items drop value (found in item_definitions.json) or 1 if 0 or less.
     *
     * @param itemId
     * @return
     */
    private long getItemPointValue(int itemId, int amount) {
        return (long) (ItemDefinition.forId(itemId).getDropValue() <= 0 ? 1 : ItemDefinition.forId(itemId).getDropValue()) * amount;
    }

    /**
     * Checks if the player has the points required to spawn item
     *
     * @param player
     * @return
     */
//    private boolean hasPoints(Player player, long points) {
//        return player.getContext().getSpawnPoints().value() >= points;
//    }

    /**
     * Calculates the total cost of the spawn, checks if the player has that amount, removes the points, spawns the item, sends a message
     * @param player
     * @param itemId
     * @param amount
     */
    public void spawn(Player player, int itemId, int amount) {
        final long totalCost = this.getItemPointValue(itemId, amount);

//        if (this.hasPoints(player, totalCost)) {
//            player.getContext().getSpawnPoints().subtract(totalCost);
//            player.getItems().addItemUnderAnyCircumstance(itemId, amount);
//            player.sendMessage("Spawned "
//                    + amount + "x "
//                    + ItemDefinition.forId(itemId).getName()
//                    + " for " + NumberFormat.getInstance().format(totalCost) + " points.");
//        } else {
//            player.sendMessage("This item requires " + NumberFormat.getInstance().format(totalCost) + " points to spawn.");
//        }
    }

    private ItemSpawnController() {

    }

}

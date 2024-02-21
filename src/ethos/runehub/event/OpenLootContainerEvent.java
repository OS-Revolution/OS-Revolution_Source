package ethos.runehub.event;

import com.google.common.base.Preconditions;
import ethos.event.Event;
import ethos.model.items.ItemDefinition;
import ethos.model.players.Player;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.model.entity.item.GameItem;
import org.runehub.api.model.entity.item.loot.Loot;
import org.runehub.api.model.entity.item.loot.Tier;

import java.util.List;

public class OpenLootContainerEvent extends Event<Player> {


    @Override
    public void initialize() {
        try {
            Preconditions.checkArgument(this.getAttachment().getItems().playerHasItem(consumedItem.getId(),consumedItem.getAmount()), "You need at least $" + consumedItem.getAmount() + " @" + consumedItem.getId() + " to do that.");
        } catch (Exception e) {
            this.getAttachment().sendMessage(e.getMessage());
            this.stop();
        }
    }

    @Override
    public void execute() {
        this.getLoot();
        this.getAttachment().sendPlainMessage(":resetBox");
        this.getAttachment().getPA().closeAllWindows();
        this.stop();
    }

    private void getLoot() {
        for (int i = 0; i < lootPool.size(); i++) {
            getAttachment().getItems().deleteItem(consumedItem.getId(), consumedItem.getAmount());
            final String name = ItemIdContextLoader.getInstance().read((int) lootPool.get(i).getId()).getName();
            final Tier tier = getPrizeTier(lootPool.get(i));
            final int amountWon = (int) lootPool.get(i).getAmount();
            getAttachment().sendMessage("^Lootbox Congratulations, you have won a " + "<col=" + getColor(tier) + tier + "@bla@ prize of @blu@" + name + " @red@ x" + amountWon + "@bla@!");
            getAttachment().getItems().addItemUnderAnyCircumstance((int) lootPool.get(i).getId(), (int) lootPool.get(i).getAmount());
            getAttachment().getItems().updateItems();
        }
    }

    private Tier getPrizeTier(Loot loot) {
        return Tier.getTier(loot.getRarity());
    }

    private String getColor(Tier tier) {
        if (tier.getId() == 1) {
            return "0cc1c4>";
        } else if (tier.getId() == 2) {
            return "2c8f18>";
        } else if (tier.getId() == 3) {
            return "d7de10>";
        } else if (tier.getId() == 4) {
            return "de8810>";
        } else if (tier.getId() == 5) {
            return "de1010>";
        } else if (tier.getId() == 6) {
            return "7e10de>";
        } else if (tier.getId() == 7) {
            return "DE10D7>";
        }
        return "0";
    }

    public OpenLootContainerEvent(Player attachment, List<Loot> rewards, GameItem consumedItem) {
        super("lootbox", attachment, 21);
        this.lootPool = rewards;
        this.consumedItem = consumedItem;
    }

    private final GameItem consumedItem;
    private final List<Loot> lootPool;
}

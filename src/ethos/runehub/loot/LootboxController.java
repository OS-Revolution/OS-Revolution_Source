package ethos.runehub.loot;

import ethos.model.players.Player;
import ethos.runehub.LootTableContainerUtils;
import org.runehub.api.model.entity.item.GameItem;
import org.runehub.api.model.entity.item.loot.Loot;
import org.runehub.api.model.entity.item.loot.LootTableContainer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LootboxController {

    private static LootboxController instance = null;

    public static LootboxController getInstance() {
        if (instance == null)
            instance = new LootboxController();
        return instance;
    }

    public void openLootboxWithoutUI(Player player, LootTableContainer container, int rolls) {
        if (container != null) {
            List<Loot> lootList = this.openLootContainer(0, rolls, container);
            player.getItems().delete(container.getContainerId(), 1);
            lootList.stream()
                    .map(loot -> new GameItem((int) loot.getId(), (int) loot.getAmount()))
                    .forEach(gameItem -> player.getItems().addOrDropItem(gameItem.getId(), gameItem.getAmount()));
        }
    }

    private List<Loot> openLootContainer(float magicFind, int rolls, LootTableContainer container) {
        List<Loot> loot = new ArrayList<>();

        for (int i = 0; i < rolls; i++) {
            loot.addAll(LootTableContainerUtils.open(container,magicFind));
        }
        return loot;
    }

    private LootboxController() {

    }
}

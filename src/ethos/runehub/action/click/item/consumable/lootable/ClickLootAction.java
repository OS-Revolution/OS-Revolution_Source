package ethos.runehub.action.click.item.consumable.lootable;

import ethos.model.players.Player;
import ethos.runehub.action.click.item.ClickConsumableItemAction;
import org.runehub.api.io.load.impl.LootTableContainerLoader;
import org.runehub.api.io.load.impl.LootTableLoader;
import org.runehub.api.model.entity.item.loot.Loot;

import java.util.ArrayList;

public class ClickLootAction extends ClickConsumableItemAction {

    @Override
    protected void consume() {
        double playerMF = this.getActor().getContext().getPlayerSaveData().getMagicFind().value();
        float mf = (float) playerMF;
        final Loot lootTable = new ArrayList<>(LootTableContainerLoader.getInstance().readAll().stream().filter(lootTableContainer -> lootTableContainer.getContainerId() == this.getItemId()).findAny().orElseThrow().roll(mf)).get(0);

        LootTableLoader.getInstance().read(lootTable.getId()).roll(mf).forEach(loot -> {
                    this.getActor().getItems().addItem((int) loot.getId(), (int) loot.getAmount());
                });

    }

    public ClickLootAction(Player attachment, int itemId, int itemAmount, int itemSlot) {
        super(attachment, 1, itemId, itemAmount, itemSlot);
    }
}

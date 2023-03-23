package ethos.runehub.event.chest;

import org.runehub.api.io.data.impl.LootTableContainerDAO;
import org.runehub.api.model.entity.item.loot.LootTableContainerEntry;

public class NatureSentinelPromotionalChestEvent extends PromotionalChestEvent {

    @Override
    public void onExecute() {
        BASE_CONTAINER.getLootTables().clear();
        BASE_CONTAINER.getLootTables().add(new LootTableContainerEntry(-1200788003530585034L, 0.85D));
        BASE_CONTAINER.getLootTables().add(new LootTableContainerEntry(BASE_TABLE.getId(), 0.90D));
        LootTableContainerDAO.getInstance().update(BASE_CONTAINER);
    }

    public NatureSentinelPromotionalChestEvent() {
        super("Nature's $Sentinel", 3,"https://discordapp.com/channels/681881287166001224/1073595491637547148/1073797924691116062");
    }
}

package ethos.runehub.event.chest;

import org.runehub.api.io.data.impl.LootTableContainerDAO;
import org.runehub.api.model.entity.item.loot.LootTableContainerEntry;

public class PrismaniaPromotionalChestEvent extends PromotionalChestEvent {

    @Override
    public void onExecute() {
        BASE_CONTAINER.getLootTables().clear();
        BASE_CONTAINER.getLootTables().add(new LootTableContainerEntry(-5063882441400499659L, 0.99D));
        BASE_CONTAINER.getLootTables().add(new LootTableContainerEntry(BASE_TABLE.getId(), 0.99D));
        LootTableContainerDAO.getInstance().update(BASE_CONTAINER);
    }

    public PrismaniaPromotionalChestEvent() {
        super("Prismania", 1,"https://discordapp.com/channels/681881287166001224/1073595491637547148/1073601915344134306");
    }
}
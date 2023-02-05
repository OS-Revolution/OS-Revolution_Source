package ethos.runehub.event.dnd;

import ethos.runehub.LootTableContainerUtils;
import ethos.runehub.event.FixedScheduleEvent;
import org.runehub.api.io.data.impl.LootTableContainerDAO;
import org.runehub.api.io.load.impl.LootTableContainerLoader;
import org.runehub.api.io.load.impl.TierLoader;
import org.runehub.api.model.entity.item.loot.LootTableContainer;
import org.runehub.api.model.entity.item.loot.LootTableContainerEntry;
import org.runehub.api.model.entity.item.loot.Tier;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Test3Event extends FixedScheduleEvent {

    @Override
    public void execute() {
        System.out.println("Executing test event");
        LootTableContainer oldContainer = LootTableContainerDAO.getInstance().read(8806239177860205239L);
        List<LootTableContainerEntry> tables = oldContainer.getLootTables();

        tables.clear();
        tables.add(new LootTableContainerEntry(1144203487647319828L, 0.99D));
        tables.add(new LootTableContainerEntry(-2682234896335024906L,0.70D));

        LootTableContainer newContainer = new LootTableContainer(oldContainer.getId(),oldContainer.getContainerId(), tables);
        LootTableContainerDAO.getInstance().update(newContainer);
    }

    private void resetShinyChestToDefault() {
        LootTableContainer oldContainer = LootTableContainerDAO.getInstance().read(8806239177860205239L);
        List<LootTableContainerEntry> tables = oldContainer.getLootTables();

        tables.clear();
        tables.add(new LootTableContainerEntry(-2682234896335024906L,1.0));

        LootTableContainer newContainer = new LootTableContainer(oldContainer.getId(),oldContainer.getContainerId(), tables);
        LootTableContainerDAO.getInstance().update(newContainer);
    }

    public Test3Event() {
        super(Duration.of(1, ChronoUnit.DAYS).toMillis(),"b");
    }

}

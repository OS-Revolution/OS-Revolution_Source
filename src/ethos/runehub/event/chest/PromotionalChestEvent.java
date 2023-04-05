package ethos.runehub.event.chest;

import ethos.runehub.event.FixedScheduleEvent;
import ethos.runehub.world.WorldSettingsController;
import ethos.util.Misc;
import org.runehub.api.io.data.impl.LootTableContainerDAO;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.io.load.impl.LootTableLoader;
import org.runehub.api.model.entity.item.loot.LootTable;
import org.runehub.api.model.entity.item.loot.LootTableContainer;
import org.runehub.api.model.entity.item.loot.LootTableContainerEntry;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

public abstract class PromotionalChestEvent extends FixedScheduleEvent {

    protected static final LootTable BASE_TABLE = LootTableLoader.getInstance().read(-2682234896335024906L);
    protected static final LootTableContainer BASE_CONTAINER = LootTableContainerDAO.getInstance().read(8806239177860205239L);

    protected abstract void onExecute();

    @Override
    public void execute() {
        if (WorldSettingsController.getInstance().getWorldSettings().getCurrentEventId() != 0) {
            WorldSettingsController.getInstance().getWorldSettings().setCurrentEventId(0);
            WorldSettingsController.getInstance().saveSettings();
            resetShinyChestToDefault();
        } else {
            this.onExecute();
        }
    }

    public static void resetShinyChestToDefault() {
        LootTableContainer oldContainer = LootTableContainerDAO.getInstance().read(8806239177860205239L);
        List<LootTableContainerEntry> tables = oldContainer.getLootTables();

        tables.clear();
        tables.add(new LootTableContainerEntry(-2682234896335024906L, 1.0));

        LootTableContainer newContainer = new LootTableContainer(oldContainer.getId(), oldContainer.getContainerId(), tables);
        LootTableContainerDAO.getInstance().update(newContainer);
    }

    public int getEventId() {
        return eventId;
    }

    public String getUrl() {
        return url;
    }

    public PromotionalChestEvent(String name, int eventId, String url) {
        super(Duration.of(14, ChronoUnit.DAYS).toMillis(), name);
        this.eventId = eventId;
        this.url = url;
    }

    private final int eventId;
    private final String url;
}

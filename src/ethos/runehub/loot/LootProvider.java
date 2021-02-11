package ethos.runehub.loot;

import org.rhd.api.model.LootTable;
import org.rhd.api.model.LootTableContainer;
import org.runehub.app.editor.l.model.entity.loot.LootTableContext;
import org.runehub.app.editor.l.model.entity.loot.LootTableProvider;
import org.runehub.app.editor.l.model.entity.loot.container.LootContainerContext;
import org.runehub.app.editor.l.model.entity.loot.container.LootContainerProvider;
import org.runehub.app.editor.l.model.entity.loot.container.LootContainerType;

public class LootProvider {

    public static final int ITEM = 0;
    public static final int NPC = 1;
    public static final int OBJECT = 2;
    public static final int MISC = 3;

    private static LootProvider instance = null;

    public static LootProvider getInstance() {
        if (instance == null)
            instance = new LootProvider();
        return instance;
    }

    public LootTableContainer getLootContainer(int id, int type) {
        return LootContainerProvider.getInstance().getLootContainer(id,this.getType(type));
    }

    public LootContainerContext getLootContainerContext(int id, int type) {
        return LootContainerProvider.getInstance().getLootContainerContext(id,this.getType(type));
    }

    public LootTable getLootTable(int id) {
        return LootTableProvider.getInstance().getLootTable(id);
    }

    public LootTableContext getLootTableContext(int id) {
        return LootTableProvider.getInstance().getLootTableContext(id);
    }

    private LootContainerType getType(int type) {
        if (type == ITEM)
            return LootContainerType.ITEM;
        else if (type == NPC)
            return LootContainerType.NPC;
        else if (type == OBJECT)
            return LootContainerType.OBJECT;
        else if (type == MISC)
            return LootContainerType.MISC;
        else throw new NullPointerException("No Such Type: " + type);
    }

    private LootProvider() {}
}

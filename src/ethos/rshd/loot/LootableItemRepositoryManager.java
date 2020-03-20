package ethos.rshd.loot;

import org.menaphos.model.loot.LootableItem;
import org.menaphos.model.loot.LootableItemRepository;
import org.menaphos.model.loot.LootableNpc;
import org.menaphos.model.loot.LootableNpcRepository;
import org.phantasye.RepositoryManager;

public final class LootableItemRepositoryManager extends RepositoryManager<LootableItem, LootableItemRepository> {

    private static LootableItemRepositoryManager instance = null;

    public static LootableItemRepositoryManager getInstance() {
        if (instance == null)
            instance = new LootableItemRepositoryManager();
        return instance;
    }

    private LootableItemRepositoryManager() {
        super("./Data/rshd/lootable-items.json", LootableItemRepository.class);
    }
}

package ethos.rshd.loot;

import org.menaphos.model.loot.LootableItem;
import org.menaphos.model.loot.LootableItemRepository;
import org.menaphos.model.loot.LootableObject;
import org.menaphos.model.loot.LootableObjectRepository;
import org.phantasye.RepositoryManager;

public final class LootableObjectRepositoryManager extends RepositoryManager<LootableObject, LootableObjectRepository> {

    private static LootableObjectRepositoryManager instance = null;

    public static LootableObjectRepositoryManager getInstance() {
        if (instance == null)
            instance = new LootableObjectRepositoryManager();
        return instance;
    }

    private LootableObjectRepositoryManager() {
        super("./Data/rshd/lootable-objects.json", LootableObjectRepository.class);
    }
}

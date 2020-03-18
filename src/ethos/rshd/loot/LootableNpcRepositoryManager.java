package ethos.rshd.loot;

import org.menaphos.model.loot.LootableNpc;
import org.menaphos.model.loot.LootableNpcRepository;
import org.phantasye.RepositoryManager;

public final class LootableNpcRepositoryManager extends RepositoryManager<LootableNpc, LootableNpcRepository> {

    private static LootableNpcRepositoryManager instance = null;

    public static LootableNpcRepositoryManager getInstance() {
        if (instance == null)
            instance = new LootableNpcRepositoryManager();
        return instance;
    }

    private LootableNpcRepositoryManager() {
        super("./Data/rshd/lootable-npcs.json", LootableNpcRepository.class);
    }
}

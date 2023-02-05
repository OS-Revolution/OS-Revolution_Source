package ethos.runehub.skill.artisan.crafting;

import org.runehub.api.io.load.LazyLoader;

public class CraftingItemReactionLoader extends LazyLoader<Long, CraftingItemReaction> {

    private static CraftingItemReactionLoader instance = null;

    public static CraftingItemReactionLoader getInstance() {
        if (instance == null)
            instance = new CraftingItemReactionLoader();
        return instance;
    }

    private CraftingItemReactionLoader() {
        super(CraftingItemReactionDAO.getInstance());
    }
}

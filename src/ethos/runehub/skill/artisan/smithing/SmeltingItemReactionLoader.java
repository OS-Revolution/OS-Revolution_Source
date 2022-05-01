package ethos.runehub.skill.artisan.smithing;

import org.runehub.api.io.load.LazyLoader;

public class SmeltingItemReactionLoader extends LazyLoader<Long, SmeltingItemReaction> {

    private static SmeltingItemReactionLoader instance = null;

    public static SmeltingItemReactionLoader getInstance() {
        if (instance == null)
            instance = new SmeltingItemReactionLoader();
        return instance;
    }

    private SmeltingItemReactionLoader() {
        super(SmeltingItemReactionDAO.getInstance());
    }
}

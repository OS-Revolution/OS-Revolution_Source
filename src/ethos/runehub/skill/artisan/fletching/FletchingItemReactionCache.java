package ethos.runehub.skill.artisan.fletching;

import org.runehub.api.io.load.LazyLoader;

public class FletchingItemReactionCache extends LazyLoader<Long, FletchingItemReaction> {

    private static FletchingItemReactionCache instance = null;

    public static FletchingItemReactionCache getInstance() {
        if (instance == null)
            instance = new FletchingItemReactionCache();
        return instance;
    }

    private FletchingItemReactionCache() {
        super(FletchingItemReactionDAO.getInstance());
    }
}

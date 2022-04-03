package ethos.runehub.skill.artisan.cooking;

import org.runehub.api.io.load.LazyLoader;

public class CookingItemReactionLoader extends LazyLoader<Long, CookingItemReaction> {

    private static CookingItemReactionLoader instance = null;

    public static CookingItemReactionLoader getInstance() {
        if (instance == null)
            instance = new CookingItemReactionLoader();
        return instance;
    }

    private CookingItemReactionLoader() {
        super(CookingItemReactionDAO.getInstance());
    }
}

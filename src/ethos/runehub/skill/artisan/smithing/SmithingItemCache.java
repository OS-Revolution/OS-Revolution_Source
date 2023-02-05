package ethos.runehub.skill.artisan.smithing;

import org.runehub.api.io.load.LazyLoader;

public class SmithingItemCache extends LazyLoader<Integer, SmithingItem> {

    private static SmithingItemCache instance = null;

    public static SmithingItemCache getInstance() {
        if (instance == null)
            instance = new SmithingItemCache();
        return instance;
    }

    private SmithingItemCache() {
        super(SmithingItemDAO.getInstance());
    }
}

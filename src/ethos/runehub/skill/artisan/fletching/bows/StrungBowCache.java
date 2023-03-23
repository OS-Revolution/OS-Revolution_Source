package ethos.runehub.skill.artisan.fletching.bows;

import org.runehub.api.io.load.LazyLoader;

public class StrungBowCache extends LazyLoader<Integer, StrungBow> {

    private static StrungBowCache instance = null;

    public static StrungBowCache getInstance() {
        if (instance == null)
            instance = new StrungBowCache();
        return instance;
    }

    private StrungBowCache() {
        super(StrungBowDAO.getInstance());
    }
}

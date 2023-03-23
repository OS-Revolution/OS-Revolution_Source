package ethos.runehub.skill.artisan.fletching.bows;

import org.runehub.api.io.load.LazyLoader;

public class UnstrungBowCache extends LazyLoader<Integer, UnstrungBow> {

    private static UnstrungBowCache instance = null;

    public static UnstrungBowCache getInstance() {
        if (instance == null)
            instance = new UnstrungBowCache();
        return instance;
    }

    private UnstrungBowCache() {
        super(UnstrungBowDAO.getInstance());
    }
}

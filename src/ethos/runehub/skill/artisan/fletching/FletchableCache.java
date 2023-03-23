package ethos.runehub.skill.artisan.fletching;

import org.runehub.api.io.load.LazyLoader;

public class FletchableCache extends LazyLoader<Integer, Fletchable> {

    private static FletchableCache instance = null;

    public static FletchableCache getInstance() {
        if (instance == null)
            instance = new FletchableCache();
        return instance;
    }

    private FletchableCache() {
        super(FletchableDAO.getInstance());
    }
}

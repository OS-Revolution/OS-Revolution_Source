package ethos.runehub.skill.artisan.crafting.armor.leather;

import org.runehub.api.io.load.LazyLoader;

public class LeatherCache extends LazyLoader<Integer, Leather> {

    private static LeatherCache instance = null;

    public static LeatherCache getInstance() {
        if (instance == null)
            instance = new LeatherCache();
        return instance;
    }

    private LeatherCache() {
        super(LeatherDAO.getInstance());
    }
}

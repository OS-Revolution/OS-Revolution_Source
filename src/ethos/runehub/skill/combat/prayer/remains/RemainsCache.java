package ethos.runehub.skill.combat.prayer.remains;

import org.runehub.api.io.load.LazyLoader;

public class RemainsCache extends LazyLoader<Integer, Remains> {

    private static RemainsCache instance = null;

    public static RemainsCache getInstance() {
        if (instance == null)
            instance = new RemainsCache();
        return instance;
    }

    private RemainsCache() {
        super(RemainsDAO.getInstance());
    }
}

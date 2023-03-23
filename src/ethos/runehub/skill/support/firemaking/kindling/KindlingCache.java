package ethos.runehub.skill.support.firemaking.kindling;

import org.runehub.api.io.load.LazyLoader;

public class KindlingCache extends LazyLoader<Integer, Kindling> {

    private static KindlingCache instance = null;

    public static KindlingCache getInstance() {
        if (instance == null)
            instance = new KindlingCache();
        return instance;
    }

    private KindlingCache() {
        super(KindlingDAO.getInstance());
    }
}

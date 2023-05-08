package ethos.runehub.content.charter;

import org.runehub.api.io.load.LazyLoader;

public class CharterCache extends LazyLoader<Long, Charter> {

    private static CharterCache instance = null;

    public static CharterCache getInstance() {
        if (instance == null)
            instance = new CharterCache();
        return instance;
    }

    private CharterCache() {
        super(CharterDAO.getInstance());
    }
}

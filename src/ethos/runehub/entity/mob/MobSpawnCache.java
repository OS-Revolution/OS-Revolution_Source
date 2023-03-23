package ethos.runehub.entity.mob;

import org.runehub.api.io.load.LazyLoader;

public class MobSpawnCache extends LazyLoader<Long, MobSpawn> {

    private static MobSpawnCache instance = null;

    public static MobSpawnCache getInstance() {
        if (instance == null)
            instance = new MobSpawnCache();
        return instance;
    }

    private MobSpawnCache() {
        super(MobSpawnDAO.getInstance());
    }
}

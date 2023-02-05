package ethos.runehub.content.instance.impl;

import org.runehub.api.io.load.LazyLoader;

public class InstanceBossCache extends LazyLoader<Integer, InstanceBoss> {

    private static InstanceBossCache instance = null;

    public static InstanceBossCache getInstance() {
        if (instance == null)
            instance = new InstanceBossCache();
        return instance;
    }

    private InstanceBossCache() {
        super(InstanceBossDAO.getInstance());
    }
}

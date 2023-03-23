package ethos.runehub.skill.support.slayer;

import org.runehub.api.io.load.LazyLoader;

public class SuperiorSlayerMonsterCache extends LazyLoader<Integer, SuperiorSlayerMonster> {

    private static SuperiorSlayerMonsterCache instance = null;

    public static SuperiorSlayerMonsterCache getInstance() {
        if (instance == null)
            instance = new SuperiorSlayerMonsterCache();
        return instance;
    }

    private SuperiorSlayerMonsterCache() {
        super(SuperiorSlayerMonsterDAO.getInstance());
    }
}

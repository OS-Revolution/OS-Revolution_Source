package ethos.runehub.loot;

import org.runehub.api.io.load.LazyLoader;

public class RewardCodeCache extends LazyLoader <Long,RewardCode> {

    private static RewardCodeCache instance = null;

    public static RewardCodeCache getInstance() {
        if (instance == null)
            instance = new RewardCodeCache();
        return instance;
    }

    private RewardCodeCache() {
        super(RewardCodeDAO.getInstance());
    }
}

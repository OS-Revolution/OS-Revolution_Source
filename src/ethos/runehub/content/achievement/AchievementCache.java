package ethos.runehub.content.achievement;

import org.runehub.api.io.load.LazyLoader;

public class AchievementCache extends LazyLoader<Long, Achievement> {

    private static AchievementCache instance = null;

    public static AchievementCache getInstance() {
        if (instance == null)
            instance = new AchievementCache();
        return instance;
    }

    private AchievementCache() {
        super(AchievementDAO.getInstance());
    }
}

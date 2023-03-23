package ethos.runehub.skill.combat.prayer.remains;

import org.runehub.api.io.load.LazyLoader;

public class RemainsItemReactionLoader extends LazyLoader<Long, RemainsItemReaction> {

    private static RemainsItemReactionLoader instance = null;

    public static RemainsItemReactionLoader getInstance() {
        if (instance == null)
            instance = new RemainsItemReactionLoader();
        return instance;
    }

    private RemainsItemReactionLoader() {
        super(RemainsItemReactionDAO.getInstance());
    }
}

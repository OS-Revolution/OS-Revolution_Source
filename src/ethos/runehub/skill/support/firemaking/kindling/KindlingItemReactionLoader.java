package ethos.runehub.skill.support.firemaking.kindling;

import org.runehub.api.io.load.LazyLoader;

public class KindlingItemReactionLoader extends LazyLoader<Long, KindlingItemReaction> {

    private static KindlingItemReactionLoader instance = null;

    public static KindlingItemReactionLoader getInstance() {
        if (instance == null)
            instance = new KindlingItemReactionLoader();
        return instance;
    }

    private KindlingItemReactionLoader() {
        super(KindlingItemReactionDAO.getInstance());
    }
}

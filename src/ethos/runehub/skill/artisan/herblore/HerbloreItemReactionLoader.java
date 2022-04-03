package ethos.runehub.skill.artisan.herblore;

import org.runehub.api.io.load.LazyLoader;

public class HerbloreItemReactionLoader extends LazyLoader<Long, HerbloreItemReaction> {

    private static HerbloreItemReactionLoader instance = null;

    public static HerbloreItemReactionLoader getInstance() {
        if (instance == null)
            instance = new HerbloreItemReactionLoader();
        return instance;
    }

    private HerbloreItemReactionLoader() {
        super(HerbloreItemReactionDAO.getInstance());
    }
}

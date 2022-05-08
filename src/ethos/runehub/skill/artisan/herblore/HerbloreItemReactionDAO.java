package ethos.runehub.skill.artisan.herblore;

import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;
import org.runehub.api.io.file.impl.APIFileSystem;

public class HerbloreItemReactionDAO extends BetaAbstractDataAcessObject<HerbloreItemReaction> {

    private static HerbloreItemReactionDAO instance = null;

    public static HerbloreItemReactionDAO getInstance() {
        if (instance == null)
            instance = new HerbloreItemReactionDAO();
        return instance;
    }

    private HerbloreItemReactionDAO() {
        super("./Data/runehub/db/item-interactions.db", HerbloreItemReaction.class);
    }
}

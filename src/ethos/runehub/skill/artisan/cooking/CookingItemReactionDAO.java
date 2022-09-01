package ethos.runehub.skill.artisan.cooking;

import ethos.runehub.RunehubConstants;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;
import org.runehub.api.io.file.impl.APIFileSystem;

public class CookingItemReactionDAO extends BetaAbstractDataAcessObject<CookingItemReaction> {

    private static CookingItemReactionDAO instance = null;

    public static CookingItemReactionDAO getInstance() {
        if (instance == null)
            instance = new CookingItemReactionDAO();
        return instance;
    }

    private CookingItemReactionDAO() {
        super(RunehubConstants.ITEM_INTERACTION_DB, CookingItemReaction.class);
    }
}

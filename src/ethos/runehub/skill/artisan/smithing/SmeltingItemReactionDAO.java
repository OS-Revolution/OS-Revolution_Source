package ethos.runehub.skill.artisan.smithing;

import ethos.runehub.RunehubConstants;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;
import org.runehub.api.io.file.impl.APIFileSystem;

public class SmeltingItemReactionDAO extends BetaAbstractDataAcessObject<SmeltingItemReaction> {

    private static SmeltingItemReactionDAO instance = null;

    public static SmeltingItemReactionDAO getInstance() {
        if (instance == null)
            instance = new SmeltingItemReactionDAO();
        return instance;
    }

    private SmeltingItemReactionDAO() {
        super(RunehubConstants.ITEM_INTERACTION_DB, SmeltingItemReaction.class);
    }
}

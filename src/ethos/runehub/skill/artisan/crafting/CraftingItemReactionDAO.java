package ethos.runehub.skill.artisan.crafting;

import ethos.runehub.RunehubConstants;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class CraftingItemReactionDAO extends BetaAbstractDataAcessObject<CraftingItemReaction> {

    private static CraftingItemReactionDAO instance = null;

    public static CraftingItemReactionDAO getInstance() {
        if (instance == null)
            instance = new CraftingItemReactionDAO();
        return instance;
    }

    private CraftingItemReactionDAO() {
        super(RunehubConstants.ITEM_INTERACTION_DB, CraftingItemReaction.class);
    }
}

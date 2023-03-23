package ethos.runehub.skill.artisan.fletching;

import ethos.runehub.RunehubConstants;
import ethos.runehub.skill.artisan.crafting.CraftingItemReaction;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class FletchingItemReactionDAO extends BetaAbstractDataAcessObject<FletchingItemReaction> {

    private static FletchingItemReactionDAO instance = null;

    public static FletchingItemReactionDAO getInstance() {
        if (instance == null)
            instance = new FletchingItemReactionDAO();
        return instance;
    }

    private FletchingItemReactionDAO() {
        super(RunehubConstants.ITEM_INTERACTION_DB, FletchingItemReaction.class);
    }
}

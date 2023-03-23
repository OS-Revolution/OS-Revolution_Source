package ethos.runehub.skill.combat.prayer.remains;

import ethos.runehub.RunehubConstants;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class RemainsItemReactionDAO extends BetaAbstractDataAcessObject<RemainsItemReaction> {

    private static RemainsItemReactionDAO instance = null;

    public static RemainsItemReactionDAO getInstance() {
        if (instance == null)
            instance = new RemainsItemReactionDAO();
        return instance;
    }

    private RemainsItemReactionDAO() {
        super(RunehubConstants.ITEM_INTERACTION_DB, RemainsItemReaction.class);
    }
}

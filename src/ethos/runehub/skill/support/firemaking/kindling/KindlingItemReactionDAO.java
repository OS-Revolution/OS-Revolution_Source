package ethos.runehub.skill.support.firemaking.kindling;

import ethos.runehub.RunehubConstants;
import ethos.runehub.skill.combat.prayer.remains.Remains;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class KindlingItemReactionDAO extends BetaAbstractDataAcessObject<KindlingItemReaction> {

    private static KindlingItemReactionDAO instance = null;

    public static KindlingItemReactionDAO getInstance() {
        if (instance == null)
            instance = new KindlingItemReactionDAO();
        return instance;
    }

    private KindlingItemReactionDAO() {
        super(RunehubConstants.ITEM_INTERACTION_DB, KindlingItemReaction.class);
    }
}

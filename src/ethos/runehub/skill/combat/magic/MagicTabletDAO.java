package ethos.runehub.skill.combat.magic;

import ethos.runehub.RunehubConstants;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class MagicTabletDAO extends BetaAbstractDataAcessObject<MagicTablet> {

    private static MagicTabletDAO instance = null;

    public static MagicTabletDAO getInstance() {
        if (instance == null)
            instance = new MagicTabletDAO();
        return instance;
    }

    private MagicTabletDAO() {
        super(RunehubConstants.ITEM_INTERACTION_DB, MagicTablet.class);
    }
}

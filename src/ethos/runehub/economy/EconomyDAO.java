package ethos.runehub.economy;

import ethos.runehub.RunehubConstants;
import ethos.runehub.skill.combat.magic.Enchantable;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class EconomyDAO extends BetaAbstractDataAcessObject<Economy> {

    private static EconomyDAO instance = null;

    public static EconomyDAO getInstance() {
        if (instance == null)
            instance = new EconomyDAO();
        return instance;
    }

    private EconomyDAO() {
        super(RunehubConstants.ITEM_INTERACTION_DB, Economy.class);
    }
}

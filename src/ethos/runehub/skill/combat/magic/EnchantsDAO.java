package ethos.runehub.skill.combat.magic;

import ethos.runehub.RunehubConstants;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class EnchantsDAO extends BetaAbstractDataAcessObject<Enchantable> {

    private static EnchantsDAO instance = null;

    public static EnchantsDAO getInstance() {
        if (instance == null)
            instance = new EnchantsDAO();
        return instance;
    }

    private EnchantsDAO() {
        super(RunehubConstants.ITEM_INTERACTION_DB, Enchantable.class);
    }
}

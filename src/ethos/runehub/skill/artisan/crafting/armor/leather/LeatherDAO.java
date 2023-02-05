package ethos.runehub.skill.artisan.crafting.armor.leather;

import ethos.runehub.RunehubConstants;
import ethos.runehub.skill.artisan.crafting.jewellery.Jewellery;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class LeatherDAO extends BetaAbstractDataAcessObject<Leather> {

    private static LeatherDAO instance = null;

    public static LeatherDAO getInstance() {
        if (instance == null)
            instance = new LeatherDAO();
        return instance;
    }

    private LeatherDAO() {
        super(RunehubConstants.ITEM_INTERACTION_DB, Leather.class);
    }
}

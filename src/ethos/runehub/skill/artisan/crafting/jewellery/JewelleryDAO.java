package ethos.runehub.skill.artisan.crafting.jewellery;

import ethos.runehub.RunehubConstants;
import ethos.runehub.skill.combat.magic.MagicTablet;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class JewelleryDAO extends BetaAbstractDataAcessObject<Jewellery> {

    private static JewelleryDAO instance = null;

    public static JewelleryDAO getInstance() {
        if (instance == null)
            instance = new JewelleryDAO();
        return instance;
    }

    private JewelleryDAO() {
        super(RunehubConstants.ITEM_INTERACTION_DB, Jewellery.class);
    }
}

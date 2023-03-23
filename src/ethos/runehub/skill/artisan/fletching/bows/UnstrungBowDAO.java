package ethos.runehub.skill.artisan.fletching.bows;

import ethos.runehub.RunehubConstants;
import ethos.runehub.skill.artisan.crafting.jewellery.Jewellery;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class UnstrungBowDAO extends BetaAbstractDataAcessObject<UnstrungBow> {

    private static UnstrungBowDAO instance = null;

    public static UnstrungBowDAO getInstance() {
        if (instance == null)
            instance = new UnstrungBowDAO();
        return instance;
    }

    private UnstrungBowDAO() {
        super(RunehubConstants.SKILL_DB, UnstrungBow.class);
    }
}

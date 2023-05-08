package ethos.runehub.skill.artisan.cooking.food;

import ethos.runehub.RunehubConstants;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class BrewDAO extends BetaAbstractDataAcessObject<Brew> {

    private static BrewDAO instance = null;

    public static BrewDAO getInstance() {
        if (instance == null)
            instance = new BrewDAO();
        return instance;
    }

    private BrewDAO() {
        super(RunehubConstants.SKILL_DB, Brew.class);
    }
}

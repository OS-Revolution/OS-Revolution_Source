package ethos.runehub.skill.artisan.fletching.bows;

import ethos.runehub.RunehubConstants;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class StrungBowDAO extends BetaAbstractDataAcessObject<StrungBow> {

    private static StrungBowDAO instance = null;

    public static StrungBowDAO getInstance() {
        if (instance == null)
            instance = new StrungBowDAO();
        return instance;
    }

    private StrungBowDAO() {
        super(RunehubConstants.SKILL_DB, StrungBow.class);
    }
}

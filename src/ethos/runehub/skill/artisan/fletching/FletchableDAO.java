package ethos.runehub.skill.artisan.fletching;

import ethos.runehub.RunehubConstants;
import ethos.runehub.skill.artisan.fletching.bows.StrungBow;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class FletchableDAO extends BetaAbstractDataAcessObject<Fletchable> {

    private static FletchableDAO instance = null;

    public static FletchableDAO getInstance() {
        if (instance == null)
            instance = new FletchableDAO();
        return instance;
    }

    private FletchableDAO() {
        super(RunehubConstants.SKILL_DB, Fletchable.class);
    }
}

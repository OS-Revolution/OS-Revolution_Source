package ethos.runehub.skill.combat.prayer.remains;

import ethos.runehub.RunehubConstants;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class RemainsDAO extends BetaAbstractDataAcessObject<Remains> {

    private static RemainsDAO instance = null;

    public static RemainsDAO getInstance() {
        if (instance == null)
            instance = new RemainsDAO();
        return instance;
    }

    private RemainsDAO() {
        super(RunehubConstants.SKILL_DB, Remains.class);
    }
}

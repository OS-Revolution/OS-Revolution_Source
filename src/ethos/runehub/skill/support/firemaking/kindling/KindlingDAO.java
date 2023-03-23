package ethos.runehub.skill.support.firemaking.kindling;

import ethos.runehub.RunehubConstants;
import ethos.runehub.skill.combat.prayer.remains.Remains;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class KindlingDAO extends BetaAbstractDataAcessObject<Kindling> {

    private static KindlingDAO instance = null;

    public static KindlingDAO getInstance() {
        if (instance == null)
            instance = new KindlingDAO();
        return instance;
    }

    private KindlingDAO() {
        super(RunehubConstants.SKILL_DB, Kindling.class);
    }
}

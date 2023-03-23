package ethos.runehub.skill.support.slayer;

import ethos.runehub.RunehubConstants;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class SuperiorSlayerMonsterDAO extends BetaAbstractDataAcessObject<SuperiorSlayerMonster> {

    private static SuperiorSlayerMonsterDAO instance = null;

    public static SuperiorSlayerMonsterDAO getInstance() {
        if (instance == null)
            instance = new SuperiorSlayerMonsterDAO();
        return instance;
    }

    private SuperiorSlayerMonsterDAO() {
        super(RunehubConstants.SKILL_DB, SuperiorSlayerMonster.class);
    }
}

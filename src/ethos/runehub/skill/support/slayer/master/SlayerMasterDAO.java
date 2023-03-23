package ethos.runehub.skill.support.slayer.master;

import ethos.runehub.RunehubConstants;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class SlayerMasterDAO extends BetaAbstractDataAcessObject<SlayerMaster> {

    private static SlayerMasterDAO instance = null;

    public static SlayerMasterDAO getInstance() {
        if (instance == null)
            instance = new SlayerMasterDAO();
        return instance;
    }

    private SlayerMasterDAO() {
        super(RunehubConstants.SKILL_DB, SlayerMaster.class);
    }
}

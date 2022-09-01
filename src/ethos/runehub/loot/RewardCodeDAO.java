package ethos.runehub.loot;

import ethos.runehub.RunehubConstants;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class RewardCodeDAO extends BetaAbstractDataAcessObject<RewardCode> {

    private static RewardCodeDAO instance = null;

    public static RewardCodeDAO getInstance() {
        if (instance == null)
            instance = new RewardCodeDAO();
        return instance;
    }

    private RewardCodeDAO() {
        super(RunehubConstants.LOOT_DB, RewardCode.class);
    }
}

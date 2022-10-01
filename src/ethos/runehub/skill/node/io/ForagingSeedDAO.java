package ethos.runehub.skill.node.io;

import ethos.runehub.RunehubConstants;
import ethos.runehub.skill.gathering.farming.foraging.ForagingSeed;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class ForagingSeedDAO extends BetaAbstractDataAcessObject<ForagingSeed> {

    private static ForagingSeedDAO instance = null;

    public static ForagingSeedDAO getInstance() {
        if (instance == null)
            instance = new ForagingSeedDAO();
        return instance;
    }

    private ForagingSeedDAO() {
        super(RunehubConstants.NODE_DB, ForagingSeed.class);
    }
}

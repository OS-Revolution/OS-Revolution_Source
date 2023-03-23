package ethos.runehub.entity.mob.hostile;


import ethos.runehub.RunehubConstants;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class HostileMobContextDAO extends BetaAbstractDataAcessObject<HostileMobContext> {

    private static HostileMobContextDAO instance = null;

    public static HostileMobContextDAO getInstance() {
        if (instance == null)
            instance = new HostileMobContextDAO();
        return instance;
    }

    private HostileMobContextDAO() {
        super(RunehubConstants.OS_DEFINTIONS_DB, HostileMobContext.class);
        this.getDatabaseServiceProvider().createTable();
    }

}

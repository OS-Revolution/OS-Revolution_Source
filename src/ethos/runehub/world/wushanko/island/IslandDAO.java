package ethos.runehub.world.wushanko.island;

import ethos.runehub.RunehubConstants;
import ethos.runehub.world.wushanko.region.IslandRegion;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class IslandDAO extends BetaAbstractDataAcessObject<Island> {

    private static IslandDAO instance = null;

    public static IslandDAO getInstance() {
        if (instance == null)
            instance = new IslandDAO();
        return instance;
    }

    private IslandDAO() {
        super(RunehubConstants.SAILING_DB, Island.class);
    }
}

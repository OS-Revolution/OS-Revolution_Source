package ethos.runehub.world.wushanko.region;

import ethos.runehub.RunehubConstants;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class IslandRegionDAO extends BetaAbstractDataAcessObject<IslandRegion> {

    private static IslandRegionDAO instance = null;

    public static IslandRegionDAO getInstance() {
        if (instance == null)
            instance = new IslandRegionDAO();
        return instance;
    }

    private IslandRegionDAO() {
        super(RunehubConstants.SAILING_DB, IslandRegion.class);
    }
}

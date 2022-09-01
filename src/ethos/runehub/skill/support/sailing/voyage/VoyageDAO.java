package ethos.runehub.skill.support.sailing.voyage;

import ethos.runehub.RunehubConstants;
import ethos.runehub.skill.support.sailing.ship.Ship;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class VoyageDAO extends BetaAbstractDataAcessObject<Voyage> {

    private static VoyageDAO instance = null;

    public static VoyageDAO getInstance() {
        if (instance == null)
            instance = new VoyageDAO();
        return instance;
    }

    private VoyageDAO() {
        super(RunehubConstants.SAILING_DB, Voyage.class);
    }
}

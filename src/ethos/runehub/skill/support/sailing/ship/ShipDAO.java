package ethos.runehub.skill.support.sailing.ship;

import ethos.runehub.RunehubConstants;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class ShipDAO extends BetaAbstractDataAcessObject<Ship> {

    private static ShipDAO instance = null;

    public static ShipDAO getInstance() {
        if (instance == null)
            instance = new ShipDAO();
        return instance;
    }

    private ShipDAO() {
        super(RunehubConstants.SAILING_DB, Ship.class);
    }
}

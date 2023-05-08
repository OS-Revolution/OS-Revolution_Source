package ethos.runehub.entity.player;

import ethos.runehub.RunehubConstants;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class IdleBrewingStationDAO extends BetaAbstractDataAcessObject<IdleBrewingStation> {

    private static IdleBrewingStationDAO instance = null;

    public static IdleBrewingStationDAO getInstance() {
        if (instance == null)
            instance = new IdleBrewingStationDAO();
        return instance;
    }

    private IdleBrewingStationDAO() {
        super(RunehubConstants.PLAYER_DB, IdleBrewingStation.class);
    }
}

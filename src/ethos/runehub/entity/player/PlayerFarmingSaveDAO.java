package ethos.runehub.entity.player;

import ethos.runehub.RunehubConstants;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class PlayerFarmingSaveDAO extends BetaAbstractDataAcessObject<PlayerFarmingSave> {

    private static PlayerFarmingSaveDAO instance = null;

    public static PlayerFarmingSaveDAO getInstance() {
        if (instance == null)
            instance = new PlayerFarmingSaveDAO();
        return instance;
    }

    private PlayerFarmingSaveDAO() {
        super(RunehubConstants.PLAYER_DB, PlayerFarmingSave.class);
    }
}

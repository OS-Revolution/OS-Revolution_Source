package ethos.runehub.content.journey;

import ethos.runehub.RunehubConstants;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class JourneyDAO extends BetaAbstractDataAcessObject<Journey> {

    private static JourneyDAO instance = null;

    public static JourneyDAO getInstance() {
        if (instance == null)
            instance = new JourneyDAO();
        return instance;
    }

    private JourneyDAO() {
        super(RunehubConstants.PLAYER_DB, Journey.class);
    }
}

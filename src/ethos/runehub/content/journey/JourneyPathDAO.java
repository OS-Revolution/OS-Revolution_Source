package ethos.runehub.content.journey;

import ethos.runehub.RunehubConstants;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class JourneyPathDAO extends BetaAbstractDataAcessObject<JourneyPath> {

    private static JourneyPathDAO instance = null;

    public static JourneyPathDAO getInstance() {
        if (instance == null)
            instance = new JourneyPathDAO();
        return instance;
    }

    private JourneyPathDAO() {
        super(RunehubConstants.JOURNEY_DB, JourneyPath.class);
    }
}

package ethos.runehub.content.journey;

import ethos.runehub.RunehubConstants;
import ethos.runehub.content.achievement.Achievement;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class JourneyStepDAO extends BetaAbstractDataAcessObject<JourneyStep> {

    private static JourneyStepDAO instance = null;

    public static JourneyStepDAO getInstance() {
        if (instance == null)
            instance = new JourneyStepDAO();
        return instance;
    }

    private JourneyStepDAO() {
        super(RunehubConstants.JOURNEY_DB, JourneyStep.class);
    }
}

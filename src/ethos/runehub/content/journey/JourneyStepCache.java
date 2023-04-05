package ethos.runehub.content.journey;

import org.runehub.api.io.load.LazyLoader;

public class JourneyStepCache extends LazyLoader<Long, JourneyStep> {

    private static JourneyStepCache instance = null;

    public static JourneyStepCache getInstance() {
        if (instance == null)
            instance = new JourneyStepCache();
        return instance;
    }

    private JourneyStepCache() {
        super(JourneyStepDAO.getInstance());
    }
}

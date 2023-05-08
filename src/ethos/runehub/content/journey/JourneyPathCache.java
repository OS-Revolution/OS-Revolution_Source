package ethos.runehub.content.journey;

import org.runehub.api.io.load.LazyLoader;

public class JourneyPathCache extends LazyLoader<Integer, JourneyPath> {

    private static JourneyPathCache instance = null;

    public static JourneyPathCache getInstance() {
        if (instance == null)
            instance = new JourneyPathCache();
        return instance;
    }

    private JourneyPathCache() {
        super(JourneyPathDAO.getInstance());
    }
}

package ethos.runehub.content.journey;

import org.runehub.api.io.load.LazyLoader;

public class JourneyCache extends LazyLoader<Integer, Journey> {

    private static JourneyCache instance = null;

    public static JourneyCache getInstance() {
        if (instance == null)
            instance = new JourneyCache();
        return instance;
    }

    private JourneyCache() {
        super(JourneyDAO.getInstance());
    }
}

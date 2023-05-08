package ethos.runehub.content.charter;

import org.runehub.api.io.load.LazyLoader;

public class DestinationCache extends LazyLoader<Long, Destination> {

    private static DestinationCache instance = null;

    public static DestinationCache getInstance() {
        if (instance == null)
            instance = new DestinationCache();
        return instance;
    }

    private DestinationCache() {
        super(DestinationDAO.getInstance());
    }
}

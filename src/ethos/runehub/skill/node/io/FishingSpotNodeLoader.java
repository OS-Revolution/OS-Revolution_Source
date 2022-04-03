package ethos.runehub.skill.node.io;

import ethos.runehub.skill.node.impl.gatherable.impl.FishingSpotNode;
import org.runehub.api.io.load.LazyLoader;

public class FishingSpotNodeLoader extends LazyLoader<Integer, FishingSpotNode> {

    private static FishingSpotNodeLoader instance = null;

    public static FishingSpotNodeLoader getInstance() {
        if (instance == null)
            instance = new FishingSpotNodeLoader();
        return instance;
    }

    private FishingSpotNodeLoader() {
        super(FishingSpotNodeDAO.getInstance());
    }
}

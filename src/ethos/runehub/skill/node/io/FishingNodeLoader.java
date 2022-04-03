package ethos.runehub.skill.node.io;

import ethos.runehub.skill.node.impl.gatherable.impl.FishingNode;
import ethos.runehub.skill.node.impl.gatherable.impl.MiningNode;
import org.runehub.api.io.load.LazyLoader;

public class FishingNodeLoader extends LazyLoader<Integer, FishingNode> {

    private static FishingNodeLoader instance = null;

    public static FishingNodeLoader getInstance() {
        if (instance == null)
            instance = new FishingNodeLoader();
        return instance;
    }

    private FishingNodeLoader() {
        super(FishingNodeDAO.getInstance());
    }
}

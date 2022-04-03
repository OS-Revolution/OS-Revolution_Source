package ethos.runehub.skill.node.io;

import ethos.runehub.skill.node.impl.gatherable.impl.MiningNode;
import org.runehub.api.io.load.LazyLoader;

public class MiningNodeLoader extends LazyLoader<Integer, MiningNode> {

    private static MiningNodeLoader instance = null;

    public static MiningNodeLoader getInstance() {
        if (instance == null)
            instance = new MiningNodeLoader();
        return instance;
    }

    private MiningNodeLoader() {
        super(MiningNodeDAO.getInstance());
    }
}

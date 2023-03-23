package ethos.runehub.skill.support.thieving;

import ethos.runehub.skill.node.impl.support.PickpocketNode;
import org.runehub.api.io.load.LazyLoader;

public class PickpocketCache extends LazyLoader<Integer, PickpocketNode> {

    private static PickpocketCache instance = null;

    public static PickpocketCache getInstance() {
        if (instance == null)
            instance = new PickpocketCache();
        return instance;
    }

    private PickpocketCache() {
        super(PickpocketDAO.getInstance());
    }
}

package ethos.runehub.skill.node.io;

import ethos.runehub.skill.node.impl.gatherable.impl.ForagingNode;
import org.runehub.api.io.load.LazyLoader;

public class ForagingNodeLoader extends LazyLoader<Integer, ForagingNode> {

    private static ForagingNodeLoader instance = null;

    public static ForagingNodeLoader getInstance() {
        if (instance == null)
            instance = new ForagingNodeLoader();
        return instance;
    }

    private ForagingNodeLoader() {
        super(ForagingNodeDAO.getInstance());
    }
}

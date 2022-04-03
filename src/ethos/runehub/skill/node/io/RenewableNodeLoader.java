package ethos.runehub.skill.node.io;

import ethos.runehub.skill.node.impl.RenewableNode;
import org.runehub.api.io.load.LazyLoader;

public class RenewableNodeLoader extends LazyLoader<Integer, RenewableNode> {

    private static RenewableNodeLoader instance = null;

    public static RenewableNodeLoader getInstance() {
        if (instance == null)
            instance = new RenewableNodeLoader();
        return instance;
    }

    private RenewableNodeLoader() {
        super(RenewableNodeDAO.getInstance());
    }
}

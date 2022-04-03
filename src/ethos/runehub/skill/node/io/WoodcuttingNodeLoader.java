package ethos.runehub.skill.node.io;

import ethos.runehub.skill.node.impl.gatherable.impl.WoodcuttingNode;
import org.runehub.api.io.load.LazyLoader;

public class WoodcuttingNodeLoader extends LazyLoader<Integer, WoodcuttingNode> {

    private static WoodcuttingNodeLoader instance = null;

    public static WoodcuttingNodeLoader getInstance() {
        if (instance == null)
            instance = new WoodcuttingNodeLoader();
        return instance;
    }

    private WoodcuttingNodeLoader() {
        super(WoodcuttingNodeDAO.getInstance());
    }
}

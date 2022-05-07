package ethos.runehub.skill.node.io;

import ethos.runehub.skill.node.impl.gatherable.impl.ThievingStallNode;
import ethos.runehub.skill.node.impl.gatherable.impl.WoodcuttingNode;
import org.runehub.api.io.load.LazyLoader;

public class ThievingStallNodeLoader extends LazyLoader<Integer, ThievingStallNode> {

    private static ThievingStallNodeLoader instance = null;

    public static ThievingStallNodeLoader getInstance() {
        if (instance == null)
            instance = new ThievingStallNodeLoader();
        return instance;
    }

    private ThievingStallNodeLoader() {
        super(ThievingStallDAO.getInstance());
    }
}

package ethos.runehub.skill.node.io;

import ethos.runehub.RunehubConstants;
import ethos.runehub.skill.node.impl.gatherable.impl.ThievingStallNode;
import ethos.runehub.skill.node.impl.gatherable.impl.WoodcuttingNode;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;
import org.runehub.api.io.file.impl.APIFileSystem;

public class ThievingStallDAO extends BetaAbstractDataAcessObject<ThievingStallNode> {

    private static ThievingStallDAO instance = null;

    public static ThievingStallDAO getInstance() {
        if (instance == null)
            instance = new ThievingStallDAO();
        return instance;
    }

    private ThievingStallDAO() {
        super(RunehubConstants.NODE_DB, ThievingStallNode.class);
    }
}

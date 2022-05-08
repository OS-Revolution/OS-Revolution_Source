package ethos.runehub.skill.node.io;

import ethos.runehub.skill.node.impl.gatherable.impl.WoodcuttingNode;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;
import org.runehub.api.io.file.impl.APIFileSystem;

public class WoodcuttingNodeDAO extends BetaAbstractDataAcessObject<WoodcuttingNode> {

    private static WoodcuttingNodeDAO instance = null;

    public static WoodcuttingNodeDAO getInstance() {
        if (instance == null)
            instance = new WoodcuttingNodeDAO();
        return instance;
    }

    private WoodcuttingNodeDAO() {
        super("./Data/runehub/db/nodes.db", WoodcuttingNode.class);
    }
}

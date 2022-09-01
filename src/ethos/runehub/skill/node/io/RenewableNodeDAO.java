package ethos.runehub.skill.node.io;

import ethos.runehub.RunehubConstants;
import ethos.runehub.skill.node.impl.RenewableNode;
import ethos.runehub.skill.node.impl.gatherable.impl.FishingNode;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;
import org.runehub.api.io.file.impl.APIFileSystem;

public class RenewableNodeDAO extends BetaAbstractDataAcessObject<RenewableNode> {

    private static RenewableNodeDAO instance = null;

    public static RenewableNodeDAO getInstance() {
        if (instance == null)
            instance = new RenewableNodeDAO();
        return instance;
    }

    private RenewableNodeDAO() {
        super(RunehubConstants.NODE_DB, RenewableNode.class);
    }
}

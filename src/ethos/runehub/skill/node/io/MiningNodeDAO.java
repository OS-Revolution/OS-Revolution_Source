package ethos.runehub.skill.node.io;

import ethos.runehub.RunehubConstants;
import ethos.runehub.skill.node.impl.gatherable.impl.MiningNode;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;
import org.runehub.api.io.file.impl.APIFileSystem;

public class MiningNodeDAO extends BetaAbstractDataAcessObject<MiningNode> {

    private static MiningNodeDAO instance = null;

    public static MiningNodeDAO getInstance() {
        if (instance == null)
            instance = new MiningNodeDAO();
        return instance;
    }

    private MiningNodeDAO() {
        super(RunehubConstants.NODE_DB, MiningNode.class);
    }
}

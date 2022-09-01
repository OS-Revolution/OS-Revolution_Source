package ethos.runehub.skill.node.io;

import ethos.runehub.RunehubConstants;
import ethos.runehub.skill.node.impl.gatherable.impl.FishingNode;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;
import org.runehub.api.io.file.impl.APIFileSystem;

public class FishingNodeDAO extends BetaAbstractDataAcessObject<FishingNode> {

    private static FishingNodeDAO instance = null;

    public static FishingNodeDAO getInstance() {
        if (instance == null)
            instance = new FishingNodeDAO();
        return instance;
    }

    private FishingNodeDAO() {
        super(RunehubConstants.NODE_DB, FishingNode.class);
    }
}

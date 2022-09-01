package ethos.runehub.skill.node.io;

import ethos.runehub.RunehubConstants;
import ethos.runehub.skill.node.impl.gatherable.GatheringNode;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;
import org.runehub.api.io.file.impl.APIFileSystem;

public class GatheringNodeDAO extends BetaAbstractDataAcessObject<GatheringNode> {

    private static GatheringNodeDAO instance = null;

    public static GatheringNodeDAO getInstance() {
        if (instance == null)
            instance = new GatheringNodeDAO();
        return instance;
    }

    private GatheringNodeDAO() {
        super(RunehubConstants.NODE_DB, GatheringNode.class);
    }
}

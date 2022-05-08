package ethos.runehub.skill.node.io;

import ethos.runehub.skill.node.impl.gatherable.impl.FishingNode;
import ethos.runehub.skill.node.impl.gatherable.impl.FishingSpotNode;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;
import org.runehub.api.io.file.impl.APIFileSystem;

public class FishingSpotNodeDAO extends BetaAbstractDataAcessObject<FishingSpotNode> {

    private static FishingSpotNodeDAO instance = null;

    public static FishingSpotNodeDAO getInstance() {
        if (instance == null)
            instance = new FishingSpotNodeDAO();
        return instance;
    }

    private FishingSpotNodeDAO() {
        super("./Data/runehub/db/nodes.db", FishingSpotNode.class);
    }
}

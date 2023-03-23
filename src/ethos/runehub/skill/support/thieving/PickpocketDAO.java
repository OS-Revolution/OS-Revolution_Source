package ethos.runehub.skill.support.thieving;

import ethos.runehub.RunehubConstants;
import ethos.runehub.skill.node.impl.support.PickpocketNode;
import ethos.runehub.skill.support.slayer.SlayerKnowledgeReward;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class PickpocketDAO extends BetaAbstractDataAcessObject<PickpocketNode> {

    private static PickpocketDAO instance = null;

    public static PickpocketDAO getInstance() {
        if (instance == null)
            instance = new PickpocketDAO();
        return instance;
    }

    private PickpocketDAO() {
        super(RunehubConstants.NODE_DB, PickpocketNode.class);
    }
}

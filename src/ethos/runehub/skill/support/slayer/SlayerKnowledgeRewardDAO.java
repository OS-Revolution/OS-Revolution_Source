package ethos.runehub.skill.support.slayer;

import ethos.runehub.RunehubConstants;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class SlayerKnowledgeRewardDAO extends BetaAbstractDataAcessObject<SlayerKnowledgeReward> {

    private static SlayerKnowledgeRewardDAO instance = null;

    public static SlayerKnowledgeRewardDAO getInstance() {
        if (instance == null)
            instance = new SlayerKnowledgeRewardDAO();
        return instance;
    }

    private SlayerKnowledgeRewardDAO() {
        super(RunehubConstants.SKILL_DB, SlayerKnowledgeReward.class);
    }
}

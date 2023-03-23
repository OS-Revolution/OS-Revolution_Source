package ethos.runehub.skill.support.slayer;

import org.runehub.api.io.load.LazyLoader;

public class SlayerKnowledgeRewardCache extends LazyLoader<Integer, SlayerKnowledgeReward> {

    private static SlayerKnowledgeRewardCache instance = null;

    public static SlayerKnowledgeRewardCache getInstance() {
        if (instance == null)
            instance = new SlayerKnowledgeRewardCache();
        return instance;
    }

    private SlayerKnowledgeRewardCache() {
        super(SlayerKnowledgeRewardDAO.getInstance());
    }
}

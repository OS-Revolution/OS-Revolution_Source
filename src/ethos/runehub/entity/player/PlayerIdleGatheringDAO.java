package ethos.runehub.entity.player;

import ethos.runehub.RunehubConstants;
import ethos.runehub.skill.gathering.tool.GatheringTool;
import ethos.runehub.skill.gathering.tool.GatheringToolDAO;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;
import org.runehub.api.io.file.impl.APIFileSystem;

public class PlayerIdleGatheringDAO extends BetaAbstractDataAcessObject<PlayerIdleGatheringContext> {

    private static PlayerIdleGatheringDAO instance = null;

    public static PlayerIdleGatheringDAO getInstance() {
        if (instance == null)
            instance = new PlayerIdleGatheringDAO();
        return instance;
    }

    private PlayerIdleGatheringDAO() {
        super(RunehubConstants.PLAYER_DB, PlayerIdleGatheringContext.class);
    }
}

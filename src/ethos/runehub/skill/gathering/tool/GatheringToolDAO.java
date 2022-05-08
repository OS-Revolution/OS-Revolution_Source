package ethos.runehub.skill.gathering.tool;

import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;
import org.runehub.api.io.file.impl.APIFileSystem;

public class GatheringToolDAO  extends BetaAbstractDataAcessObject<GatheringTool> {

    private static GatheringToolDAO instance = null;

    public static GatheringToolDAO getInstance() {
        if (instance == null)
            instance = new GatheringToolDAO();
        return instance;
    }

    private GatheringToolDAO() {
        super("./Data/runehub/db/tools.db", GatheringTool.class);
    }
}

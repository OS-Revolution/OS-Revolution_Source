package ethos.runehub.skill.node.io;

import ethos.runehub.RunehubConstants;
import ethos.runehub.skill.gathering.fishing.FishLevel;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;
import org.runehub.api.io.file.impl.APIFileSystem;

public class FishLevelDAO extends BetaAbstractDataAcessObject<FishLevel> {

    private static FishLevelDAO instance = null;

    public static FishLevelDAO getInstance() {
        if (instance == null)
            instance = new FishLevelDAO();
        return instance;
    }

    private FishLevelDAO() {
        super(RunehubConstants.NODE_DB, FishLevel.class);
    }
}

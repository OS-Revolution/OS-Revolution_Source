package ethos.runehub.entity.mob;

import ethos.runehub.RunehubConstants;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class MobSpawnDAO extends BetaAbstractDataAcessObject<MobSpawn> {

    private static MobSpawnDAO instance = null;

    public static MobSpawnDAO getInstance() {
        if (instance == null)
            instance = new MobSpawnDAO();
        return instance;
    }

    private MobSpawnDAO() {
        super(RunehubConstants.OS_DEFINTIONS_DB, MobSpawn.class);
    }
}

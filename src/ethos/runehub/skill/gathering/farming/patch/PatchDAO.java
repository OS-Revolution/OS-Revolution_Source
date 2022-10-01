package ethos.runehub.skill.gathering.farming.patch;

import ethos.runehub.RunehubConstants;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class PatchDAO extends BetaAbstractDataAcessObject<Patch> {

    private static PatchDAO instance = null;

    public static PatchDAO getInstance() {
        if (instance == null)
            instance = new PatchDAO();
        return instance;
    }

    private PatchDAO() {
        super(RunehubConstants.FARMING_DB, Patch.class);
    }
}

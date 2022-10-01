package ethos.runehub.skill.gathering.farming.crop;

import ethos.runehub.RunehubConstants;
import ethos.runehub.skill.gathering.farming.patch.Patch;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class CropDAO extends BetaAbstractDataAcessObject<Crop> {

    private static CropDAO instance = null;

    public static CropDAO getInstance() {
        if (instance == null)
            instance = new CropDAO();
        return instance;
    }

    private CropDAO() {
        super(RunehubConstants.FARMING_DB, Crop.class);
    }
}

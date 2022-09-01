package ethos.runehub.skill.artisan.herblore.potion;

import ethos.runehub.RunehubConstants;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;
import org.runehub.api.io.file.impl.APIFileSystem;

public class PotionDAO extends BetaAbstractDataAcessObject<Potion> {

    private static PotionDAO instance = null;

    public static PotionDAO getInstance() {
        if (instance == null)
            instance = new PotionDAO();
        return instance;
    }

    private PotionDAO() {
        super(RunehubConstants.ITEM_ACTION_DB, Potion.class);
    }
}

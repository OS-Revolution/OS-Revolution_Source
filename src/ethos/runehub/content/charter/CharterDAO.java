package ethos.runehub.content.charter;

import ethos.runehub.RunehubConstants;
import ethos.runehub.content.journey.Journey;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class CharterDAO extends BetaAbstractDataAcessObject<Charter> {

    private static CharterDAO instance = null;

    public static CharterDAO getInstance() {
        if (instance == null)
            instance = new CharterDAO();
        return instance;
    }

    private CharterDAO() {
        super(RunehubConstants.REGION_DB, Charter.class);
    }
}

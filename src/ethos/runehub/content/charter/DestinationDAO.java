package ethos.runehub.content.charter;

import ethos.runehub.RunehubConstants;
import ethos.runehub.content.journey.Journey;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;

public class DestinationDAO extends BetaAbstractDataAcessObject<Destination> {

    private static DestinationDAO instance = null;

    public static DestinationDAO getInstance() {
        if (instance == null)
            instance = new DestinationDAO();
        return instance;
    }

    private DestinationDAO() {
        super(RunehubConstants.REGION_DB, Destination.class);
    }
}

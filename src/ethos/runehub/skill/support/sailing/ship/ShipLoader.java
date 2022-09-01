package ethos.runehub.skill.support.sailing.ship;

import org.runehub.api.io.load.LazyLoader;

public class ShipLoader extends LazyLoader<Integer,Ship> {

    private static ShipLoader instance = null;

    public static ShipLoader getInstance() {
        if (instance == null)
            instance = new ShipLoader();
        return instance;
    }

    private ShipLoader() {
        super(ShipDAO.getInstance());
    }
}

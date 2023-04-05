package ethos.runehub.world;

import org.runehub.api.io.load.LazyLoader;
import org.runehub.api.model.world.region.Region;
import org.runehub.api.model.world.region.RegionContext;
import org.runehub.api.model.world.region.RegionContextDataAccessObject;
import org.runehub.api.model.world.region.RegionDataAccessObject;

public class RegionContextCache extends LazyLoader <Long, RegionContext> {

    private static RegionContextCache instance = null;

    public static RegionContextCache getInstance() {
        if (instance == null)
            instance = new RegionContextCache();
        return instance;
    }

    public RegionContextCache() {
        super(RegionContextDataAccessObject.getInstance());
    }
}

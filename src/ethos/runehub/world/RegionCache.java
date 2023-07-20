package ethos.runehub.world;

import org.runehub.api.io.data.DataAcessObjectBase;
import org.runehub.api.io.load.LazyLoader;
import org.runehub.api.model.world.region.Region;
import org.runehub.api.model.world.region.RegionDataAccessObject;

public class RegionCache extends LazyLoader<Long, Region> {

    private static RegionCache instance = null;

    public static RegionCache getInstance() {
        if (instance == null)
            instance = new RegionCache();
        return instance;
    }

    public RegionCache() {
        super(RegionDataAccessObject.getInstance());
    }
}

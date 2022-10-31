package ethos.runehub.world;

import ethos.clip.Region;
import org.runehub.api.io.load.LazyLoader;

public class WorldController {

    private static WorldController instance = null;

    public static WorldController getInstance() {
        if (instance == null)
            instance = new WorldController();
        return instance;
    }

    public Region loadRegion(int regionId) {
        return regionLoader.read(regionId);
    }

    public boolean isRegionLoaded(int regionId) {
        return regionLoader.containsKey(regionId);
    }

    private WorldController() {
        this.regionLoader = new RegionLoader();
    }

    private final LazyLoader<Integer, Region> regionLoader;
}

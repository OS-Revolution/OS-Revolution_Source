package ethos.runehub.world.wushanko.region;

import org.runehub.api.io.load.LazyLoader;

public class IslandRegionLoader extends LazyLoader<Integer, IslandRegion> {

    private static IslandRegionLoader instance = null;

    public static IslandRegionLoader getInstance() {
        if (instance == null)
            instance = new IslandRegionLoader();
        return instance;
    }

    private IslandRegionLoader() {
        super(IslandRegionDAO.getInstance());
    }
}

package ethos.runehub.skill.gathering.farming.crop;

import org.runehub.api.io.load.LazyLoader;

public class CropCache extends LazyLoader<Integer, Crop> {

    private static CropCache instance = null;

    public static CropCache getInstance() {
        if (instance == null)
            instance = new CropCache();
        return instance;
    }

    private CropCache() {
        super(CropDAO.getInstance());
    }
}

package ethos.runehub.skill.node.io;

import ethos.runehub.skill.gathering.fishing.FishLevel;
import ethos.runehub.skill.node.impl.gatherable.impl.MiningNode;
import org.runehub.api.io.load.LazyLoader;

public class FishLevelLoader extends LazyLoader<Integer, FishLevel> {

    private static FishLevelLoader instance = null;

    public static FishLevelLoader getInstance() {
        if (instance == null)
            instance = new FishLevelLoader();
        return instance;
    }

    private FishLevelLoader() {
        super(FishLevelDAO.getInstance());
    }
}

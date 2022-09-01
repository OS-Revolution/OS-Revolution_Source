package ethos.runehub.world.wushanko.island;

import org.runehub.api.io.load.LazyLoader;

public class IslandLoader extends LazyLoader<Integer, Island> {

    private static IslandLoader instance = null;

    public static IslandLoader getInstance() {
        if (instance == null)
            instance = new IslandLoader();
        return instance;
    }

    private IslandLoader() {
        super(IslandDAO.getInstance());
    }
}

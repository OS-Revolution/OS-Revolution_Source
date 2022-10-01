package ethos.runehub.skill.node.io;

import ethos.runehub.skill.gathering.farming.foraging.ForagingSeed;
import org.runehub.api.io.load.LazyLoader;

public class ForagingSeedLoader extends LazyLoader<Integer, ForagingSeed> {

    private static ForagingSeedLoader instance = null;

    public static ForagingSeedLoader getInstance() {
        if (instance == null)
            instance = new ForagingSeedLoader();
        return instance;
    }

    private ForagingSeedLoader() {
        super(ForagingSeedDAO.getInstance());
    }
}
